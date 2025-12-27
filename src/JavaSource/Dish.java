package JavaSource;

// Classe Dish pour représenter la table Dish
public class Dish {
    private int id;
    private String name;
    private String dishType; // ou DishType si vous créez un enum Java

    public Dish(int id, String name, String dishType) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDishType() { return dishType; }

    @Override
    public String toString() {
        return "Dish{id=" + id + ", name='" + name + "', dishType='" + dishType + "'}";
    }
}
