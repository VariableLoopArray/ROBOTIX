package Implementation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class prototype {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        utilisateur user = new utilisateur("YinYang","password", "Yin", "Yang", "yinyang@gmail.com", "514 999 9999"); // hard coded only one user
        fournisseur supplier = new fournisseur("Robotix", "1234 rue Robot", "robotix@gmail.com", "514 999 9999", new ArrayList<>(Arrays.asList("capteur", "helice", "bras")), 1);// hard coded only one supplier

        System.out.println("Bienvenue chez Robotix !");
        System.out.println("Veuillez entrer un nombre: ");
        boolean running = true;
        boolean logged = false;
        while (running) {
        System.out.println("1: s'inscrire comme utilisateur");
        System.out.println("2: Se connecter");
        System.out.println("3: Enregistrer un robot");
        System.out.println("4: Acheter des composants");
        System.out.println("5: Créer une commande");
        System.out.println("6: Afficher les métriques de ma flotte");
        System.out.println("7: Gestion de problèmes");
        System.out.println("8: S'inscrire comme fournisseur");
        System.out.println("9: Quitter");
        String input = sc.nextLine();
        switch (input) {
            case "1":
                System.out.println("Veuillez entrer votre nom d'utilisateur: ");
                String userName1 = sc.nextLine();
                System.out.println("Veuillez entrer votre prénom: ");
                String name1 = sc.nextLine();
                System.out.println("Veuillez entrer votre nom de famille: ");
                String lastName1 = sc.nextLine();
                System.out.println("Veuillez entrer votre email: ");
                String email1 = sc.nextLine();
                System.out.println("Veuillez entrer votre numéro de téléphone: ");
                String phone1 = sc.nextLine();
                // user.createUser(userName, name, lastName, email, phone);...
                System.out.println("Votre compte a été créé avec succès. Appuyez sur 'Enter' pour continuer.");
                if (sc.nextLine() == "Enter"){
                    continue;
                }
                break;
            case "2":
                System.out.println("Veuillez entrer votre nom d'utilisateur: ");
                String userName2 = sc.nextLine();
                System.out.println("Veuillez entrer votre mot de passe: ");
                String password2 = sc.nextLine();
                logged = user.login(userName2, password2);
                if (sc.nextLine() == "Enter"){
                    continue;
                }
                break;
            case "3":
                if (logged == false){
                    System.out.println("Vous devez vous connecter pour enregistrer un robot. Appuyez sur 'Enter' pour continuer.");
                } else{
                    System.out.println("Veuillez entrer le nom de votre robot: ");
                    String robotName = sc.nextLine();
                    System.out.println("Veuillez entrer le numéro de série de votre robot: ");
                    String serialNb = sc.nextLine();
                    // Robot robot = new Robot(robotName, serialNb);...
                    System.out.println("Bravo! Votre robot a été enregistré avec succès. Appuyez sur 'Enter' pour continuer.");
                }
                if (sc.nextLine() == "Enter"){
                    continue;
                }
                break;
            case "4":
                if (logged == false){
                    System.out.println("Vous devez vous connecter pour acheter des composants. Appuyez sur 'Enter' pour continuer.");
                } else{                    
                    System.out.println("Voici la liste des fournisseurs: ");
                    for (int i = 0; i < 5; i++){
                    System.out.println("Fournisseur " + i);// hard coded
                    }            
                    System.out.println("Veuillez entrer le numéro du fournisseur: ");
                    String supplierNb = sc.nextLine();
                    switch (supplierNb) {
                        case "0":
                            System.out.println("Voici la liste des produits: ");
                            supplier.toSale();
                            System.out.println("Veuillez entrer le numéro du produit: ");
                            break;
                        case "1":
                            System.out.println("Voici la liste des produits: ");
                            supplier.toSale();
                            System.out.println("Veuillez entrer le numéro du produit: ");
                            break;
                        case "2":
                            System.out.println("Voici la liste des produits: ");
                            supplier.toSale();
                            System.out.println("Veuillez entrer le numéro du produit: ");
                            break;
                        case "3":
                            System.out.println("Voici la liste des produits: ");
                            supplier.toSale();
                            System.out.println("Veuillez entrer le numéro du produit: ");
                            break;
                        case "4":
                            System.out.println("Voici la liste des produits: ");
                            supplier.toSale();
                            System.out.println("Veuillez entrer le numéro du produit: ");
                            break;
                        default:
                            break;
                    }
                }
                if (sc.nextLine() == "Enter"){
                    continue;
                }
                break;
                
            case "5":
                if (logged == false){
                    System.out.println("Vous devez vous connecter pour créer une commande. Appuyez sur 'Enter' pour continuer.");
                } else{
                    System.out.println("Veuillez entrer le numéro de série de votre robot: ");
                    String serialNb2 = sc.nextLine();
                    System.out.println("Veuillez programmer la commande: ");
                    String command = sc.nextLine();
                    // Command command = new Command(serialNb2, command);...
                }
                if (sc.nextLine() == "Enter"){
                    continue;
                }
                break;
            case "6":
                if (logged == false){
                    System.out.println("Vous devez vous connecter pour afficher les métriques de votre flotte. Appuyez sur 'Enter' pour continuer.");
                } else{
                    System.out.println("Voici les métriques de votre flotte: ");
                    // fleet.getMetrics();...
                }
                if (sc.nextLine() == "Enter"){
                    continue;
                }
                break;
            case "7":
                if (logged == false){
                    System.out.println("Vous devez vous connecter pour gérer les problèmes. Appuyez sur 'Enter' pour continuer.");
                } else{
                    System.out.println("Veuillez entrer le numéro de série de votre robot: ");
                    String serialNb3 = sc.nextLine();
                    System.out.println("Veuillez entrer le problème: ");
                    String problem = sc.nextLine();
                    // Problem problem = new Problem(serialNb3, problem);...
                }
                if (sc.nextLine() == "Enter"){
                    continue;
                }
                break;
            case "8":
                System.out.println("Veuillez entrer le nom de votre entreprise: ");
                String companyName = sc.nextLine();
                System.out.println("Veuillez entrer votre nom: ");
                String name2 = sc.nextLine();
                System.out.println("Veuillez entrer votre addresse: ");
                String addresse = sc.nextLine();
                System.out.println("Veuillez entrer votre email: ");
                String email2 = sc.nextLine();
                System.out.println("Veuillez entrer votre numéro de téléphone: ");
                String phone2 = sc.nextLine();
                // supplier.createSupplier(companyName, name, addresse, email, phone);...
                System.out.println("Votre compte a été créé avec succès. Appuyez sur 'Enter' pour continuer.");
                if (sc.nextLine() == "Enter"){
                    continue;
                }

                break;
            case "9":
                System.out.println("Merci d'avoir utilisé Robotix !");
                running = false;
                break;
            default:
                System.out.println("Veuillez entrer un nombre valide. Appuyez sur 'Enter' pour continuer.");
                if (sc.nextLine() == "Enter"){
                    continue;
                }
            }
        }     
    }
}

