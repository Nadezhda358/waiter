import java.util.Scanner;

public abstract class User implements Displayable{
    private final String username;
    private final String password;
    private final Role role;
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public static int readNumber(String message) {
        Scanner scan = new Scanner(System.in);
        boolean isNumber;
        int number = 0;
        do {
            System.out.print(message);
            if (scan.hasNextInt()) {
                number = scan.nextInt();
                isNumber = true;
            } else {
                System.out.println("Invalid input.");
                isNumber = false;
                scan.next();
            }
        } while (!isNumber);
        return number;
    }
}
