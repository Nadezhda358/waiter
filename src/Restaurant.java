import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Restaurant {
    String menuFileName = "Menu.csv";
    String orderListFileName = "Orders.csv";
    String usersInfoFileName = "usersInfo.txt";
    private int tablesCount;
    public Menu menu;
    public OrderList orderList;
    public ArrayList<User> users = new ArrayList<>();

    public Restaurant() {
        menu = new Menu(menuFileName);
        orderList = new OrderList(orderListFileName, menu);
        setUsers();
        setTablesCount();
    }

    public void setTablesCount() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(orderListFileName));
            while ((reader.readLine()) != null){
                this.tablesCount++;
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getTablesCount() {
        return tablesCount;
    }

    public void setUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(usersInfoFileName));
            String line;
            while ((line = reader.readLine()) != null){
                addUser(line, this);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void addUser(String userInfo, Restaurant restaurant){
        String[] splitUserInfo = userInfo.split(",");
        switch (splitUserInfo[2]) {
            case "cook" -> restaurant.users.add(new Cook(splitUserInfo[0], splitUserInfo[1], Role.COOK));
            case "waiter" -> restaurant.users.add(new Waiter(splitUserInfo[0], splitUserInfo[1], Role.WAITER));
        }
    }
}

