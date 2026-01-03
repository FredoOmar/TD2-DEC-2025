package test;

// IngredientTestSimple.java
public class IngredientTest {

    // Classe Dish pour le test
    static class Dish {
        private String name;

        public Dish(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // Classe Ingredient pour le test
    static class Ingredient {
        private Dish dish;

        public Ingredient(Dish dish) {
            this.dish = dish;
        }

        public String getDishName() {
            return dish != null ? dish.getName() : null;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Tests Ingredient ===");

        // Test 1 : Dish existe
        Dish dish = new Dish("Pizza Margherita");
        Ingredient ingredient = new Ingredient(dish);
        String result1 = ingredient.getDishName();
        boolean test1 = "Pizza Margherita".equals(result1);
        System.out.println("Test 1 (Dish exists): " + (test1 ? "✅ PASS" : "❌ FAIL"));

        // Test 2 : Dish est null
        Ingredient ingredient2 = new Ingredient(null);
        String result2 = ingredient2.getDishName();
        boolean test2 = result2 == null;
        System.out.println("Test 2 (Dish is null): " + (test2 ? "✅ PASS" : "❌ FAIL"));

        // Test 3 : Dish name est null
        Dish dish3 = new Dish(null);
        Ingredient ingredient3 = new Ingredient(dish3);
        String result3 = ingredient3.getDishName();
        boolean test3 = result3 == null;
        System.out.println("Test 3 (Dish name is null): " + (test3 ? "✅ PASS" : "❌ FAIL"));

        // Résumé
        System.out.println("\nRésumé : " +
                (test1 && test2 && test3 ? "✅ TOUS LES TESTS PASSENT" : "❌ CERTAINS TESTS ÉCHOUENT"));
    }
}