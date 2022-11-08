import java.util.Scanner;

public class DisplayWaiter {
    public static void printWaiterMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Menu\n2. Active orders\n3. Back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        switch (choice){
            case 1:
                Menu menu = new Menu("Menu.csv");
                menu.printMenu();
                editMenu();
                break;
            case 2://TODO print active orders
                editOrders();
                break;
            case 3:
                Login.printStartMenu();break;
            default:
                System.out.println("Invalid input. Try again.\n");
                printWaiterMenu();
        }
    }
    public static void editMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1.1 add item\n1.2 remove item\n1.3 back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1://TODO method that adds item to the menu
                editMenu();
                break;
            case 2:
                //TODO method that removes item to the menu
                editMenu();
                break;
            case 3:
                System.out.println();
                printWaiterMenu();
                break;
            default:
                System.out.println("Invalid input. Try again.\n");
                editMenu();
        }
    }
    public static void editOrders(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n2.1 add order\n2.2 remove order\n2.3 current bill for order\n2.4 change status(served or payed)\n2.5 back");
        System.out.print("Enter your choice(1-4): ");
        int choice = scan.nextInt();
        switch (choice){
            case 1:editOrders(); break;//TODO method that adds order
            case 2:editOrders(); break;//TODO method that removes order
            case 3:editOrders(); break;//TODO method that prints current bill
            case 4:editOrders(); break;//TODO method that changes the status of the order
            case 5:
                System.out.println();
                printWaiterMenu(); break;
            default:
                System.out.println("Invalid input. Try again.\n");
                editOrders();
        }
    }
}
