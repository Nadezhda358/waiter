import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    public static void printStartMenu(Restaurant restaurant) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Sign in\n2. Login\n3. Exit.");
        System.out.print("Enter your choice(1, 2 or 3): ");
        String choice = scan.next();
        switch (choice) {
            case "1":
                signUp(getUserInfo());
                printStartMenu(restaurant);
                break;
            case "2": login(getUserInfo(), restaurant);break;
            case "3": return;
            default:
                System.out.println("Invalid input. Try again!");
                printStartMenu(restaurant);
        }
    }
    public static String getUserInfo() {
        String role, name, password;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your role (cook or waiter): ");
        role = scan.next();//TODO check if such role exists
        System.out.print("Enter your name: ");
        name = scan.next();
        System.out.print("Enter your password: ");
        password = scan.next();
        return name + "," + password + "," + role.toUpperCase();
    }
    public static void signUp(String userInfo){
        try {
            FileWriter myWriter = new FileWriter("usersInfo.txt", true);
            myWriter.append(userInfo).append("\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void login(String userInfo, Restaurant restaurant){
        boolean doesUserExist = false;
        for (int i = 0; i < restaurant.users.size(); i++) {
            String restaurantWorkerInfo = restaurant.users.get(i).getUsername() + "," + restaurant.users.get(i).getPassword() + "," + restaurant.users.get(i).getRole().toString();
            if (restaurantWorkerInfo.equals(userInfo)){
                restaurant.users.get(i).display(restaurant);
                doesUserExist = true;
            }
        }
        if (!doesUserExist){
            System.out.println("There is no such user! Try again.");
            printStartMenu(restaurant);
        }
    }
}
