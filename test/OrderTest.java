import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    public void testIsGetTableNumberWorksCorrect() {
        Restaurant r = new Restaurant();
        int tableNumber = r.orderList.orders.get(0).getTableNumber();
        assertEquals(tableNumber, 1);
    }
    @Test
    public void testIsChangeStatusWaiterWorksCorrect() {
        Restaurant r = new Restaurant();

        r.orderList.orders.get(0).changeStatusWaiter();
        OrderStatus actualValue = r.orderList.orders.get(0).getStatus();
        assertEquals(actualValue, OrderStatus.TAKEN);
    }
}
