import java.util.Arrays;

public enum DishType {
    SOUP("Soups"),
    SALAD("Salads"),
    GRILL("Grills"),
    DESSERT("Desserts");

    private final String textRepresentation;

    DishType(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    public static DishType getDishTypeByNumber(int num) {
        return Arrays.stream(DishType.values()).toList().get(num - 1);
    }

    @Override
    public String toString() {
        return textRepresentation;
    }

    public static void printDishTypes() {
        int n = 0;
        for (DishType element : DishType.values()) {
            n++;
            System.out.println(n + ". " + element);
        }
    }
}
