import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    @Test
    public void testIsDrinkMenuItemAdded() {
        Restaurant r = new Restaurant();
        int sizeBeforeAdding = r.menu.getDrinkItems().get(DrinkType.NONALCOHOLIC).size();
        Drink drink = new Drink("Pepsi",3.50,DrinkType.NONALCOHOLIC,250);
        r.menu.addDrinkItem(drink);
        int sizeAfterAdding = r.menu.getDrinkItems().get(DrinkType.NONALCOHOLIC).size();
        assertEquals(sizeBeforeAdding,sizeAfterAdding-1);
    }

    @Test
    public void testIsDishMenuItemAdded() {
        Restaurant r = new Restaurant();
        int sizeBeforeAdding = r.menu.getDishItems().get(DishType.GRILL).size();
        Dish dish = new Dish("Pork steak",7.50,DishType.GRILL,300);
        r.menu.addDishItem(dish);
        int sizeAfterAdding = r.menu.getDishItems().get(DishType.GRILL).size();
        assertEquals(sizeBeforeAdding,sizeAfterAdding-1);
    }
    @Test
    public void testIsDishMenuItemDeleted() {
        Restaurant r = new Restaurant();
        int sizeBeforeDeleting = r.menu.getDishItems().get(DishType.SOUP).size();
        r.menu.deleteDishItemByNumber(1);
        int sizeAfterDeleting =  r.menu.getDishItems().get(DishType.SOUP).size();
        assertEquals(sizeBeforeDeleting,sizeAfterDeleting+1);
    }
    @Test
    public void testIsDishMenuItemNotDeleted() {
        Restaurant r = new Restaurant();
        int sizeBeforeDeleting = r.menu.getDishItems().get(DishType.SOUP).size();
        r.menu.deleteDishItemByNumber(1000);
        int sizeAfterDeleting =  r.menu.getDishItems().get(DishType.SOUP).size();
        assertEquals(sizeBeforeDeleting,sizeAfterDeleting);
    }
    @Test
    public void testIsDrinkMenuItemDeleted() {
        Restaurant r = new Restaurant();
        int sizeBeforeDeleting = r.menu.getDrinkItems().get(DrinkType.HOT).size();
        r.menu.deleteDrinkItemByNumber(1);
        int sizeAfterDeleting =  r.menu.getDrinkItems().get(DrinkType.HOT).size();
        assertEquals(sizeBeforeDeleting,sizeAfterDeleting+1);
    }
    @Test
    public void testIsDrinkMenuItemIsNotDeleted() {
        Restaurant r = new Restaurant();
        int sizeBeforeDeleting = r.menu.getDrinkItems().get(DrinkType.HOT).size();
        r.menu.deleteDrinkItemByNumber(1000);
        int sizeAfterDeleting =  r.menu.getDrinkItems().get(DrinkType.HOT).size();
        assertEquals(sizeBeforeDeleting,sizeAfterDeleting);
    }

}

