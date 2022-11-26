import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumsTest {
    @Test
    public void testPrintDishTypes() {
        DishType.printDishTypes();
    }
    @Test
    public void testGetDishTypeByNumber() {
        DishType actualValue = DishType.getDishTypeByNumber(1);
        DishType expectedValue = DishType.SOUP;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testPrintDrinkTypes() {
        DrinkType.printDrinkTypes();
    }
    @Test
    public void testGetDrinkTypeByNumber() {
        DrinkType actualValue = DrinkType.getDrinkTypeByNumber(1);
        DrinkType expectedValue = DrinkType.HOT;
        assertEquals(expectedValue, actualValue);
    }
}