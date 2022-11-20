
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
                //restaurant.orderList.PrintOrderList();
                printOrdersMenu(restaurant.menu);
                break;
            case 3:
                //Login.printStartMenu();break;
            default:
                System.out.println("Invalid input. Try again.\n");
                printWaiterMenu();
        }
    }
    public static void editMenu(Menu menu){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1.1 add new dish\n1.2 add new drink\n1.3 remove dish\n1.4 remove drink\n1.5 back");
        System.out.print("Enter your choice(1-5): ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:menu.addDishItem(readDish());
            menu.saveMenuToFile("Menu.csv");
            editMenu(menu);
            break;
            case 2: menu.addDrinkItem(readDrink());
            menu.saveMenuToFile("Menu.csv");
            editMenu(menu);
            break;
            case 3:System.out.print("Enter dish number: ");
                int dishNumber = scan.nextInt();
                menu.deleteDishItemByNumber(dishNumber);
                menu.saveMenuToFile("Menu.csv");
                editMenu(menu);
                break;
            case 4:System.out.print("Enter drink number: ");
                int drinkNumber = scan.nextInt();
                menu.deleteDrinkItemByNumber(drinkNumber);
                menu.saveMenuToFile("Menu.csv");
                editMenu(menu);
                break;
            case 5:
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
        DishType.printDishTypes();
        System.out.print("Enter the number of the dish type: ");
        int dishTypeNumber = scan.nextInt();
        DishType dishType = DishType.getDishTypeByNumber(dishTypeNumber);
        System.out.print("Enter dish weight in grams: ");
        int weight = scan.nextInt();
        return new Dish(name, price, dishType, weight);
    }
    public static Drink readDrink(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter drink name: ");
        String name = scan.nextLine();
        System.out.print("Enter drink price: ");
        double price = scan.nextDouble();
        DrinkType.printDrinkTypes();
        System.out.print("Enter the number of the drink type: ");
        int drinkTypeNumber = scan.nextInt();
        DrinkType drinkType = DrinkType.getDrinkTypeByNumber(drinkTypeNumber);
        System.out.print("Enter drink volume in ml: ");
        int ml = scan.nextInt();
        return new Drink(name, price, drinkType, ml);
    }
    public static void printOrdersMenu(Menu menu){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n2.1 add order\n2.2 edit order\n2.3 back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        switch (choice){
            case 1:printOrdersMenu(menu);
                System.out.print("Enter table number: ");
                int tableNumber = scan.nextInt();
                Order order = new Order(tableNumber);
                printOrdersMenu(menu); break;
            case 2:
                System.out.print("Witch order do you want to edit?\nEnter the number: " );
                int orderNumber = scan.nextInt();
                //editOrder();
                printOrdersMenu(menu); break;//TODO chose a table, check if it has an order and print the menu for editing it
            case 3:
                System.out.println();
                printWaiterMenu(); break;
            default:
                System.out.println("Invalid input. Try again.\n");
                printOrdersMenu(menu);
        }
    }
    public static void editOrder(Order order, Menu menu){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1 add dish to order\n2 add drink to order\n3 serve order\n4 playing the bill\n5 back");
        System.out.print("Enter your choice(1-5): ");
        int choice = scan.nextInt();
        switch (choice){
            case 1:
                menu.printDishItems();
                System.out.print("Enter the number of the dish: ");
                int dishNumber = scan.nextInt();
                System.out.println("Enter portions count: ");
                int portionsCount = scan.nextInt();
                order.addOrderedItem(new OrderItem(menu.getDishItemByNumber(dishNumber), portionsCount));
            case 2:
                menu.printDrinkItems();
                System.out.print("Enter the number of the drink: ");
                int drinkNumber = scan.nextInt();
                System.out.println("Enter count: ");
                int drinkCount = scan.nextInt();
                order.addOrderedItem(new OrderItem(menu.getDrinkItemByNumber(drinkNumber), drinkCount));
                break;
            case 3:break;
            case 4:break;
            case 5:printOrdersMenu(menu);break;
        default:
                System.out.println("Invalid input. Try again.\n");
                editOrder(order, menu);
        }
    }
}
