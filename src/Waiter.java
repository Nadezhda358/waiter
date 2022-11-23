import java.io.FileNotFoundException;
import java.util.Scanner;

public class Waiter extends User{
    public Waiter(String username, String password, Role role) {
        super(username, password, role);
    }

    @Override
    public void display(Restaurant restaurant) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Menu\n2. Orders\n3. Back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1 -> {
                restaurant.menu.printMenu();
                editMenu(restaurant);
            }
            case 2 -> {
                restaurant.orderList.PrintOrderList();
                try {
                    printOrdersMenu(restaurant);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            case 3 -> Login.printStartMenu(restaurant);
            default -> {
                System.out.println("Invalid input. Try again.\n");
                display(restaurant);
            }
        }
    }
    public void editMenu(Restaurant restaurant){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1.1 add new dish\n1.2 add new drink\n1.3 remove dish\n1.4 remove drink\n1.5 back");
        System.out.print("Enter your choice(1-5): ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1 -> {
                restaurant.menu.addDishItem(readDish());
                restaurant.menu.saveMenuToFile(restaurant.menuFileName);
                editMenu(restaurant);
            }
            case 2 -> {
                restaurant.menu.addDrinkItem(readDrink());
                restaurant.menu.saveMenuToFile(restaurant.menuFileName);
                editMenu(restaurant);
            }
            case 3 -> {
                System.out.print("Enter dish number: ");
                int dishNumber = scan.nextInt();
                restaurant.menu.deleteDishItemByNumber(Math.abs(dishNumber));
                restaurant.menu.saveMenuToFile(restaurant.menuFileName);
                editMenu(restaurant);
            }
            case 4 -> {
                System.out.print("Enter drink number: ");
                int drinkNumber = scan.nextInt();
                restaurant.menu.deleteDrinkItemByNumber(Math.abs(drinkNumber));
                restaurant.menu.saveMenuToFile(restaurant.menuFileName);
                editMenu(restaurant);
            }
            case 5 -> {
                System.out.println();
                display(restaurant);
            }
            default -> {
                System.out.println("Invalid input. Try again.\n");
                editMenu(restaurant);
            }
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
    public void printOrdersMenu(Restaurant restaurant) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n2.1 add order\n2.2 edit order\n2.3 back");
        System.out.print("Enter your choice(1-3): ");
        int choice = scan.nextInt();
        int tableNumber;
        switch (choice) {
            case 1 -> {
                System.out.print("Enter table number: ");
                tableNumber = scan.nextInt();
                if (tableNumber <= restaurant.getTablesCount()) {
                    if (restaurant.orderList.orders.get(tableNumber - 1).getStatus() == OrderStatus.PAYED) {
                        restaurant.orderList.orders.get(tableNumber - 1).setStatus(OrderStatus.TAKING);
                        restaurant.orderList.saveOrderListToFile(restaurant.orderListFileName, restaurant.menu);
                        editOrder(restaurant, (Math.abs(tableNumber) - 1));
                    } else {
                        System.out.println("That table is already taken.");
                        printOrdersMenu(restaurant);
                    }
                } else {
                    System.out.println("There is no such table.");
                    printOrdersMenu(restaurant);
                }
            }
            //printOrdersMenu(restaurant); break;
            case 2 -> {
                System.out.print("Witch table's order do you want to edit?\nEnter the number: ");
                tableNumber = scan.nextInt();
                if (tableNumber <= restaurant.getTablesCount()) {
                    if (restaurant.orderList.orders.get(tableNumber - 1).getStatus() != OrderStatus.PAYED) {
                        editOrder(restaurant, (tableNumber - 1));
                    }else {
                        System.out.println("There is no order from that table.");
                        printOrdersMenu(restaurant);
                    }
                }else{
                    System.out.println("There is no such table.");
                    printOrdersMenu(restaurant);
                }
            }
            case 3 -> display(restaurant);
            default -> {
                System.out.println("Invalid input. Try again.\n");
                printOrdersMenu(restaurant);
            }
        }
    }
    public void editOrder(Restaurant restaurant, int orderNumber) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1 add dish to order\n2 add drink to order\n3 delete item from order\n4 change status\n5 back");
        System.out.print("Enter your choice(1-5): ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1 -> {
                if (restaurant.orderList.orders.get(orderNumber).getStatus().equals(OrderStatus.TAKING)) {
                    restaurant.menu.printDishItems();
                    System.out.print("Enter the number of the dish: ");
                    int dishNumber = scan.nextInt();
                    System.out.print("Enter portions count: ");
                    int portionsCount = scan.nextInt();
                    restaurant.orderList.orders.get(orderNumber).addOrderedItem(new OrderItem(restaurant.menu.getDishItemByNumber(dishNumber), portionsCount));
                    restaurant.orderList.saveOrderListToFile(restaurant.orderListFileName, restaurant.menu);
                    editOrder(restaurant, orderNumber);
                }else{
                    System.out.println("The order is already taken. You can't add dishes to it.");
                    editOrder(restaurant, orderNumber);
                }
            }
            case 2 -> {
                if (restaurant.orderList.orders.get(orderNumber).getStatus().equals(OrderStatus.TAKING)) {
                    restaurant.menu.printDrinkItems();
                    System.out.print("Enter the number of the drink: ");
                    int drinkNumber = scan.nextInt();
                    System.out.print("Enter count: ");
                    int drinkCount = scan.nextInt();
                    restaurant.orderList.orders.get(orderNumber).addOrderedItem(new OrderItem(restaurant.menu.getDrinkItemByNumber(drinkNumber), drinkCount));
                    restaurant.orderList.saveOrderListToFile(restaurant.orderListFileName, restaurant.menu);
                    editOrder(restaurant, orderNumber);
                }else{
                    System.out.println("The order is already taken. You can't add drinks to it.");
                    editOrder(restaurant, orderNumber);
                }
            }
            case 3 ->{
                if (restaurant.orderList.orders.get(orderNumber).getStatus().equals(OrderStatus.TAKING)) {
                    System.out.print("Enter the number of the item you want to remove: ");
                    int itemNumber = scan.nextInt();
                    restaurant.orderList.orders.get(orderNumber).deleteOrderedItem(itemNumber);
                    restaurant.orderList.saveOrderListToFile(restaurant.orderListFileName, restaurant.menu);
                    editOrder(restaurant, orderNumber);
                }else{
                    System.out.println("The order is already taken. You can't remove items from it.");
                    editOrder(restaurant, orderNumber);
                }
            }
            case 4 -> {
                //restaurant.orderList.PrintWaiterOrderListToChangeStatus();
                restaurant.orderList.orders.get(orderNumber).changeStatusWaiter();
                restaurant.orderList.saveOrderListToFile(restaurant.orderListFileName, restaurant.menu);
                editOrder(restaurant, orderNumber);
            }
            case 5 -> printOrdersMenu(restaurant);
            default -> {
                System.out.println("Invalid input. Try again.\n");
                editOrder(restaurant, orderNumber);
            }
        }
    }
}
