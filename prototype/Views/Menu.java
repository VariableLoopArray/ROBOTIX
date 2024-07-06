package Views;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import javax.xml.crypto.Data;

import Controllers.ActivityController;
import Controllers.AdminController;
import Controllers.LoginController;
import Controllers.TimeController;
import Controllers.UserController;
import Database.Database;
import Models.Activity;
import Models.User;
public class Menu {

    public static void displayLoginPage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n\t\t*** Menu de Connexion ***\n");
        System.out.println("[0] Se connecter");
        System.out.println("[1] Créer un compte");
        System.out.println("[2] Commander publiques");
        System.out.println("[3] Quitter");
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
                displayPublicOrders();
                break;
            case "3":
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
        System.out.println("[4] Gérer Flotte de Robots");
        System.out.println("[5] Gérer Inventaire");
        System.out.println("[6] Gérer Temps");
        System.out.println("[7] Se Déconnecter");

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
                UserMenu.displayManageOrders(user);
                break;
            case "3":
                ActivityMenu.displayManageActivities(user);
                break;
            case "4":
                RobotFleetMenu.displayManageRobotFleet(user);
                break;
            case "5":
                UserMenu.displayManageInventory(user);
                break;
            case "6":
                TimeController.displayTimeMenu(user);
                break;
            case "7":
                Menu.displayLoginPage();
                break;
            default:
                displayHomePage(user);
                break;
        }

        scanner.close();
    }

    public static void displayPublicOrders(){
        try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n\t\t*** Commandes Publiques ***\n");
        System.out.println("Voici les commandes publiques disponibles");
        System.out.println("[0] Récupérer la liste des clients");
        System.out.println("[1] Rechercher un client par son nom d'utilisateur");
        System.out.println("[2] Voir le profil d'un client");
        System.out.println("[3] Récupérer la liste des activités");
        System.out.println("[4] Récupérer la liste des intérêts");
        System.out.println("[5] Récupérer la liste des fournisseurs");
        System.out.println("[6] Rechercher un fournisseur par son nom d'utilisateur");
        System.out.println("[7] Voir le profil d'un fournisseur");
        System.out.println("[8] Rechercher une composante par son nom");
        System.out.println("[9] Retour");

        String value = scanner.nextLine();
        switch (value) {
            case "0":
                for (User u : Database.getAllClients()){
                    System.out.println(u.getUserName());
                }
                displayPublicOrders();
                break;
            case "1":
                AdminController.searchClient();
                displayPublicOrders();
                break;
            case "2":
                AdminController.displayClientProfile();
                displayPublicOrders();
                break;
            case "3":
                for (Activity activity : Database.getAllActivities()){
                    System.out.println(activity.getName());
                }
                displayPublicOrders();
                break;
            case "4":
                for (String interest : Database.getInterests()){
                    System.out.println(interest);
                }
                displayPublicOrders();
                break;
            case "5":
                for (User supplier : Database.getAllSuppliers()){
                    System.out.println(supplier.getUserName());
                }
                displayPublicOrders();
                break;
            case "6":
                AdminController.searchSupplier();
                displayPublicOrders();
                break;
            case "7":
                AdminController.displaySupplierProfile();
                displayPublicOrders();
                break;
            case "8":
                AdminController.searchComponent();
                displayPublicOrders();
                break;
            case "9":
                displayLoginPage();
                break;
            default:
                System.out.println("Entrée invalide");
                displayPublicOrders();
                break;
        }
        } catch(Exception e){
            System.out.println("Erreur: Entrez un numéro valide");
            displayPublicOrders();
        }

    }
    
}
