import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private List<OrderItem> orderedItems;
    //private int table;
    private LocalDateTime dateOfOrder;
    private OrderStatus status;

    public Order() {
        this.orderedItems = new ArrayList<>();
        this.dateOfOrder = LocalDateTime.now();
        this.status = OrderStatus.TAKING;
    }

    public void addOrderedItem(OrderItem orderItem){
        this.orderedItems.add(orderItem);
    }
    public void printOrder(){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDate = this.dateOfOrder.format(formatDate);
        System.out.println("\n--- O R D E R ---\n"+formattedDate);
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

