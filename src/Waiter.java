import java.io.FileNotFoundException;
import java.util.Scanner;

public class Waiter extends User{
    public Waiter(String username, String password, Role role) {
        super(username, password, role);
    }

    @Override
    public void display(Restaurant restaurant) {
        System.out.println("1. Menu\n2. Orders\n3. Back");
        int choice = readNumber("Enter your choice(1-3): ");
        switch (choice) {
            case 1 -> editMenu(restaurant);
            case 2 -> {
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
        System.out.println("\n1. Print menu\n2. Add new dish\n3. Add new drink\n4. Remove dish\n5. Remove drink\n6. Back");
        int choice = readNumber("Enter your choice(1-6): ");
        switch (choice) {
            case 1 -> {
                restaurant.menu.printMenu();
                editMenu(restaurant);
            }
            case 2 -> {
                restaurant.menu.addDishItem(readDish());
                restaurant.menu.saveMenuToFile(restaurant.menuFileName);
                editMenu(restaurant);
            }
            case 3 -> {
                restaurant.menu.addDrinkItem(readDrink());
                restaurant.menu.saveMenuToFile(restaurant.menuFileName);
                editMenu(restaurant);
            }
            case 4 -> {
                int dishNumber = readNumber("Enter dish number: ");
                restaurant.menu.deleteDishItemByNumber(dishNumber);
                restaurant.menu.saveMenuToFile(restaurant.menuFileName);
                editMenu(restaurant);
            }
            case 5 -> {
                int drinkNumber = readNumber("Enter drink number: ");
                restaurant.menu.deleteDrinkItemByNumber(drinkNumber);
                restaurant.menu.saveMenuToFile(restaurant.menuFileName);
                editMenu(restaurant);
            }
            case 6 -> {
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
        double price = readNumber("Enter dish price: ");
        DishType.printDishTypes();
        int dishTypeNumber;
        while (true){
            dishTypeNumber = readNumber("Enter the number of the dish type: ");
            if (dishTypeNumber <= DishType.values().length && dishTypeNumber > 0){
                break;
            }
            System.out.println("Invalid input. Try again.");
        }
        DishType dishType = DishType.getDishTypeByNumber(dishTypeNumber);
        int weight = readNumber("Enter dish weight in grams: ");
        return new Dish(name, price, dishType, weight);
    }
    public static Drink readDrink(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter drink name: ");
        String name = scan.nextLine();
        double price = readNumber("Enter drink price: ");
        DrinkType.printDrinkTypes();
        int drinkTypeNumber;
        while (true){
            drinkTypeNumber = readNumber("Enter the number of the drink type: ");
            if (drinkTypeNumber <= DrinkType.values().length && drinkTypeNumber > 0){
                break;
            }
            System.out.println("Invalid input. Try again.");
        }
        DrinkType drinkType = DrinkType.getDrinkTypeByNumber(drinkTypeNumber);
        int ml = readNumber("Enter drink volume in ml: ");
        return new Drink(name, price, drinkType, ml);
    }
    public void printOrdersMenu(Restaurant restaurant) throws FileNotFoundException {
        System.out.println("\n1. Print all orders\n2. Print orders, which status you can change\n3. Add order\n4. Edit order\n5. Back");
        int choice = readNumber("Enter your choice(1-5): ");
        int tableNumber;
        switch (choice) {
            case 1 -> {
                restaurant.orderList.PrintOrderList();
                printOrdersMenu(restaurant);
            }
            case 2 -> {
                restaurant.orderList.PrintWaiterOrderListToChangeStatus();
                printOrdersMenu(restaurant);}
            case 3 -> {
                tableNumber = readNumber("Enter table number: ");
                if (tableNumber <= restaurant.getTablesCount() && tableNumber > 0) {
                    if (restaurant.orderList.orders.get(tableNumber - 1).getStatus() == OrderStatus.PAYED) {
                        restaurant.orderList.orders.get(tableNumber - 1).setStatus(OrderStatus.TAKING);
                        restaurant.orderList.saveOrderListToFile(restaurant.orderListFileName, restaurant.menu);
                        editOrder(restaurant, (tableNumber - 1));
                    } else {
                        System.out.println("That table is already taken.");
                        printOrdersMenu(restaurant);
                    }
                } else {
                    System.out.println("There is no such table.");
                    printOrdersMenu(restaurant);
                }
            }
            case 4 -> {
                System.out.print("Witch table's order do you want to edit?");
                tableNumber = readNumber("\nEnter the number: ");
                if (tableNumber <= restaurant.getTablesCount() && tableNumber > 0) {
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
            case 5 -> display(restaurant);
            default -> {
                System.out.println("Invalid input. Try again.\n");
                printOrdersMenu(restaurant);
            }
        }
    }
    public void editOrder(Restaurant restaurant, int orderNumber) throws FileNotFoundException {
        System.out.println("\n1. Add dish to order\n2. Add drink to order\n3. Delete item from order\n4. Change status\n5. Back");
        int choice = readNumber("Enter your choice(1-5): ");
        switch (choice) {
            case 1 -> {
                if (restaurant.orderList.orders.get(orderNumber).getStatus().equals(OrderStatus.TAKING)) {
                    restaurant.menu.printDishItems();
                    int dishNumber = readNumber("Enter the number of the dish: ");
                    int portionsCount = readNumber("Enter portions count: ");
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
                    int drinkNumber = readNumber("Enter the number of the drink: ");
                    int drinkCount = readNumber("Enter count: ");
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
                    int itemNumber;
                    while (true){
                        itemNumber = readNumber("Enter the number of the item you want to remove: ");
                        if (itemNumber <= restaurant.orderList.orders.get(orderNumber).getOrderedItems().size()){
                            break;
                        }
                        System.out.println("There is no such item.");
                    }
                    restaurant.orderList.orders.get(orderNumber).deleteOrderedItem(itemNumber);
                    System.out.println("The item was deleted.");
                    restaurant.orderList.saveOrderListToFile(restaurant.orderListFileName, restaurant.menu);
                    editOrder(restaurant, orderNumber);
                }else{
                    System.out.println("The order is already taken. You can't remove items from it.");
                    editOrder(restaurant, orderNumber);
                }
            }
            case 4 -> {
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
