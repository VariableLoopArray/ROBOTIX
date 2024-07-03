package Views;

import java.util.Scanner;

import Database.Database;
import Models.User;

/**
 * UserMenu
 */
public class UserMenu {

    public static void displayManageProfile(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Menu UTILISATEUR ***");
        System.out.println("[1] Voir mes données");
        System.out.println("[2] Gérer mes données");
        System.out.println("[3] Retour");
        switch(scanner.nextLine()){
            case "1":
                user.userData();
                displayManageProfile(user);
                break;
            case "2":
                System.out.println("Choisissez l'aspect que vous voulez Changer");
                System.out.println("[1] Changer le nom");
                System.out.println("[2] Changer le prénom");
                System.out.println("[3] Changer le nom d'utilisateur");
                System.out.println("[4] Changer le mot de passe");
                System.out.println("[5] Changer l'adresse courriel");
                System.out.println("[6] Changer le nom de l'entreprise");
                System.out.println("[7] Changer le numéro de téléphone");
                System.out.println("[8] Changer le portefeuille");
                System.out.println("[9] Follow quelqu'un");
                System.out.println("[10] Unfollow quelqu'un");
                System.out.println("[11] Retour");    


                String value = scanner.nextLine();
                switch(value){
                    case "1":
                        System.out.println("Entrez le nouveau nom");
                        user.setLastName(scanner.nextLine());
                        displayManageProfile(user);

                        break;
                    case "2":
                        System.out.println("Entrez le nouveau prénom");
                        user.setFirstName(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "3":
                        System.out.println("Entrez le nouveau nom d'utilisateur");
                        user.setUserName(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "4":
                        System.out.println("Entrez le nouveau mot de passe");
                        user.setPassword(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "5":
                        System.out.println("Entrez la nouvelle adresse courriel");
                        user.setEmail(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "6":
                        System.out.println("Entrez le nouveau nom de l'entreprise");
                        user.setCompanyName(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "7":
                        System.out.println("Entrez le nouveau numéro de téléphone");
                        user.setPhoneNumber(scanner.nextLine());
                        displayManageProfile(user);
                        break;
                    case "8":
                        System.out.println("Entrez la quantité d'argent que vous voulez ajouter");
                        user.setWallet(Float.parseFloat(scanner.nextLine()));
                        displayManageProfile(user);
                        break;
                    case "9":
                        System.out.println("Entrez le prénom de la personne que vous voulez follow");
                        String name = scanner.nextLine();
                        for (User user2 : Database.getAllUsers()){
                            if (user2.getFirstName().equalsIgnoreCase(name)){
                                user.getFollowers().add(user2);
                                System.out.println("La personne a été follow avec succès");
                                displayManageProfile(user);
                                break;
                            }
                        }
                        System.out.println("Le nom est invalide");
                        displayManageProfile(user);
                        break;
                    case "10":
                        System.out.println("Entrez le prénom de la personne que vous voulez unfollow");
                        String nameToUnffolow = scanner.nextLine();
                        for (User user2 : user.getFollowers()){
                            if (user2.getFirstName().equalsIgnoreCase(nameToUnffolow)){
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
                    case "11":
                        displayManageProfile(user);
                    default:
                        System.out.println("commande non connue");
                        displayManageProfile(user);
                        break;
                }
                break;

            case "3":
                Menu.displayHomePage(user);
                break;
            default:
                System.out.println("commande non connue");
                displayManageProfile(user);
                break;
        }
}
    public static void displayManageWallet(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("[1] Regarder la quantité d'argent dans ma portefeuille");
        System.out.println("[2] Ajouter de l'argent dans mon compte");
        System.out.println("[3] retour");

        switch(scanner.nextLine()){
            case "1":
                System.out.println("Montant: " + user.getWallet());
                displayManageWallet(user);
                break;
            case "2":
                System.out.println("Indiquez combien d'argent vous voulez ajouter");
                while (true) {
                    double money = Double.parseDouble(scanner.nextLine());
                    if (money <= 0 ){
                        System.out.println("Vous ne pouvez pas ajouter 0$ ou de l'argent négatif.");
                    }
                    else{
                        user.addMoney(money);
                        break;
                    }
                }
                displayManageWallet(user);
                break;
            case "3":
                Menu.displayHomePage(user);
                break;

        }
    }
}
