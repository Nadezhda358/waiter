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

    public void addOrderedItem(OrderItem orderItem) {
        boolean isOrderItemExist = false;
        for (OrderItem item : this.orderedItems) {
            if (item.getItem() == orderItem.getItem()) {
                item.setCount(item.getCount() + orderItem.getCount());
                isOrderItemExist = true;
                break;
            }
        }
        if (!isOrderItemExist) {
            this.orderedItems.add(orderItem);
        }

    }

    public void printOrder() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDate = this.dateOfOrder.format(formatDate);
        System.out.println("\n-- O R D E R -- in Table " + this.tableNumber + " is " + this.getStatus() + "\nCreated on " + formattedDate);
        System.out.println("-------------------------------------");

        for (OrderItem item : this.orderedItems) {
            System.out.println(item);
        }
        System.out.println("-------------------------------------");

    }

    public void printOrderBill() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDate = this.dateOfOrder.format(formatDate);
        System.out.println("\n--- ORDER BILL--- to Table " + this.tableNumber + " is " + this.getStatus() + "\nCreated on " + formattedDate);
        System.out.println("--------------------------------------------------------");
        double totalOrderSum = 0;
        for (OrderItem item : this.orderedItems) {
            double itemSum = item.getCount() * item.getItem().price;
            System.out.printf("%-40s\t->\t%.2f lv.\n", item, itemSum);
            totalOrderSum += itemSum;
        }
        System.out.println("--------------------------------------------------------");
        String emptyString = "";
        System.out.printf("%-40sTotal: %.2f lv.\n", emptyString, totalOrderSum);

    }
    public void changeStatusWaiter(){
        if (getStatus().equals(OrderStatus.PAYED)){
            System.out.println("There is no order from that table.");
        }else if (getStatus().equals(OrderStatus.TAKING)){
            setStatus(OrderStatus.TAKEN);
            System.out.println("The order is taken. It can't be changed anymore.");
        }else if (getStatus().equals(OrderStatus.COOKED)){
            setStatus(OrderStatus.SERVED);
            System.out.println("The order is served.");
        }else if(getStatus().equals(OrderStatus.SERVED)){
            setStatus(OrderStatus.PAYED);
            printOrderBill();
            this.orderedItems = new ArrayList<>();

        }else{
            System.out.println("You can't change the status of that order.");
        }
    }
    public void changeStatusCook(){
        if (getStatus().equals(OrderStatus.PAYED)){
            System.out.println("There is no order from that table.");
        }else if (getStatus().equals(OrderStatus.TAKEN)){
            setStatus(OrderStatus.COOKING);
            System.out.println("The order is cooking.");
        }else if(getStatus().equals(OrderStatus.COOKING)){
            setStatus(OrderStatus.COOKED);
            System.out.println("The order is cooked.");
        }else{
            System.out.println("You can't change the status of that order.");
        }
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

