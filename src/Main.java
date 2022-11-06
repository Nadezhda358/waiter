public class Main {
    public static void main(String[] args) {

        String fileName = "Menu.csv";
        Menu menu = new Menu(fileName);
        menu.printMenu();
        //Checks deleteDishItem() and deleteDrinkItem() do works
        Dish d = menu.getDishItems().get(DishType.SOUP).get(1);
        System.out.println(d);
        menu.deleteDishItem(d);
        Drink d1 = menu.getDrinkItems().get(DrinkType.NONALCOHOLIC).get(0);
        System.out.println(d1);
        menu.deleteDrinkItem(d1);

        //Checks addDishItem(), addDrinkItem(), sortMenuItems() and saveMenuToFile() do works
        Dish newDish = new Dish("Tomato soup", 2.55, DishType.SOUP, 450);
        System.out.println(newDish);
        menu.addDishItem(newDish);
        Drink newDrink = new Drink("L�monade", 1.99, DrinkType.NONALCOHOLIC, 450);
        System.out.println(newDrink);
        menu.addDrinkItem(newDrink);
        menu.sortMenuItems();
        menu.printMenu();

        try {
            menu.saveMenuToFile("NewMenu.csv");
        }catch (Exception e){
            System.out.println("File not found!");
        }
    }
}