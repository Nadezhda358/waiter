import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    public void testIsGetTableNumberWorksCorrect() {
        Restaurant r = new Restaurant();
        int expectedTableNumber = r.orderList.orders.get(0).getTableNumber();
        assertEquals(expectedTableNumber, 1);
    }
    @Test
    public void testIsChangedStatusWaiterTaking() {
        Restaurant r = new Restaurant();
        r.orderList.orders.get(0).setStatus(OrderStatus.TAKING);
        r.orderList.orders.get(0).changeStatusWaiter();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        OrderStatus expectedlValue = OrderStatus.TAKEN;
        assertEquals(expectedlValue, actualValue);
    }
    @Test
    public void testIsChangedStatusWaiterCooked() {
        Restaurant r = new Restaurant();
        r.orderList.orders.get(0).setStatus(OrderStatus.COOKED);
        r.orderList.orders.get(0).changeStatusWaiter();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        OrderStatus expectedValue = OrderStatus.SERVED;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testIsChangedStatusWaiterServed() {
        Restaurant r = new Restaurant();
        r.orderList.orders.get(0).setStatus(OrderStatus.SERVED);
        r.orderList.orders.get(0).changeStatusWaiter();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        OrderStatus expectedValue = OrderStatus.PAYED;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testIsChangedStatusWaiterPayed() {
        Restaurant r = new Restaurant();
        r.orderList.orders.get(0).setStatus(OrderStatus.PAYED);
        r.orderList.orders.get(0).changeStatusWaiter();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        OrderStatus expectedValue = OrderStatus.PAYED;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testIsChangedStatusWaiterCooking() {
        Restaurant r = new Restaurant();
        r.orderList.orders.get(0).setStatus(OrderStatus.COOKING);
        r.orderList.orders.get(0).changeStatusWaiter();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        OrderStatus expectedValue = OrderStatus.COOKING;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testIsChangedStatusCookCooking() {
        Restaurant r = new Restaurant();
        r.orderList.orders.get(0).setStatus(OrderStatus.COOKING);
        r.orderList.orders.get(0).changeStatusCook();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        OrderStatus expectedValue = OrderStatus.COOKED;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testIsChangedStatusCookTaken() {
        Restaurant r = new Restaurant();
        r.orderList.orders.get(0).setStatus(OrderStatus.TAKEN);
        r.orderList.orders.get(0).changeStatusCook();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        OrderStatus expectedValue = OrderStatus.COOKING;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testIsChangedStatusCookServed() {
        Restaurant r = new Restaurant();
        r.orderList.orders.get(0).setStatus(OrderStatus.SERVED);
        r.orderList.orders.get(0).changeStatusCook();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        OrderStatus expectedValue = OrderStatus.SERVED;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testIsChangedStatusCookPayed() {
        Restaurant r = new Restaurant();
        r.orderList.orders.get(0).setStatus(OrderStatus.PAYED);
        r.orderList.orders.get(0).changeStatusCook();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        OrderStatus expectedValue = OrderStatus.PAYED;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testIsDeleteOrderItemForMoreThenOneCount() {
        Menu menu = new Menu("NewMenu.csv");
        OrderList orderList = new OrderList("NewOrders.csv",menu);
        int actualValue = orderList.orders.get(0).getOrderedItems().get(0).getCount();

        orderList.orders.get(0).deleteOrderedItem(1);

        int expectedValue = orderList.orders.get(0).getOrderedItems().get(0).getCount() + 1;
        assertEquals(expectedValue, actualValue);
    }
    @Test
    public void testIsDeleteOrderItemForOneCount() {
        Menu menu = new Menu("NewMenu.csv");
        OrderList orderList = new OrderList("NewOrders.csv",menu);
        int actualValue = orderList.orders.get(2).getOrderedItems().size();
        orderList.orders.get(2).deleteOrderedItem(1);
        int expectedValue = orderList.orders.get(2).getOrderedItems().size()+1;
        assertEquals(expectedValue, actualValue);
    }
}
