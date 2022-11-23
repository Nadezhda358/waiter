import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Restaurant restaurant = new Restaurant();
        Login.printStartMenu(restaurant);

        //Checks when deletes order item with more, then one counts
        //int table= 5;
        //Order currentOrder = restaurant.orderList.orders.get(table-1);
        //currentOrder.printOrder();
        //int orderItemToDelete = 4;
        ///*currentOrder.deleteOrderedItem(orderItemToDelete);
        //currentOrder.printOrder();*/
//
        ////Checks if deletes order item with one count
        //currentOrder.printOrder();
        //orderItemToDelete = 2;
        //currentOrder.deleteOrderedItem(orderItemToDelete);
        //currentOrder.printOrder();
    }
}