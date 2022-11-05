import java.io.File;
import java.util.*;

public class Menu {
    private EnumMap<DishType, ArrayList<MenuItem>> dishItems;
    private EnumMap<DrinkType, ArrayList<MenuItem>> drinkItems;

    public Menu() {
        this.dishItems = new EnumMap<DishType, ArrayList<MenuItem>>(DishType.class);
        this.drinkItems = new EnumMap<DrinkType, ArrayList<MenuItem>>(DrinkType.class);
    }



    public Menu(String fileName) {
        this.dishItems = new EnumMap<DishType, ArrayList<MenuItem>>(DishType.class);
        this.drinkItems = new EnumMap<DrinkType, ArrayList<MenuItem>>(DrinkType.class);
        try {
            File menuItemsFile = new File(fileName);
            Scanner fileReader = new Scanner(menuItemsFile, "windows-1251");
            String menuRow = fileReader.nextLine();
            while (!menuRow.equals("Drinks")) {
                String[] line = menuRow.split(",");
                DishType type = DishType.valueOf(line[0]);
                String name = line[1];
                double price = Double.parseDouble(line[2]);
                int weight = Integer.parseInt(line[3]);
                MenuItem menuItem = new Dish(name, price, type, weight);
                this.dishItems.computeIfAbsent(type, k -> new ArrayList<>()).add(menuItem);
                menuRow = fileReader.nextLine();
            }
            menuRow = fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String[] line = menuRow.split(",");
                DrinkType type = DrinkType.valueOf(line[0]);
                String name = line[1];
                double price = Double.parseDouble(line[2]);
                int volumeMl = Integer.parseInt(line[3]);
                MenuItem menuItem = new Drink(name, price, type, volumeMl);
                this.drinkItems.computeIfAbsent(type, k -> new ArrayList<>()).add(menuItem);
                menuRow = fileReader.nextLine();
            }
            fileReader.close();

        } catch (Exception e) {
            System.out.println("Menu items file " + fileName + " not found!");
        }
    }

    public void printMenu() {
        System.out.println("\n-------- M E N U --------\n\n***** DISHES *****");
        for (Map.Entry<DishType, ArrayList<MenuItem>> m : this.dishItems.entrySet()) {
            System.out.println("\t"+m.getKey() + "S");

            for (int i = 0; i < m.getValue().size(); i++) {
                System.out.println((i + 1) + ". " + m.getValue().get(i));
            }
        }
        System.out.println("\n***** DRINKS *****");
        for (Map.Entry<DrinkType, ArrayList<MenuItem>> m : this.drinkItems.entrySet()) {
            System.out.println("\t"+m.getKey() + " DRINKS");

            for (int i = 0; i < m.getValue().size(); i++) {
                System.out.println((i + 1) + ". " + m.getValue().get(i));
            }
        }
    }

    @Override
    public String toString() {
        return "MENU\n" +
                this.dishItems.keySet() + "\n" + this.dishItems +
                '}';
    }
}
