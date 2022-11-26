import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class OrderListTest {
    @Test
    public void testSaveOrderListToFile() throws FileNotFoundException {
        Menu menu = new Menu("NewMenu.csv");
        OrderList orderList = new OrderList("NewOrders.csv",menu);
        orderList.SaveOrderListToFile("NewOrders.csv",menu);
    }
    @Test
    public void testPrintCookOrderList(){
        Menu menu = new Menu("NewMenu.csv");
        OrderList orderList = new OrderList("NewOrders.csv",menu);
        orderList.PrintCookOrderList();
    }
    @Test
    public void testPrintOrderList(){
        Menu menu = new Menu("NewMenu.csv");
        OrderList orderList = new OrderList("NewOrders.csv",menu);
        orderList.PrintOrderList();
    }
    @Test
    public void testPrintWaiterOrderListToChangeStatus(){
        Menu menu = new Menu("NewMenu.csv");
        OrderList orderList = new OrderList("NewOrders.csv",menu);
        orderList.PrintWaiterOrderListToChangeStatus();
    }
}
