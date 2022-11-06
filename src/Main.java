public class Main {
    public static void main(String[] args) {

        String fileName = "Menu.csv";
        Menu menu = new Menu(fileName);
        menu.printMenu();

        Dish d = menu.getDishItems().get(DishType.SOUP).get(1);
        Drink d1 = menu.getDrinkItems().get(DrinkType.NONALCOHOLIC).get(0);

        Order newOrder = new Order();
        OrderItem newOrderItem1 = new OrderItem(d,2);
        OrderItem newOrderItem2 = new OrderItem(d1,1);
        newOrder.addOrderedItem(newOrderItem1);
        newOrder.addOrderedItem(newOrderItem2);
        newOrder.printOrder();



    }
}