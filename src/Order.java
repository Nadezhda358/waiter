import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private List<OrderItem> orderedItems;
    private final int tableNumber;
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

    public void deleteOrderedItem(int numberOfOrderedItem) {
        if (orderedItems.get(numberOfOrderedItem - 1).getCount() == 1) {
            orderedItems.remove(numberOfOrderedItem - 1);
        } else {
            orderedItems.get(numberOfOrderedItem - 1).setCount(orderedItems.get(numberOfOrderedItem - 1).getCount() - 1);
        }
    }

    public void printOrder() {
        printOrderHeader();
        printOrderBody();
    }

    private void printOrderBody() {
        double totalOrderSum = 0;
        int orderItemNumber = 0;
        for (OrderItem item : this.orderedItems) {
            orderItemNumber++;
            double itemSum = item.getCount() * item.getItem().price;
            totalOrderSum += itemSum;
            System.out.println(orderItemNumber + ". " + item);
        }
        System.out.println("----------------------------------------");
        System.out.printf("%-15sOrder's total: %.2f lv.\n", "", totalOrderSum);
    }

    private void printOrderHeader() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDate = this.dateOfOrder.format(formatDate);

        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";

        LocalDateTime startDate = LocalDateTime.parse(formattedDate, formatDate);
        LocalDateTime endDate = LocalDateTime.parse(LocalDateTime.now().format(formatDate),formatDate);
        if (isDifferenceInTimeMoreThen30Minutes(startDate,endDate) && this.status != OrderStatus.SERVED) {
            System.out.println("\n"+ANSI_RED+"-- O R D E R -- in Table " + this.tableNumber + " is " + this.getStatus() +ANSI_RESET+ "\n" + ANSI_RED + "Created on " + formattedDate +" - NOT served in "+getDifferenceInMinutes(startDate,endDate)+" minutes" + ANSI_RESET);
        }else
        {
            System.out.println("\n-- O R D E R -- in Table " + this.tableNumber + " is " + this.getStatus() + "\n"  + "Created on " + formattedDate);
        }
        System.out.println("----------------------------------------");
    }

    private boolean isDifferenceInTimeMoreThen30Minutes(LocalDateTime start_date, LocalDateTime end_date) {
        Duration diff = Duration.between(start_date, end_date);
        return diff.toMinutes() > 30;
    }
    private long getDifferenceInMinutes(LocalDateTime start_date, LocalDateTime end_date) {
        return Duration.between(start_date, end_date).toMinutes();
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
        System.out.printf("%-40sTotal: %.2f lv.\n", "", totalOrderSum);
    }

    public void changeStatusWaiter() {
        if (getStatus().equals(OrderStatus.PAYED)) {
            System.out.println("There is no order from that table.");
        } else if (getStatus().equals(OrderStatus.TAKING) && orderedItems.size() > 0) {
            setStatus(OrderStatus.TAKEN);
            System.out.println("The order is taken.");
        } else if (getStatus().equals(OrderStatus.COOKED)) {
            setStatus(OrderStatus.SERVED);
            System.out.println("The order is served.");
        } else if (getStatus().equals(OrderStatus.SERVED)) {
            setStatus(OrderStatus.PAYED);
            printOrderBill();
            this.orderedItems = new ArrayList<>();
        } else {
            System.out.println("You can't change the status of that order.");
        }
    }

    public void changeStatusCook() {
        if (getStatus().equals(OrderStatus.PAYED)) {
            System.out.println("There is no order from that table.");
        } else if (getStatus().equals(OrderStatus.TAKEN)) {
            setStatus(OrderStatus.COOKING);
            System.out.println("The order is cooking.");
        } else if (getStatus().equals(OrderStatus.COOKING)) {
            setStatus(OrderStatus.COOKED);
            System.out.println("The order is cooked.");
        } else {
            System.out.println("You can't change the status of that order.");
        }
    }
}