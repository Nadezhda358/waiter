import java.io.File;
import java.util.*;

public class Menu {
private HashMap<DishType,ArrayList<MenuItem>> menuItems;

    public Menu() {
        this.menuItems = new HashMap<DishType ,ArrayList<MenuItem>>();

    }

    public HashMap<DishType,ArrayList<MenuItem>> getMenuItems() {
        return this.menuItems;
    }

    public Menu(String fileName) {
        //ArrayList<MenuItem> menuItems = new ArrayList<>();
        List<MenuItem> menuItems = new ArrayList<>();
        this.menuItems = new HashMap<DishType,ArrayList<MenuItem>>();
        try {
            File menuItemsFile = new File(fileName);
            Scanner fileReader = new Scanner(menuItemsFile, "windows-1251");
            while (fileReader.hasNextLine()) {
                String[] line = fileReader.nextLine().split(",");
                DishType type = DishType.valueOf(line[0]);
                double price = Double.parseDouble(line[2]);
                int weight = Integer.parseInt(line[3]);
                String name = line[1];
                MenuItem menuItem = new Dish(name, price, type,weight );
                this.menuItems.computeIfAbsent(type,k -> new ArrayList<>()).add(menuItem);
            }
            fileReader.close();

        } catch (Exception e) {
            System.out.println("Menu items file " + fileName + " not found!");
        }
    }
    public void printMenu(){
        for (Map.Entry<DishType,ArrayList<MenuItem>> item:this.menuItems.entrySet() ) {

            System.out.println(item.toString());
        }
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuItems=" + menuItems +
                '}';
    }
}
