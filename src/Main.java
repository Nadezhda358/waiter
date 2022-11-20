import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){

        //Restaurant restaurant = new Restaurant();

        //How to print dish types and select a type by number
        DishType.printDishTypes();//Print types to select number from waiter
        int selectedDish = 1;//Choice from waiter
        DishType dishType = DishType.getDishTypeByNumber(selectedDish);
        System.out.println(dishType);

        DrinkType.printDrinkTypes();
        int selectedDrink = 2;
        DrinkType drinkType = DrinkType.getDrinkTypeByNumber(selectedDrink);
        System.out.println(drinkType);

        //Login.printStartMenu();
    }
}