
public class Main {
    public static void main(String[] args){

        Restaurant restaurant = new Restaurant();



        //Checks if save methods works correctly
        //restaurant.orderList.saveOrderListToFile("Orders.csv",restaurant.menu);
        //restaurant.menu.saveMenuToFile("Menu.csv");
        Login.printStartMenu(restaurant);

        ////restaurant.menu.printMenu();
//
        //System.out.println("\n--------------Waiter Order List To View--------------");
        //restaurant.orderList.PrintOrderList();
//
        //System.out.println("\n--------------Waiter Order List To Change--------------");
        //restaurant.orderList.PrintWaiterOrderListToChangeStatus();
//
        //System.out.println("\n--------------Cook Order List--------------");
        //restaurant.orderList.PrintCookOrderList();
//
        ////Prints Order Bil for table number
        //int tableNumber = 3;
        //restaurant.orderList.orders.get(tableNumber-1).printOrderBill();
    }
}