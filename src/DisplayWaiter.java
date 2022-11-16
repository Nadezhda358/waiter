
import java.util.Scanner;

public class DisplayWaiter {
    public static void printWaiterMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Menu\n2. Orders\n3. Back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        Restaurant restaurant = new Restaurant();
        switch (choice){
            case 1:
                restaurant.menu.printMenu();
                editMenu(restaurant.menu);
                break;
            case 2:
                restaurant.orderList.PrintOrderList();
                printOrdersMenu();
                break;
            case 3:
                Login.printStartMenu();break;
            default:
                System.out.println("Invalid input. Try again.\n");
                printWaiterMenu();
        }
    }
    public static void editMenu(Menu menu){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1.1 add new dish\n1.2 add new drink\n1.3 remove item\n1.4 back");
        System.out.print("Enter your choice(1-4): ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                menu.addDishItem(readDish());
                menu.saveMenuToFile("Menu.csv");
                editMenu(menu);
            case 2:
                menu.addDrinkItem(readDrink());
                menu.saveMenuToFile("Menu.csv");
                editMenu(menu);
                break;
            case 3:
                //TODO method that removes item from the menu
                menu.deleteDishItem(readDish());
                menu.saveMenuToFile("Menu.csv");
                editMenu(menu);
                break;
            case 4:
                System.out.println();
                printWaiterMenu();
                break;
            default:
                System.out.println("Invalid input. Try again.\n");
                editMenu(menu);
        }
    }
    public static Dish readDish(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter dish name: ");
        String name = scan.nextLine();
        System.out.print("Enter dish price: ");
        double price = scan.nextDouble();
        System.out.print("Enter dish type: ");
        String dishType = scan.next();
        System.out.print("Enter dish weight in grams: ");
        int weight = scan.nextInt();
        return new Dish(name, price, DishType.valueOf(dishType.toUpperCase()), weight);
    }
    public static Drink readDrink(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter drink name: ");
        String name = scan.nextLine();
        System.out.print("Enter drink price: ");
        double price = scan.nextDouble();
        System.out.print("Enter drink type: ");
        String drinkType = scan.next();
        System.out.print("Enter drink volume in ml: ");
        int ml = scan.nextInt();
        return new Drink(name, price, DrinkType.valueOf(drinkType.toUpperCase()), ml);
    }
    public static void printOrdersMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n2.1 add order\n2.2 edit order\n2.3 back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        switch (choice){
            case 1:printOrdersMenu(); break;//TODO method that adds order
            case 2:editOrder(); printOrdersMenu(); break;//TODO chose a table, check if it has an order and print the menu for editing it
            case 3:
                System.out.println();
                printWaiterMenu(); break;
            default:
                System.out.println("Invalid input. Try again.\n");
                printOrdersMenu();
        }
    }
    public static void editOrder(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1 add dish to order\n2 add drink to order\n3 serve order\n4 playing the bill\n5 back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        switch (choice){
            case 1:break;
            case 2:break;
            case 3:break;
            case 4:break;
            case 5:printOrdersMenu();break;
        default:
                System.out.println("Invalid input. Try again.\n");
                editOrder();
        }
    }
}
