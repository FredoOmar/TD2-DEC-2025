package JavaSource;

import java.util.Objects;

public class Ingredients {
    private int id;
    private String name;
    private double price;
    private String category;


    public Ingredients(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }


    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }


   public boolean isVegetable() {
        return "vegetable".equalsIgnoreCase(category);
    }


    public boolean isAnimal() {
        return "Animal".equalsIgnoreCase(category);
    }


    public boolean isMarine() {
        return "Marine".equalsIgnoreCase(category);
    }

    public boolean isDairy() {
        return "Dairy".equalsIgnoreCase(category);
    }

    @Override
    public String toString() {
        return String.format("Ingredient[id=%d, name='%s', price=%.2f, category='%s']",
                id, name, price, category);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }
}
