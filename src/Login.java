import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    public static void printStartMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Sign in\n2. Login\n3. Exit.");
        System.out.print("Enter your choice(1, 2 or 3): ");
        String choice = scan.next();
        switch (choice) {
            case "1":
                signUp(getUserInfo());
                printStartMenu();
                break;
            case "2": login(getUserInfo());break;
            case "3": return;
            default:
                System.out.println("Invalid input. Try again!");
                printStartMenu();
        }
    }
    public static String getUserInfo() {
        String role, name, password;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your role (cook or waiter): ");
        role = scan.next(); //TODO check if such role exists
        System.out.print("Enter your name: ");
        name = scan.next();
        System.out.print("Enter your password: ");
        password = scan.next();
        return name + "," + password + "," + role;
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
    public static void login(String userInfo){
        boolean doesUserExist = false;
        File usersInfo = new File("usersInfo.txt");
        String[] splitUserInfo = userInfo.split(",");
        try {
            Scanner fileReader = new Scanner(usersInfo, "windows-1251");
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                if (line.equalsIgnoreCase(userInfo)){
                    if (Role.WAITER.toString().equalsIgnoreCase(splitUserInfo[2])){
                        System.out.println("You logged in successfully.");
                        //TODO cook display
                    }else if (Role.COOK.toString().equalsIgnoreCase(splitUserInfo[2])){
                        System.out.println("You logged in successfully.");
                        //TODO cook display
                    }
                    doesUserExist = true;
                }
            }
            if (!doesUserExist){
                System.out.println("There is no such user. Try again.");
                printStartMenu();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
