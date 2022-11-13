import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;

public class OrderList {
    public List<Order> orders = new ArrayList<>();

    public OrderList(String fileName, Menu menu) {
        try {
            File ordersFile = new File(fileName);
            Scanner fileReader = new Scanner(ordersFile, "windows-1251");
            //String orderRow = fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String orderRow = fileReader.nextLine();
                String[] orderRowArray = orderRow.split(",");
                Order order = new Order(Integer.parseInt(orderRowArray[0]));
                OrderStatus status = OrderStatus.valueOf(orderRowArray[1]);
                if (status != OrderStatus.PAYED) {
                    order.setDateOfOrder(LocalDateTime.parse(orderRowArray[2],DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")));
                    int numOfOrderItems = (orderRowArray.length - 3) / 4;
                    for (int i = 0; i < numOfOrderItems; i++) {
                        if (orderRowArray[3 + i * 4].equals("Dish")) {
                            MenuItem menuItem = menu.getDishItems().get(DishType.valueOf(orderRowArray[4 + i * 4])).get(Integer.parseInt(orderRowArray[5 + i * 4]));
                            OrderItem orderItem = new OrderItem(menuItem, Integer.parseInt(orderRowArray[6 + i * 4]));
                            order.addOrderedItem(orderItem);
                        } else if (orderRowArray[3 + i * 4].equals("Drink")) {
                            MenuItem menuItem = menu.getDrinkItems().get(DrinkType.valueOf(orderRowArray[4 + i * 4])).get(Integer.parseInt(orderRowArray[5 + i * 4]));
                            OrderItem orderItem = new OrderItem(menuItem, Integer.parseInt(orderRowArray[6 + i * 4]));
                            order.addOrderedItem(orderItem);
                        }
                    }
                }
                this.orders.add(order);
            }
            fileReader.close();

        } catch (Exception e) {
            System.out.println("Order list file " + fileName + " not found!");
        }
    }
public void PrintOrderList(){
    for (Order order:this.orders) {
        order.printOrder();
    }
}
    @Override
    public String toString() {
        return "OrderList{" +
                "orders=" + orders +
                '}';
    }
}
