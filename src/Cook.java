import java.util.Scanner;

public class Cook extends User{
    public Cook(String username, String password, Role role) {
        super(username, password, role);
    }

    @Override
    public void display(Restaurant restaurant) {
        //DisplayCook.printCookMenu();
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Active orders\n2. Change order status\n3. Back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        switch (choice){
            case 1:
                System.out.println("\nTAKING ORDERS:");
                restaurant.orderList.PrintOrderList(OrderStatus.TAKING);
                System.out.println("\nCOOKING ORDERS:");
                restaurant.orderList.PrintOrderList(OrderStatus.COOKING);
                break;
            case 2:
                //TODO change status
                break;
            case 3:
                Login.printStartMenu(restaurant);break;
            default:
                System.out.println("Invalid input. Try again.\n");
                this.display(restaurant);
        }
    }
}
