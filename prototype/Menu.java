import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
public class Menu {

    public static void displayLoginPage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ROBOTIX");
        System.out.println("[1] Login");
        System.out.println("[2] Create Account");
        String value = scanner.nextLine();
        switch (value) {
            case "1":
                System.out.println("Enter Username:");
                String username = scanner.nextLine();
                System.out.println("Enter Password:");
                String password = scanner.nextLine();

                if (LoginController.login(username,password,Database.getAllUsers())){
                    System.out.println("welcome to the homepage");
                } else {
                    System.out.println("wrong  password please try again");
                    displayLoginPage();
                }
                break;
            case "2":

                AdminController.createAccount();
                
                break;
            default:
                break;
        }

    }

    
}
