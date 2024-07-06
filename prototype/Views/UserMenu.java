package Views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

import Controllers.UserController;
import Models.Supplier;
import Database.Database;
import Models.Client;
import Models.Component;
import Models.Order;
import Models.User;

public class UserMenu {

    public static void displayManageProfile(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n\t\t*** Menu Utilisateur ***");
        System.out.println("[0] Visualiser mes données");
        System.out.println("[1] Gérer mes données");
        System.out.println("[2] Retour");
        switch(scanner.nextLine()){
            case "0":
                user.userData();
                displayManageProfile(user);
                break;
            case "1":
                System.out.println("Choisissez l'aspect que vous voulez Changer");
                System.out.println("[0] Changer le nom");
                System.out.println("[1] Changer le prénom");
                System.out.println("[2] Changer le nom d'utilisateur");
                System.out.println("[3] Changer le mot de passe");
                System.out.println("[4] Changer l'adresse courriel");
                System.out.println("[5] Changer le nom de l'entreprise");
                System.out.println("[6] Changer le numéro de téléphone");
                System.out.println("[7] Follow quelqu'un");
                System.out.println("[8] Unfollow quelqu'un");
                System.out.println("[9] Retour");    


                String value = scanner.nextLine();
                switch(value){
                    case "0":
                        System.out.println("Entrez le nouveau nom");
                        user.setLastName(scanner.nextLine());
                        displayManageProfile(user);

                        break;
                    case "1":
                        System.out.println("Entrez le nouveau prénom");
                        user.setFirstName(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "2":
                        System.out.println("Entrez le nouveau nom d'utilisateur");
                        user.setUserName(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "3":
                        System.out.println("Entrez le nouveau mot de passe");
                        user.setPassword(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "4":
                        System.out.println("Entrez la nouvelle adresse courriel");
                        user.setEmail(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "5":
                        System.out.println("Entrez le nouveau nom de l'entreprise");
                        user.setCompanyName(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "6":
                        System.out.println("Entrez le nouveau numéro de téléphone");
                        user.setPhoneNumber(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "7":
                        if(Database.getAllUsers().size() == 0){
                            System.out.println("Il n'y a personne à follow");
                            displayManageProfile(user);
                        } else {
                        System.out.println("Voici la liste des personnes que vous pouvez follow");
                        for (User user2 : Database.getAllUsers()){
                            if (!user.getFollowers().contains(user2)){
                                System.out.println(user2.getUserName());
                            }
                        }
                        System.out.println("Entrez le username de la personne que vous voulez follow");
                        String name = scanner.nextLine();
                        for (User user2 : Database.getAllUsers()){
                            if (user2.getUserName().equalsIgnoreCase(name)){
                                user.getFollowers().add(user2);
                                user2.getNotifs().add("NewFollower " + user.getUserName());
                                System.out.println("La personne a été follow avec succès");
                                displayManageProfile(user);
                                break;
                            }
                        }
                        System.out.println("Le nom est invalide");
                        displayManageProfile(user);
                        break;
                        }
                    case "8":
                        if(user.getFollowers().size() == 0){
                            System.out.println("Vous n'avez personne à unfollow");
                            displayManageProfile(user);
                        } else {
                        System.out.println("Voici la liste des personnes que vous pouvez unfollow");
                        for (User user2 : user.getFollowers()){
                            System.out.println(user2.getFirstName());
                        }
                        System.out.println("Entrez le username de la personne que vous voulez unfollow");
                        String nameToUnffolow = scanner.nextLine();
                        for (User user2 : user.getFollowers()){
                            if (user2.getUserName().equalsIgnoreCase(nameToUnffolow)){
                                user.getFollowers().remove(user2);
                                System.out.println("La personne a été unfollow avec succès");
                                scanner.close();
                                displayManageProfile(user);

                                break;
                            }
                        }
                        System.out.println("Le nom est invalide");
                        displayManageProfile(user);
                        break;
                        }
                    case "9":
                        displayManageProfile(user);
                    default:
                        System.out.println("Entrée invalide");
                        displayManageProfile(user);
                        break;
                }
                break;

            case "2":
                Menu.displayHomePage(user);
                break;
            default:
                System.out.println("Entrée invalide");
                displayManageProfile(user);
                break;
        }
}
    public static void displayManageWallet(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n*** Menu Portefeuille ***");
        System.out.println("[0] Regarder la quantité d'argent dans ma portefeuille");
        System.out.println("[1] Ajouter de l'argent dans mon compte");
        System.out.println("[2] retour");

        switch(scanner.nextLine()){
            case "0":
                System.out.println("Montant: " + String.format("%.2f",user.getWallet()) + "$");
                displayManageWallet(user);
                break;
            case "1":
                System.out.println("Entrez le montant que vous voulez ajouter dans votre compte");
                while (true) {
                    try {
                    double money = Double.parseDouble(scanner.nextLine());
                        if (money <= 0 ){
                            System.out.println("Entrez un montant valide");
                            break;
                        }
                        else{
                            user.addMoney(money);
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Entrez un montant valide");
                        displayManageProfile(user);
                        break;
                    }
                }
                displayManageWallet(user);
                break;
            case "2":
                Menu.displayHomePage(user);
                break;
            default:
                System.out.println("commande non connue");
                displayManageWallet(user);
                break;

        }
    }
    
    public static void displayManageOrders(User user){
        try {
            if(user instanceof User){
                System.out.println("\n\n\n*** Menu Commandes ***");
            System.out.println("[0] Voir mes commandes");
            System.out.println("[1] Commander une composante");
            System.out.println("[2] Retour");
            Scanner scanner = new Scanner(System.in);
            switch(scanner.nextLine()){
                case "0":
                    user.showOrders();
                    displayManageOrders(user);
                    break;
                case "1":
                    UserController.order(user);
                    break;
                case "2":
                    Menu.displayHomePage(user);
                    break;
                default:
                    System.out.println("Entrée invalide");
                    displayManageOrders(user);
                    break;
            }
            } else if (user instanceof Supplier) {
                System.out.println("\n\n\n*** Menu Commandes ***");
                System.out.println("[0] Voir mes ventes");
                System.out.println("[1] Quitter");
                Scanner scanner = new Scanner(System.in);
                switch(scanner.nextLine()){
                    case "0":
                        user.showOrders();
                        displayManageOrders(user);
                        break;
                    case "1":
                        Menu.displayHomePage(user);
                        break;
                    default:
                        System.out.println("Entrée invalide");
                        displayManageOrders(user);
                        break;
                }
            }
            
        } catch (Exception e) {
            System.out.println("Entrée invalide");
            displayManageOrders(user);
        }
    }


    
    public static void displayManageInventory(User user){
        Scanner scanner = new Scanner(System.in);
        
        try {
            if (user instanceof Client) {
                System.out.println("Choisissez ce que vous voulez faire");
                System.out.println("[0] Voir mon inventaire ");
                System.out.println("[1] Enlever des composantes de mon inventaire");
                System.out.println("[2] Retour");

                
                
                switch(scanner.nextLine()){
                    case "0":
                        user.showInventory();
                        displayManageInventory(user);
                        break;
                    case "1":
                        user.deleteInventory();
                        displayManageInventory(user);
                        break;
                    case "2":
                        Menu.displayHomePage(user);
                        break;
                    default:
                        System.out.println("Commande Inconnue, veuillez réessayer");
                        displayManageInventory(user);
                        break;
                    }
                    
            } else if (user instanceof Supplier)
            {
                System.out.println("Choisissez ce que vous voulez faire");
                System.out.println("[0] Voir mon inventaire ");
                System.out.println("[1] Enlever une composantes de mon inventaire");
                System.out.println("[2] Ajouter une composante à mon inventaire");
                System.out.println("[3] Retour");


                    switch(scanner.nextLine()){
                        case "0":
                            user.showInventory();
                            displayManageInventory(user);
                            break;
                        case "1":
                            user.deleteInventory();
                            displayManageInventory(user);
                            break;
                        case "2":
                            Supplier userS = (Supplier) user;
                            userS.addComponents();
                            displayManageInventory(user);
                            break;
                        case "3":
                            Menu.displayHomePage(user);
                            break;
                        default:
                            System.out.println("Commande Inconnue, veuillez réessayer");
                            displayManageInventory(user);
                            break;
                    }
                    
            
            }
            }catch (Exception e) {
                System.out.println("Entrée invalide veuillez réessayez.");
            }
        
    }
}
