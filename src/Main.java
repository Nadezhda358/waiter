import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Restaurant restaurant = new Restaurant();
        restaurant.menu.printMenu();


        //How to get Dish by number in Dish menu
        Dish seventhDish = restaurant.menu.getDishItemByNumber(7);
        System.out.println(seventhDish);

        //How to delete Dish by number in Dish menu
        restaurant.menu.deleteDishItemByNumber(7);

        //How to get Drink by number in Drink menu
        Drink fifthDrink = restaurant.menu.getDrinkItemByNumber(5);
        System.out.println(fifthDrink);

        //How to delete Drink by number in Drink menu
        restaurant.menu.deleteDrinkItemByNumber(5);

        restaurant.menu.printMenu();

    }
}