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
        System.out.println("\n\n\n*** Menu UTILISATEUR ***");
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
                                System.out.println("La personne a été follow avec succès");
                                displayManageProfile(user);
                                break;
                            }
                        }
                        System.out.println("Le nom est invalide");
                        displayManageProfile(user);
                        break;
                    case "8":
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
                    case "9":
                        displayManageProfile(user);
                    default:
                        System.out.println("commande non connue");
                        displayManageProfile(user);
                        break;
                }
                break;

            case "2":
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
        System.out.println("\n\n\n*** Menu PORTEFEUILLE ***");
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
}
