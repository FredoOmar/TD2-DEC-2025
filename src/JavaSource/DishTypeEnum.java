package JavaSource;

public enum DishTypeEnum {
    STARTER,
    MAIN,
    DESSERT;

    public static DishTypeEnum fromDb(String value) {
        if (value == null) return null;

        return switch (value.toUpperCase()) {
            case "START", "STARTER" -> STARTER;
            case "MAIN", "MAIN_DISH" -> MAIN;
            case "DESSERT" -> DESSERT;
            default -> throw new IllegalArgumentException(
                    "Type de plat inconnu en base : " + value
            );
        };
    }
}
