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
        System.out.println("[0] Login");
        System.out.println("[1] Create Account");
        System.out.println("[2] Exit");
        String value = scanner.nextLine();
        switch (value) {
            case "0":
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
            case "1":

                AdminController.createAccount();
                break;
            case "2":
                System.exit(0);
                break;
            default:
                displayLoginPage();
                break;
        }

    }

    public static void displayHomePage(User user){
        System.out.println("\n\n\nWelcome to ROBOTIX " + user.getFirstName() +" "+  user.getLastName());

        System.out.println("[0] Gérer Profile");
        System.out.println("[1] Gérer Portefeuille");
        System.out.println("[2] Gérer Commandes");
        System.out.println("[3] Gérer Activités");
        System.out.println("[4] Ma Flotte De Robots");
        System.out.println("[5] Mon Inventaire");
        System.out.println("[6] Se Déconnecter");

        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        
        switch (value) {
            case "0":
                UserMenu.displayManageProfile(user);
                
                break;
            case "1":
                UserMenu.displayManageWallet(user);
                break;
            case "2":
                System.out.println("cette page est indisponible pour le moment");
                displayHomePage(user);
                break;
            case "3":
                ActivityMenu.displayManageActivities(user);
                
                break;
            case "4":
                RobotFleetMenu.displayManageRobotFleet(user);
                
                break;
            case "5":
                System.out.println("cette page est indisponible pour le moment");
                displayHomePage(user);
                break;
            case "6":
                Menu.displayLoginPage();
                break;
            default:
                break;
        }

        scanner.close();
    }
    
}
