package Views;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import Controllers.AdminController;
import Controllers.LoginController;
import Database.Database;
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
                    displayHomePage();
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

    public static void displayHomePage(){
        System.out.println("Welcome to ROBOTIX");
        System.out.println("[1] ManageProfile");
        System.out.println("[2] ManageWallet");
        System.out.println("[3] ManageOrders");
        System.out.println("[4] ManageActivities");
        System.out.println("[5] MyRobotFleet");
        System.out.println("[6] MyInventory");
        System.out.println("[7] Logout");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        switch (value) {
            case "1":
                System.out.println("Profile");
                break;
            case "2":
                System.out.println("Orders");
                break;
            case "3":
                System.out.println("Activities");
                break;
            case "4":
                displayLoginPage();
                break;
            default:
                break;
        }


    }
    
}
