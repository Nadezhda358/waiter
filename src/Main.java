import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){

        Restaurant restaurant = new Restaurant();
        //restaurant.menu.printMenu();

        System.out.println("\n--------------Waiter Order List To View--------------");
        restaurant.orderList.PrintOrderList();

        System.out.println("\n--------------Waiter Order List To Change--------------");
        restaurant.orderList.PrintWaiterOrderListToChangeStatus();

        System.out.println("\n--------------Cook Order List--------------");
        restaurant.orderList.PrintCookOrderList();

        //Prints Order Bil for table number
        int tableNumber = 3;
        restaurant.orderList.orders.get(tableNumber-1).printOrderBill();

        //Login.printStartMenu();
    }
}