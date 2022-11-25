import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Restaurant {
    String menuFileName = "Menu.csv";
    String orderListFileName = "Orders.csv";
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
            BufferedReader reader = new BufferedReader(new FileReader("usersInfo.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                String[] splitLine = line.split(",");
                switch (splitLine[2]){
                    case "waiter": users.add(new Waiter(splitLine[0], splitLine[1], Role.WAITER));break;
                    case "cook": users.add(new Cook(splitLine[0], splitLine[1], Role.COOK));break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

