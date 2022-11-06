import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Menu {
    private EnumMap<DishType, ArrayList<Dish>> dishItems;
    private EnumMap<DrinkType, ArrayList<Drink>> drinkItems;

    public EnumMap<DishType, ArrayList<Dish>> getDishItems() {
        return dishItems;
    }

    public void setDishItems(EnumMap<DishType, ArrayList<Dish>> dishItems) {
        this.dishItems = dishItems;
    }

    public EnumMap<DrinkType, ArrayList<Drink>> getDrinkItems() {
        return drinkItems;
    }

    public void setDrinkItems(EnumMap<DrinkType, ArrayList<Drink>> drinkItems) {
        this.drinkItems = drinkItems;
    }

    public Menu() {
        this.dishItems = new EnumMap<>(DishType.class);
        this.drinkItems = new EnumMap<>(DrinkType.class);
    }


    public Menu(String fileName) {
        this.dishItems = new EnumMap<>(DishType.class);
        this.drinkItems = new EnumMap<>(DrinkType.class);
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
                Dish dishItem = new Dish(name, price, type, weight);
                addDishItem(dishItem);
                menuRow = fileReader.nextLine();
            }
            while (fileReader.hasNextLine()) {
                menuRow = fileReader.nextLine();
                String[] line = menuRow.split(",");
                DrinkType type = DrinkType.valueOf(line[0]);
                String name = line[1];
                double price = Double.parseDouble(line[2]);
                int volumeMl = Integer.parseInt(line[3]);
                Drink drinkItem = new Drink(name, price, type, volumeMl);
                addDrinkItem(drinkItem);
            }
            fileReader.close();

        } catch (Exception e) {
            System.out.println("Menu items file " + fileName + " not found!");
        }
    }

    public void printMenu() {
        System.out.println("\n-------- M E N U --------\n");
        printDishItems();
        printDrinkItems();
    }


    public void printDrinkItems() {
        System.out.println("\n***** DRINKS *****");

        for (Map.Entry<DrinkType, ArrayList<Drink>> drinkItem : this.drinkItems.entrySet()) {
            System.out.println("\t" + drinkItem.getKey() + " DRINKS");

            for (int i = 0; i < drinkItem.getValue().size(); i++) {
                System.out.println((i + 1) + ". " + drinkItem.getValue().get(i));
            }
        }
    }

    public void printDishItems() {
        System.out.println("\n***** DISHES *****");

        for (Map.Entry<DishType, ArrayList<Dish>> dishItem : this.dishItems.entrySet()) {
            System.out.println("\t" + dishItem.getKey() + "S");

            for (int i = 0; i < dishItem.getValue().size(); i++) {
                System.out.println((i + 1) + ". " + dishItem.getValue().get(i));
            }
        }
    }

    public void sortMenuItems() {
        for (Map.Entry<DishType, ArrayList<Dish>> dishItem : this.dishItems.entrySet()) {
            dishItem.getValue().sort(Comparator
                    .comparing(MenuItem::getName)
                    .thenComparing(MenuItem::getPrice));
        }
        for (Map.Entry<DrinkType, ArrayList<Drink>> drinkItem : this.drinkItems.entrySet()) {
            drinkItem.getValue().sort(Comparator
                    .comparing(MenuItem::getName)
                    .thenComparing(MenuItem::getPrice));
        }
    }

    public void addDishItem(Dish dishToAdd) {
        this.dishItems.computeIfAbsent(dishToAdd.getDishType(), k -> new ArrayList<>()).add(dishToAdd);
    }

    public void addDrinkItem(Drink drinkToAdd) {
        this.drinkItems.computeIfAbsent(drinkToAdd.getDrinkType(), k -> new ArrayList<>()).add(drinkToAdd);
    }

    public void deleteDishItem(Dish dishToDelete) {
        this.dishItems.get(dishToDelete.getDishType()).remove(dishToDelete);
    }

    public void deleteDrinkItem(Drink drinkToDelete) {
        this.drinkItems.get(drinkToDelete.getDrinkType()).remove(drinkToDelete);
    }

    public void saveMenuToFile(String fileName) throws FileNotFoundException {
        this.sortMenuItems();
        PrintStream fileWriter = new PrintStream(fileName);
        for (Map.Entry<DishType, ArrayList<Dish>> dishItem : this.dishItems.entrySet()) {
            for (int i = 0; i < dishItem.getValue().size(); i++) {
                fileWriter.println(dishItem.getKey() + ","
                        + dishItem.getValue().get(i).getName() + "," +
                        dishItem.getValue().get(i).getPrice() + "," +
                        dishItem.getValue().get(i).getWeightInGrams());
            }
        }
        fileWriter.println("Drinks");
        for (Map.Entry<DrinkType, ArrayList<Drink>> drinkItem : this.drinkItems.entrySet()) {
            for (int i = 0; i < drinkItem.getValue().size(); i++) {
                fileWriter.println(drinkItem.getKey() + ","
                        + drinkItem.getValue().get(i).getName() + "," +
                        drinkItem.getValue().get(i).getPrice() + "," +
                        drinkItem.getValue().get(i).getVolumeInMl());
            }
        }
        fileWriter.close();

    }

    @Override
    public String toString() {
        return "MENU\n" +
                this.dishItems.keySet() + "\n" + this.dishItems +
                '}';
    }
}
