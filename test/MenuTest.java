import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    @Test
    public void testIsMenuCreated() {
        Menu menu = new Menu("Menu.csv");
        int DishItemsCount = menu.getDishItems().size();
        int DrinkItemsCount = menu.getDrinkItems().size();
        boolean isExistsDishItemsCount=DishItemsCount>0;
        boolean isExistsDrinkItemsCount=DrinkItemsCount>0;

        assertTrue(isExistsDishItemsCount && isExistsDrinkItemsCount);
    }
    @Test
    public void testIsDrinkMenuItemAdded() {
        Restaurant r = new Restaurant();
        int sizeBeforeAdding = r.menu.getDrinkItems().get(DrinkType.NONALCOHOLIC).size();
        Drink drink = new Drink("Pepsi", 3.50, DrinkType.NONALCOHOLIC, 250);
        r.menu.addDrinkItem(drink);
        int sizeAfterAdding = r.menu.getDrinkItems().get(DrinkType.NONALCOHOLIC).size();
        assertEquals(sizeBeforeAdding, sizeAfterAdding - 1);
    }

    @Test
    public void testIsDishMenuItemAdded() {
        Restaurant r = new Restaurant();
        int sizeBeforeAdding = r.menu.getDishItems().get(DishType.GRILL).size();
        Dish dish = new Dish("Pork steak", 7.50, DishType.GRILL, 300);
        r.menu.addDishItem(dish);
        int sizeAfterAdding = r.menu.getDishItems().get(DishType.GRILL).size();
        assertEquals(sizeBeforeAdding, sizeAfterAdding - 1);
    }

    @Test
    public void testIsDishMenuItemDeleted() {
        Restaurant r = new Restaurant();
        int sizeBeforeDeleting = r.menu.getDishItems().get(DishType.SOUP).size();
        r.menu.deleteDishItemByNumber(1);
        int sizeAfterDeleting = r.menu.getDishItems().get(DishType.SOUP).size();
        assertEquals(sizeBeforeDeleting, sizeAfterDeleting + 1);
    }

    @Test
    public void testIsDishMenuItemNotDeleted() {
        Restaurant r = new Restaurant();
        int sizeBeforeDeleting = r.menu.getDishItems().get(DishType.SOUP).size();
        r.menu.deleteDishItemByNumber(1000);
        int sizeAfterDeleting = r.menu.getDishItems().get(DishType.SOUP).size();
        assertEquals(sizeBeforeDeleting, sizeAfterDeleting);
    }

    @Test
    public void testIsDrinkMenuItemDeleted() {
        Restaurant r = new Restaurant();
        int sizeBeforeDeleting = r.menu.getDrinkItems().get(DrinkType.HOT).size();
        r.menu.deleteDrinkItemByNumber(1);
        int sizeAfterDeleting = r.menu.getDrinkItems().get(DrinkType.HOT).size();
        assertEquals(sizeBeforeDeleting, sizeAfterDeleting + 1);
    }

    @Test
    public void testIsDrinkMenuItemIsNotDeleted() {
        Restaurant r = new Restaurant();
        int sizeBeforeDeleting = r.menu.getDrinkItems().get(DrinkType.HOT).size();
        r.menu.deleteDrinkItemByNumber(1000);
        int sizeAfterDeleting = r.menu.getDrinkItems().get(DrinkType.HOT).size();
        assertEquals(sizeBeforeDeleting, sizeAfterDeleting);
    }

    @Test
    public void testIsGetDishItemByNumberCorrect() {
        Restaurant r = new Restaurant();
        Dish checkedDish = r.menu.getDishItems().get(DishType.SOUP).get(0);
        Dish expectedDish = r.menu.getDishItemByNumber(1);

        assertEquals(checkedDish, expectedDish);
    }
    @Test
    public void testIsGetDishItemByNumberNotCorrect() {
        Restaurant r = new Restaurant();
        Dish expectedDish = r.menu.getDishItemByNumber(1000);
        assertNull(expectedDish);
    }
    @Test
    public void testIsGetDrinkItemByNumberCorrect() {
        Restaurant r = new Restaurant();
        Drink checkedDrink = r.menu.getDrinkItems().get(DrinkType.HOT).get(0);
        Drink expectedDrink = r.menu.getDrinkItemByNumber(1);

        assertEquals(checkedDrink, expectedDrink);
    }
    @Test
    public void testIsGetDrinkItemByNumberNotCorrect() {
        Restaurant r = new Restaurant();
        Drink expectedDrink = r.menu.getDrinkItemByNumber(1000);
        assertNull(expectedDrink);
    }
    @Test
    public void testSaveMenuToFile() {
        Menu menu = new Menu("NewMenu.csv");
        menu.saveMenuToFile("NewMenu.csv");
    }
    @Test
    public void testPrintMenu() {
        Menu menu = new Menu("NewMenu.csv");
        menu.printMenu();
    }
}

