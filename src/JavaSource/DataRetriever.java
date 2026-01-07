package JavaSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    public DataRetriever() {
    }
    public Dish findDishById(Integer id) {

        String dishSql = "SELECT * FROM dish WHERE id = ?";
        String ingredientSql = "SELECT * FROM ingredient WHERE id_dish = ?";

        try (Connection con = DBconnection.getDBConnection()) {

            PreparedStatement dishStmt = con.prepareStatement(dishSql);
            dishStmt.setInt(1, id);
            ResultSet dishRs = dishStmt.executeQuery();

            if (!dishRs.next()) {
                return null;
            }

            Dish dish = new Dish(
                    dishRs.getInt("id"),
                    dishRs.getString("name"),
                    DishTypeEnum.fromDb(dishRs.getString("dish_type")),
                    new ArrayList<>()
            );

            PreparedStatement ingStmt = con.prepareStatement(ingredientSql);
            ingStmt.setInt(1, id);
            ResultSet ingRs = ingStmt.executeQuery();

            while (ingRs.next()) {
                dish.getIngredients().add(new Ingredients(
                        ingRs.getInt("id"),
                        ingRs.getString("name"),
                        ingRs.getDouble("price"),
                        CategoryEnum.fromDb(ingRs.getString("category")),
                        dish
                ));
            }

            return dish;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findDishById", e);
        }
    }

    public List<Ingredients> findIngredients(int page, int size) {

        String sql = "SELECT * FROM ingredient LIMIT ? OFFSET ?";
        int offset = (page - 1) * size; // pagination correcte (page commence à 1)

        List<Ingredients> ingredients = new ArrayList<>();

        try (Connection con = DBconnection.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, size);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ingredients.add(new Ingredients(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        CategoryEnum.valueOf(
                                rs.getString("category").toUpperCase()
                        ),
                        null
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur pagination ingrédients", e);
        }

        return ingredients;
    }


    public List<Ingredients> createIngredients(List<Ingredients> newIngredients) {

        String checkSql = "SELECT COUNT(*) FROM ingredient WHERE name = ?";
        String insertSql = "INSERT INTO ingredient(name, price, category, id_dish) VALUES (?, ?, ?, ?)";

        try (Connection con = DBconnection.getDBConnection()) {

            con.setAutoCommit(false);

            for (Ingredients i : newIngredients) {

                try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                    checkStmt.setString(1, i.getName());
                    ResultSet rs = checkStmt.executeQuery();
                    rs.next();

                    if (rs.getInt(1) > 0) {
                        throw new RuntimeException(
                                "Ingrédient déjà existant : " + i.getName()
                        );
                    }
                }

                try (PreparedStatement insertStmt = con.prepareStatement(insertSql)) {
                    insertStmt.setString(1, i.getName());
                    insertStmt.setDouble(2, i.getPrice());
                    insertStmt.setString(3, i.getCategory().name());
                    insertStmt.setObject(
                            4,
                            i.getDish() != null ? i.getDish().getId() : null
                    );
                    insertStmt.executeUpdate();
                }
            }

            con.commit();
            return newIngredients;

        } catch (Exception e) {
            throw new RuntimeException("Transaction annulée", e);
        }
    }


    public Dish saveDish(Dish dish) {

        try (Connection con = DBconnection.getDBConnection()) {

            con.setAutoCommit(false);

            if (dish.getId() == 0) {

                String insert = "INSERT INTO dish(name, dish_type) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(
                        insert,
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, dish.getName());
                ps.setString(2, dish.getDishType().name());
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                keys.next();
                dish.setId(keys.getInt(1));

            } else {

                String update = "UPDATE dish SET name=?, dish_type=? WHERE id=?";
                PreparedStatement ps = con.prepareStatement(update);
                ps.setString(1, dish.getName());
                ps.setString(2, dish.getDishType().name());
                ps.setInt(3, dish.getId());
                ps.executeUpdate();
            }

            String clear = "UPDATE ingredient SET id_dish=NULL WHERE id_dish=?";
            PreparedStatement clearStmt = con.prepareStatement(clear);
            clearStmt.setInt(1, dish.getId());
            clearStmt.executeUpdate();

            String link = "UPDATE ingredient SET id_dish=? WHERE id=?";
            for (Ingredients i : dish.getIngredients()) {
                PreparedStatement linkStmt = con.prepareStatement(link);
                linkStmt.setInt(1, dish.getId());
                linkStmt.setInt(2, i.getId());
                linkStmt.executeUpdate();
            }

            con.commit();
            return dish;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur saveDish", e);
        }
    }


    public List<Dish> findDishByIngredientName(String ingredientName) {

        String sql = """
            SELECT DISTINCT d.*
            FROM dish d
            JOIN ingredient i ON d.id = i.id_dish
            WHERE i.name ILIKE ?
        """;

        List<Dish> dishes = new ArrayList<>();

        try (Connection con = DBconnection.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + ingredientName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dishes.add(new Dish(
                        rs.getInt("id"),
                        rs.getString("name"),
                        DishTypeEnum.fromDb(rs.getString("dish_type")),
                        new ArrayList<>()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findDishByIngredientName", e);
        }

        return dishes;
    }


    public List<Ingredients> findIngredientsByCriteria(
            String ingredientName,
            CategoryEnum category,
            String dishName,
            int page,
            int size
    ) {

        StringBuilder sql = new StringBuilder("""
            SELECT i.*
            FROM ingredient i
            LEFT JOIN dish d ON i.id_dish = d.id
            WHERE 1=1
        """);

        List<Object> params = new ArrayList<>();

        if (ingredientName != null) {
            sql.append(" AND i.name ILIKE ?");
            params.add("%" + ingredientName + "%");
        }

        if (category != null) {
            sql.append(" AND i.category = ?");
            params.add(category.name());
        }

        if (dishName != null) {
            sql.append(" AND d.name ILIKE ?");
            params.add("%" + dishName + "%");
        }

        sql.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add((page - 1) * size);

        List<Ingredients> list = new ArrayList<>();

        try (Connection con = DBconnection.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Ingredients(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        CategoryEnum.valueOf(
                                rs.getString("category").toUpperCase()
                        ),
                        null
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findIngredientsByCriteria", e);
        }

        return list;
    }
}
