import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Menu implements Deletable, Addable, Gettable{
    private final EnumMap<DishType, ArrayList<Dish>> dishItems;
    private final EnumMap<DrinkType, ArrayList<Drink>> drinkItems;

    public EnumMap<DishType, ArrayList<Dish>> getDishItems() {
        return dishItems;
    }


    public EnumMap<DrinkType, ArrayList<Drink>> getDrinkItems() {
        return drinkItems;
    }


    public Menu(String fileName) {
        this.dishItems = new EnumMap<>(DishType.class);
        this.drinkItems = new EnumMap<>(DrinkType.class);
        try {
            File menuItemsFile = new File(fileName);
            Scanner fileReader = new Scanner(menuItemsFile, "windows-1251");
            readDishes(fileReader);
            readDrinks(fileReader);
            fileReader.close();

        } catch (Exception e) {
            System.out.println("Menu items file " + fileName + " not found!");
        }
    }

    private void readDrinks(Scanner fileReader) {
        String menuRow;
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
    }

    private void readDishes(Scanner fileReader) {
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
    }

    public void printMenu() {
        System.out.println("\n-------- M E N U --------");
        printDishItems();
        printDrinkItems();
    }


    public void printDrinkItems() {
        System.out.println("\n***** DRINKS *****");
        int currentNum = 0;
        for (Map.Entry<DrinkType, ArrayList<Drink>> drinkItem : this.drinkItems.entrySet()) {
            System.out.println("\t--" + drinkItem.getKey() + " drinks--");

            for (int i = 0; i < drinkItem.getValue().size(); i++) {
                currentNum++;
                System.out.println(currentNum + ". " + drinkItem.getValue().get(i));
            }
        }
    }

    public void printDishItems() {
        System.out.println("\n***** DISHES *****");
        int currentNum = 0;
        for (Map.Entry<DishType, ArrayList<Dish>> dishItem : this.dishItems.entrySet()) {
            System.out.println("\t--" + dishItem.getKey() + "--");

            for (int i = 0; i < dishItem.getValue().size(); i++) {
                currentNum++;
                System.out.println((currentNum) + ". " + dishItem.getValue().get(i));
            }
        }
    }

    private void sortMenuItems() {
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
    public void deleteDishItemByNumber(int dishNumber) {
        int dishCount=0;
        for (Map.Entry<DishType, ArrayList<Dish>> dishItem : this.dishItems.entrySet()) {
            dishCount += dishItem.getValue().size();
        }

        if (dishNumber <= dishCount && dishNumber > 0){
            Dish dishToDelete = getDishItemByNumber(dishNumber);
            this.dishItems.get(dishToDelete.getDishType()).remove(dishToDelete);
        }else {
            System.out.println("There is no such dish.");
        }
    }
    public Dish getDishItemByNumber(int dishNumber) {
        int currentNum = 0;
        for (Map.Entry<DishType, ArrayList<Dish>> dishItem : this.dishItems.entrySet()) {
            for (int i = 0; i < dishItem.getValue().size(); i++) {
                currentNum++;
                if (currentNum == dishNumber) {
                    return dishItem.getValue().get(i);
                }
            }
        }
        return null;
    }

    public void deleteDrinkItemByNumber(int drinkNumber) {
        int drinksCount=0;
        for (Map.Entry<DrinkType, ArrayList<Drink>> drinkItem : this.drinkItems.entrySet()) {
            drinksCount += drinkItem.getValue().size();
        }
        if (drinkNumber <= drinksCount && drinkNumber > 0){
            Drink drinkToDelete = getDrinkItemByNumber(drinkNumber);
            this.drinkItems.get(drinkToDelete.getDrinkType()).remove(drinkToDelete);
        }else{
            System.out.println("There is no such drink.");
        }
    }
    public Drink getDrinkItemByNumber(int drinkNumber) {
        int currentNum = 0;
        for (Map.Entry<DrinkType, ArrayList<Drink>> drinkItem : this.drinkItems.entrySet()) {
            for (int i = 0; i < drinkItem.getValue().size(); i++) {
                currentNum++;
                if (currentNum == drinkNumber) {
                    return drinkItem.getValue().get(i);
                }
            }
        }
        return null;
    }

    public void saveMenuToFile(String fileName){
        this.sortMenuItems();
        PrintStream fileWriter;
        try {
            fileWriter = new PrintStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        saveDishes(fileWriter);
        fileWriter.println("Drinks");
        saveDrinks(fileWriter);
        fileWriter.close();
    }

    private void saveDrinks(PrintStream fileWriter) {
        for (Map.Entry<DrinkType, ArrayList<Drink>> drinkItem : this.drinkItems.entrySet()) {
            for (int i = 0; i < drinkItem.getValue().size(); i++) {
                fileWriter.println(drinkItem.getKey().name() + ","
                        + drinkItem.getValue().get(i).getName() + "," +
                        drinkItem.getValue().get(i).getPrice() + "," +
                        drinkItem.getValue().get(i).getVolumeInMl());
            }
        }
    }

    private void saveDishes(PrintStream fileWriter) {
        for (Map.Entry<DishType, ArrayList<Dish>> dishItem : this.dishItems.entrySet()) {
            for (int i = 0; i < dishItem.getValue().size(); i++) {
                fileWriter.println(dishItem.getKey().name() + ","
                        + dishItem.getValue().get(i).getName() + "," +
                        dishItem.getValue().get(i).getPrice() + "," +
                        dishItem.getValue().get(i).getWeightInGrams());
            }
        }
    }
}
