package Views;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import javax.xml.crypto.Data;

import Controllers.ActivityController;
import Controllers.AdminController;
import Controllers.LoginController;
import Controllers.UserController;
import Database.Database;
import Models.User;
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
                User user = new User();
                
                if (LoginController.login(username,password,Database.getAllUsers()) >= 0){
                    user = Database.getAllUsers().get(LoginController.login(username,password,Database.getAllUsers()));
                    displayHomePage(user);
                } else {
                    System.out.println("wrong  password or username please try again");
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

    public static void displayHomePage(User user){
        System.out.println("Welcome to ROBOTIX " + user.getFirstName() +" "+  user.getLastName());

        System.out.println("[0] ManageProfile");
        System.out.println("[1] ManageWallet");
        System.out.println("[2] ManageOrders");
        System.out.println("[3] ManageActivities");
        System.out.println("[4] MyRobotFleet");
        System.out.println("[5] MyInventory");
        System.out.println("[6] Logout");

        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        
        switch (value) {
            case "0":
                UserMenu.displayManageProfile(user);
                scanner.close();
                break;
            case "1":

                break;
            case "2":

                break;
            case "3":
                ActivityMenu.displayManageActivities(user);
                scanner.close();
                break;
            case "4":
                RobotFleetMenu.displayManageRobotFleet(user);
                scanner.close();
                break;
            case "5":

                break;
            case "6":
                Menu.displayLoginPage();
                break;
            default:
                break;
        }


    }
    
}
