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
        System.out.println("\n\n\n\t\t*** Menu de Connexion ***\n");
        System.out.println("[0] Se connecter");
        System.out.println("[1] Créer un compte");
        System.out.println("[2] Quitter");
        String value = scanner.nextLine();
        switch (value) {
            case "0":
                System.out.println("Entrez votre nom d'utilisateur:");
                String username = scanner.nextLine();
                System.out.println("Entrez votre mot de passe:");
                String password = scanner.nextLine();
                User user = new User();
                
                if (LoginController.login(username,password,Database.getAllUsers()) >= 0){
                    user = Database.getAllUsers().get(LoginController.login(username,password,Database.getAllUsers()));
                    displayHomePage(user);
                } else {
                    System.out.println("Nom d'utilisateur ou mot de passe incorrect, veuillez réessayer");
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
        System.out.println("\n\n\nBienvenue chez ROBOTIX " + user.getFirstName() +" "+  user.getLastName());

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
                displayHomePage(user);
                break;
        }

        scanner.close();
    }
    
}
