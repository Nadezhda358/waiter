public class Main {
    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant();
        restaurant.menu.printMenu();
        restaurant.orderList.PrintOrderList();

        Dish d = restaurant.menu.getDishItems().get(DishType.SOUP).get(1);
        Drink d1 = restaurant.menu.getDrinkItems().get(DrinkType.NONALCOHOLIC).get(0);

        Order newOrder = new Order(1);
        OrderItem newOrderItem1 = new OrderItem(d,2);
        OrderItem newOrderItem2 = new OrderItem(d1,1);
        newOrder.addOrderedItem(newOrderItem1);
        newOrder.addOrderedItem(newOrderItem2);
        newOrder.printOrder();
        restaurant.orderList.orders.add(newOrder);
        restaurant.orderList.PrintOrderList();

    }
}