import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Restaurant restaurant = new Restaurant();
        restaurant.menu.printMenu();
        restaurant.orderList.PrintOrderList();

        //Testing if order in table not exists
        int tableNumber =1;
        Dish d = restaurant.menu.getDishItems().get(DishType.SOUP).get(1);
        Drink d1 = restaurant.menu.getDrinkItems().get(DrinkType.NONALCOHOLIC).get(0);

        Order currentOrder = restaurant.orderList.orders.get(tableNumber-1);
        if (currentOrder.getStatus()==OrderStatus.PAYED){
            currentOrder.setStatus(OrderStatus.TAKING);
        }
        currentOrder.addOrderedItem(new OrderItem(d,2));
        currentOrder.addOrderedItem(new OrderItem(d1,1));
        currentOrder.addOrderedItem(new OrderItem(d1,1));

        //Testing if order in table exists
        tableNumber =3;
        currentOrder = restaurant.orderList.orders.get(tableNumber-1);
        if (currentOrder.getStatus()==OrderStatus.PAYED){
            currentOrder.setStatus(OrderStatus.TAKING);
        }
        currentOrder.addOrderedItem(new OrderItem(d,2));
        currentOrder.addOrderedItem(new OrderItem(d1,1));
        currentOrder.addOrderedItem(new OrderItem(d1,1));
        currentOrder.printOrderBill();
        restaurant.orderList.saveOrderListToFile("NewOrders.csv",restaurant.menu);
        //restaurant.orderList.PrintOrderList();

    }
}