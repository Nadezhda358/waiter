import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login implements Loggable{
    public static void printStartMenu(Restaurant restaurant) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Sign in\n2. Login\n3. Exit.");
        System.out.print("Enter your choice(1, 2 or 3): ");
        String choice = scan.next();
        switch (choice) {
            case "1":
                signUp(getUserInfo(), restaurant);
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
        while (true){
            System.out.print("Enter your role (" + Role.printRoles() + "): ");
            role = scan.next();
            if (isRoleValid(role)){
                break;
            }
            System.out.println("There is no such role. Try again.");
        }
        System.out.print("Enter your name: ");
        name = scan.next();
        System.out.print("Enter your password: ");
        password = scan.next();
        return name + "," + password + "," + role.toLowerCase();
    }
    public static boolean isRoleValid(String role){
        Role[] roles = Role.values();
        for (Role value : roles) {
            if (value.toString().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }
    public static void signUp(String userInfo, Restaurant restaurant){
        if (!isNameTaken(userInfo, restaurant)) {
            try {
                FileWriter myWriter = new FileWriter(restaurant.usersInfoFileName, true);
                myWriter.append(userInfo).append("\n");
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            restaurant.addUser(userInfo, restaurant);
            //restaurant.setUsers();
        }else {
            System.out.println("That username is already taken. Try again.");
            signUp(getUserInfo(), restaurant);
        }
    }
    public static boolean isNameTaken(String userInfo, Restaurant restaurant){
        String[] splitUserInfo = userInfo.split(",");
        for (int i = 0; i < restaurant.users.size(); i++){
            if (splitUserInfo[0].equals(restaurant.users.get(i).getUsername()) && splitUserInfo[2].equalsIgnoreCase(restaurant.users.get(i).getRole().toString())){
                return true;
            }
        }
        return false;
    }
    public static void login(String userInfo, Restaurant restaurant){
        boolean doesUserExist = false;
        for (int i = 0; i < restaurant.users.size(); i++) {
            String restaurantWorkerInfo = restaurant.users.get(i).getUsername() + "," + restaurant.users.get(i).getPassword() + "," + restaurant.users.get(i).getRole().toString().toLowerCase();
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
