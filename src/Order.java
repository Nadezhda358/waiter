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

    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        this.orderedItems = new ArrayList<>();
        this.dateOfOrder = LocalDateTime.now();
        this.status = OrderStatus.PAYED;
    }

    public void addOrderedItem(OrderItem orderItem){
        this.orderedItems.add(orderItem);

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

