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
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1. Print menu\n2. Add new dish\n3. Add new drink\n4. Remove dish\n5. Remove drink\n6. Back");
        System.out.print("Enter your choice(1-6): ");
        int choice = scan.nextInt();
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
                System.out.print("Enter dish number: ");
                int dishNumber = scan.nextInt();
                restaurant.menu.deleteDishItemByNumber(Math.abs(dishNumber));
                restaurant.menu.saveMenuToFile(restaurant.menuFileName);
                editMenu(restaurant);
            }
            case 5 -> {
                System.out.print("Enter drink number: ");
                int drinkNumber = scan.nextInt();
                restaurant.menu.deleteDrinkItemByNumber(Math.abs(drinkNumber));
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
        System.out.print("Enter dish price: ");
        double price = scan.nextDouble();
        DishType.printDishTypes();
        int dishTypeNumber;
        while (true){
            System.out.print("Enter the number of the dish type: ");
            dishTypeNumber = scan.nextInt();
            if (dishTypeNumber <= DishType.values().length){
                break;
            }
            System.out.println("Invalid input. Try again.");
        }
        DishType dishType = DishType.getDishTypeByNumber(Math.abs(dishTypeNumber));
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
        int drinkTypeNumber;
        while (true){
            System.out.print("Enter the number of the drink type: ");
            drinkTypeNumber = scan.nextInt();
            if (drinkTypeNumber <= DrinkType.values().length){
                break;
            }
            System.out.println("Invalid input. Try again.");
        }
        DrinkType drinkType = DrinkType.getDrinkTypeByNumber(Math.abs(drinkTypeNumber));
        System.out.print("Enter drink volume in ml: ");
        int ml = scan.nextInt();
        return new Drink(name, price, drinkType, ml);
    }
    public void printOrdersMenu(Restaurant restaurant) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1. Print all orders\n2. Print orders, which status you can change\n3. Add order\n4. Edit order\n5. Back");
        System.out.print("Enter your choice(1-5): ");
        int choice = scan.nextInt();
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
            case 4 -> {
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
            case 5 -> display(restaurant);
            default -> {
                System.out.println("Invalid input. Try again.\n");
                printOrdersMenu(restaurant);
            }
        }
    }
    public void editOrder(Restaurant restaurant, int orderNumber) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n1. Add dish to order\n2. Add drink to order\n3. Delete item from order\n4. Change status\n5. Back");
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
                    int itemNumber;
                    while (true){
                        System.out.print("Enter the number of the item you want to remove: ");
                        itemNumber = scan.nextInt();
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
