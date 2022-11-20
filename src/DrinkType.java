import java.util.Arrays;

public enum DrinkType {
    HOT("Hot"),
    NONALCOHOLIC("Non alcoholic"),
    ALCOHOLIC("Alcoholic"),
    COCKTAIL("Coctail");

    private final String textRepresentation;

    DrinkType(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }
    public static DrinkType getDrinkTypeByNumber(int num){
        return Arrays.stream(DrinkType.values()).toList().get(num-1);
    }
    @Override
    public String toString() {
        return textRepresentation;
    }

    public static void printDrinkTypes() {
        int n = 0;
        for (DrinkType element : DrinkType.values()) {
            n++;
            System.out.println(n + ". " + element);
        }
    }
}
