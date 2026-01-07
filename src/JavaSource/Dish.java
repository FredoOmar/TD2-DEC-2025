package JavaSource;

import java.util.List;
import java.util.Objects;

// Classe Dish pour représenter la table Dish
public class Dish {
    private int id;
    private String name;
    private DishTypeEnum dishType;
    private List<Ingredients> ingredients;


    public Dish(int id, String name, DishTypeEnum dishType, List<Ingredients> ingredients) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
        this.ingredients = ingredients;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DishTypeEnum getDishType() {
        return dishType;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Dish dish)) return false;
        return id == dish.id && Objects.equals(name, dish.name) && dishType == dish.dishType && Objects.equals(ingredients, dish.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dishType, ingredients);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishType=" + dishType +
                ", ingredients=" + ingredients +
                '}';
    }

    public double getDishCost() {
        double totalCost = 0.0;
        for (Ingredients ingredient : this.ingredients) {
            if (ingredient.getRequired_quantity() <= 0) {
                throw new IllegalArgumentException("La quantité nécessaire pour l'ingrédient " + ingredient.getName() + " est inconnue ou nulle.");
            }
            totalCost += ingredient.getPrice() * ingredient.getRequired_quantity();
        }
        return totalCost;
    }
}
