import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private List<OrderItem> orderedItems;
    private int tableNumber;
    private LocalDateTime dateOfOrder;
    private OrderStatus status;

    public void setDateOfOrder(LocalDateTime dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }
    public void setStatusServed() {
        this.status = OrderStatus.SERVED;
    }
    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        this.orderedItems = new ArrayList<>();
        this.dateOfOrder = LocalDateTime.now();
        this.status = OrderStatus.PAYED;
    }

    public List<OrderItem> getOrderedItems() {
        return orderedItems;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public LocalDateTime getDateOfOrder() {
        return dateOfOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addOrderedItem(OrderItem orderItem){
        boolean isOrderItemExist = false;
        for (OrderItem item: this.orderedItems) {
            if (item.getItem() == orderItem.getItem()) {
                item.setCount(item.getCount()+orderItem.getCount());
                isOrderItemExist = true;
                break;
            }
        }
        if (!isOrderItemExist) {
            this.orderedItems.add(orderItem);
        }

    }
    public void printOrder(){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDate = this.dateOfOrder.format(formatDate);
        System.out.println("\n--- O R D E R --- in Table "+this.tableNumber+" Created on "+formattedDate);
        System.out.println("---------------------------------");

        for (OrderItem item: this.orderedItems) {
            System.out.println(item);
        }
        System.out.println("---------------------------------");

    }
    @Override
    public String toString() {
        return "Order{" +
                "orderedItems=" + orderedItems +
                ", dateOfOrder=" + dateOfOrder +
                ", status=" + status +
                '}';
    }
}

