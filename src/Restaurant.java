public class Restaurant {
    String menuFileName = "Menu.csv";
    String orderListFileName = "Orders.csv";
    public Menu menu = new Menu(menuFileName);;
    public OrderList orderList = new OrderList(orderListFileName,menu);
    
    }

