import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderList {
    public List<Order> orders = new ArrayList<>();

    public OrderList(String fileName, Menu menu) {
        try {
            File ordersFile = new File(fileName);
            Scanner fileReader = new Scanner(ordersFile, "windows-1251");
            while (fileReader.hasNextLine()) {
                String orderRow = fileReader.nextLine();
                String[] orderRowArray = orderRow.split(",");
                Order order = getOrder(menu, orderRowArray);
                this.orders.add(order);
            }
            fileReader.close();

        } catch (Exception e) {
            System.out.println("Order list file " + fileName + " not found!");
        }
    }

    private Order getOrder(Menu menu, String[] orderRowArray) {
        Order order = new Order(Integer.parseInt(orderRowArray[0]));
        OrderStatus status = OrderStatus.valueOf(orderRowArray[1]);
        order.setStatus(OrderStatus.valueOf(orderRowArray[1]));
        if (status != OrderStatus.PAYED) {
            order.setStatus(status);
            order.setDateOfOrder(LocalDateTime.parse(orderRowArray[2], DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")));
            setOrderItems(menu, orderRowArray, order);
        }
        return order;
    }

    private void setOrderItems(Menu menu, String[] orderRowArray, Order order) {
        int numOfOrderItems = (orderRowArray.length - 3) / 4;
        for (int i = 0; i < numOfOrderItems; i++) {
            if (orderRowArray[3 + i * 4].equals("Dish")) {
                MenuItem menuItem = menu
                        .getDishItems()
                        .get(DishType.valueOf(orderRowArray[4 + i * 4]))
                        .get(Integer.parseInt(orderRowArray[5 + i * 4]));
                OrderItem orderItem = new OrderItem(menuItem, Integer.parseInt(orderRowArray[6 + i * 4]));
                order.addOrderedItem(orderItem);
            } else if (orderRowArray[3 + i * 4].equals("Drink")) {
                MenuItem menuItem = menu
                        .getDrinkItems().get(DrinkType.valueOf(orderRowArray[4 + i * 4]))
                        .get(Integer.parseInt(orderRowArray[5 + i * 4]));
                OrderItem orderItem = new OrderItem(menuItem, Integer.parseInt(orderRowArray[6 + i * 4]));
                order.addOrderedItem(orderItem);
            }
        }
    }

    public void printOrderList() {
        for (Order order : this.orders) {
            if (order.getStatus() != OrderStatus.PAYED) {
                order.printOrder();
            }
        }
    }
    public void printWaiterOrderList() {
        for (Order order : this.orders) {
            if (order.getStatus() != OrderStatus.PAYED &&
                    order.getStatus() != OrderStatus.COOKING &&
                    order.getStatus() != OrderStatus.TAKEN) {
                order.printOrder();
            }
        }
    }
    public void printCookOrderList() {
        for (Order order : this.orders) {
            if (order.getStatus() != OrderStatus.PAYED &&
                    order.getStatus() != OrderStatus.TAKING &&
                    order.getStatus() != OrderStatus.SERVED &&
            order.getStatus() != OrderStatus.COOKED)
            {
                order.printOrder();
            }
        }
    }

    public void saveOrderListToFile(String fileName, Menu menu) throws FileNotFoundException {
        PrintStream fileWriter = new PrintStream(fileName);
        for (Order order : this.orders) {
            if (order.getStatus() == OrderStatus.PAYED) {
                fileWriter.print(order.getTableNumber() + "," + order.getStatus().name() + "\n");
            } else {
                fileWriter.print(order.getTableNumber() + "," + order.getStatus().name() + "," + order.getDateOfOrder().format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")));
                saveOrderItems(menu, fileWriter, order);
                fileWriter.print("\n");
            }
        }
        fileWriter.close();
    }

    private void saveOrderItems(Menu menu, PrintStream fileWriter, Order order) {
        for (OrderItem orderItem : order.getOrderedItems()) {
            if (orderItem.getItem() instanceof Dish) {
                fileWriter.print(",Dish," + ((Dish) orderItem.getItem()).getDishType().name() + "," + menu.getDishItems().get(((Dish) orderItem.getItem()).getDishType()).indexOf((Dish) orderItem.getItem()));
            } else if (orderItem.getItem() instanceof Drink) {
                fileWriter.print(",Drink," + ((Drink) orderItem.getItem()).getDrinkType().name() + "," + menu.getDrinkItems().get(((Drink) orderItem.getItem()).getDrinkType()).indexOf((Drink) orderItem.getItem()));
            }
            fileWriter.print("," + orderItem.getCount());
        }
    }
}