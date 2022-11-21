import java.util.Arrays;

public enum OrderStatus {
    TAKING("taking"),
    TAKEN("taken"),
    COOKING("cooking"),
    COOKED("cooked"),
    SERVED("served"),
    PAYED("payed");
    private final String textRepresentation;

    OrderStatus(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    @Override
    public String toString() {
        return textRepresentation;
    }
}
