import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cook extends User{
    public Cook(String username, String password, Role role) {
        super(username, password, role);
    }

    @Override
    public void display(Restaurant restaurant) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Active orders\n2. Change order status\n3. Back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        switch (choice){
            case 1:
                restaurant.orderList.PrintCookOrderList();
                display(restaurant);
                break;
            case 2:
                System.out.print("Witch order's status do you want to edit?\nEnter the number: " );
                int orderNumber = scan.nextInt();
                restaurant.orderList.orders.get(orderNumber-1).changeStatusCook();
                try {
                    restaurant.orderList.saveOrderListToFile(restaurant.orderListFileName,restaurant.menu);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                display(restaurant);
                break;
            case 3:
                Login.printStartMenu(restaurant);break;
            default:
                System.out.println("Invalid input. Try again.\n");
                this.display(restaurant);
        }
    }
}
