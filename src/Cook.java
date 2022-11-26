import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cook extends User{
    public Cook(String username, String password, Role role) {
        super(username, password, role);
    }

    @Override
    public void display(Restaurant restaurant) {
        System.out.println("1. Active orders\n2. Change order status\n3. Back");
        int choice = readNumber("Enter your choice(1-3): ");
        switch (choice) {
            case 1 -> {
                restaurant.orderList.PrintCookOrderList();
                display(restaurant);
            }
            case 2 -> {
                System.out.print("Witch table's order status do you want to edit?");
                int tableNumber = readNumber("\nEnter the number: ");
                if (tableNumber <= restaurant.getTablesCount() && tableNumber > 0) {
                    restaurant.orderList.orders.get(Math.abs(tableNumber) - 1).changeStatusCook();
                }else{
                    System.out.println("There is no such table.");
                }
                try {
                    restaurant.orderList.saveOrderListToFile(restaurant.orderListFileName, restaurant.menu);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                display(restaurant);
            }
            case 3 -> Login.printStartMenu(restaurant);
            default -> {
                System.out.println("Invalid input. Try again.\n");
                this.display(restaurant);
            }
        }
    }
}
