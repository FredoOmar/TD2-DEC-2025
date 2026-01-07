package JavaSource;

import java.util.List;

class DataRetrieverTest {

    public static void main(String[] args) {

        DataRetriever dr = new DataRetriever();

        System.out.println("\n===== TESTS SELON LE SUJET =====\n");

        // a) id = 1
        Dish dish = dr.findDishById(1);
        System.out.println("a) Dish id=1");
        System.out.println("Attendu : Salade Fraîche (Laitue, Tomate)");
        System.out.println("Obtenu : " + dish.getName());
        dish.getIngredients().forEach(i ->
                System.out.println(" - " + i.getName())
        );

        // b) id = 999
        System.out.println("\nb) Dish id=999");
        Dish notFound = dr.findDishById(999);
        System.out.println("Attendu : RuntimeException ou null");
        System.out.println("Obtenu : " + notFound);

        // c) page=2 size=2
        System.out.println("\nc) Ingredients page=2 size=2");
        System.out.println("Attendu : Poulet, Chocolat");
        List<Ingredients> page2 = dr.findIngredients(2, 2);
        page2.forEach(i -> System.out.println(" - " + i.getName()));

        // d) page=3 size=5
        System.out.println("\nd) Ingredients page=3 size=5");
        System.out.println("Attendu : Liste vide");
        List<Ingredients> page3 = dr.findIngredients(3, 5);
        System.out.println("Taille : " + page3.size());

        // e) ingredientName = "eur"
        System.out.println("\ne) Dishes contenant 'eur'");
        System.out.println("Attendu : Gâteau au chocolat");
        List<Dish> dishes = dr.findDishByIngredientName("eur");
        dishes.forEach(d -> System.out.println(" - " + d.getName()));

        // f) criteria
        System.out.println("\nf) Ingredients category=VEGETABLE");
        System.out.println("Attendu : Laitue, Tomate");
        List<Ingredients> vegetables =
                dr.findIngredientsByCriteria(
                        null,
                        CategoryEnum.VEGETABLE,
                        null,
                        1,
                        10
                );

        vegetables.forEach(i ->
                System.out.println(" - " + i.getName())
        );

        System.out.println("\n===== FIN =====");
    }
}
