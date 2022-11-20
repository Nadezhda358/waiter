import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){

        Restaurant restaurant = new Restaurant();
        restaurant.menu.printMenu();
        restaurant.orderList.PrintOrderList();
        restaurant.orderList.orders.get(3).printOrderBill();

        //Login.printStartMenu();
    }
}