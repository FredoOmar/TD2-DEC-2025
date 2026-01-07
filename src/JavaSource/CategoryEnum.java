package JavaSource;

public enum CategoryEnum {

    VEGETABLE,
    MEAT,
    FISH,
    FRUIT,
    SPICE,
    ANIMAL;

    public static CategoryEnum fromDb(String value) {

        if (value == null) return null;

        return switch (value.toLowerCase()) {
            case "vegetable", "legume" -> VEGETABLE;
            case "meat", "viande" -> MEAT;
            case "fish", "poisson" -> FISH;
            case "fruit" -> FRUIT;
            case "spice", "epice" -> SPICE;
            case "animal" -> ANIMAL;
            default -> throw new IllegalArgumentException(
                    "Catégorie inconnue en base : " + value
            );
        };
    }
}
