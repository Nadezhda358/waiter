import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Restaurant restaurant = new Restaurant();


        //Checks if save methods works correctly
        restaurant.orderList.saveOrderListToFile("Orders.csv",restaurant.menu);
        restaurant.menu.saveMenuToFile("Menu.csv");
        //Login.printStartMenu();
    }
}