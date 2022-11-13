import java.util.Scanner;

public class DisplayCook {
    public static void printCookMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Active orders\n2. Change order status\n3. Back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        switch (choice){
            case 1://TODO print active orders
                break;
            case 2:
                //TODO change status
                break;
            case 3:
                Login.printStartMenu();break;
            default:
                System.out.println("Invalid input. Try again.\n");
                printCookMenu();
        }
    }
}
