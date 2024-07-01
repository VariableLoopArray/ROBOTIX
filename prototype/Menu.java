import java.util.ArrayList;
import java.util.Scanner;
public class Menu {
    ArrayList<User> allUsers;
    ArrayList<Activity> allActivities;
    public Menu(ArrayList<User> allUsers, ArrayList<Activity> allActivities){
        this.allUsers = allUsers;
        this.allActivities = allActivities;
    }
    public void displayLoginPage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ROBOTIX");
        System.err.println("[1] Login");
        System.out.println("[2] Create Account");
        String value = scanner.nextLine();
        switch (value) {
            case "1":
                System.out.println("Enter Username:");
                String username = scanner.nextLine();
                System.out.println("Enter Password:");
                String password = scanner.nextLine();
                loginController.login(username,password,this.allUsers);
                break;
            case "2":
                System.out.println("")
                break;
            default:
                break;
        }

    }

    
}
