import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
private List<MenuItem> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<MenuItem>();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Menu(String fileName) {
        this.menuItems = new ArrayList<MenuItem>();
        try {
            File menuItemsFile = new File(fileName);
            Scanner fileReader = new Scanner(menuItemsFile, "windows-1251");
            while (fileReader.hasNextLine()) {
                String[] line = fileReader.nextLine().split(",");
                DishType type = DishType.valueOf(line[0]);
                double price = Double.parseDouble(line[2]);
                int weight = Integer.parseInt(line[3]);
                MenuItem menuItem = new Dish(line[1], price, type,weight );
                menuItems.add(menuItem);
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Menu items file " + fileName + " not found!");
        }
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuItems=" + menuItems +
                '}';
    }
}
