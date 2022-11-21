import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Restaurant {
    String menuFileName = "Menu.csv";
    String orderListFileName = "Orders.csv";
    public Menu menu;
    public OrderList orderList;
    public ArrayList<User> users = new ArrayList<>();

    public Restaurant() {
        menu = new Menu(menuFileName);
        orderList = new OrderList(orderListFileName, menu);
        setUsers();
    }

    public void setUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("usersInfo.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                String[] splitLine = line.split(",");
                switch (splitLine[2]){
                    case "waiter": users.add(new Waiter(splitLine[0], splitLine[1], Role.WAITER));
                    case "cook": users.add(new Cook(splitLine[0], splitLine[1], Role.COOK));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

