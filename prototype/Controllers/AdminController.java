package Controllers;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

import Models.Supplier;
import Models.Client;
import Models.RobotFleet;
import Models.Robot;
import Models.Component;
import Models.Activity;
import Models.User;
import Models.Order;
import Views.Menu;
import Database.Database;



public class AdminController {
    public static void createAccount(){
        try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Souhaitez-vous vous enregistrer en tant que fournisseur ou client ?");
                String userType = scanner.nextLine();
                userType = userType.toLowerCase();
                if (!userType.equals("fournisseur" ) && !userType.equals("client") ){
                    System.out.println("Veuillez choisir entre [fournisseur] ou [client]  \n");
                    createAccount();
                }

                System.out.println("Indiquez votre prénom");
                String firstName = scanner.nextLine();

                System.out.println("Indiquez votre nom");
                String lastName = scanner.nextLine();

                System.out.println("Indiquez votre nom d'utilisateur");
                String userName = scanner.nextLine();

                System.out.println("Indiquez votre mot de passe");
                String passWord = scanner.nextLine();

                UUID userID = UUID.randomUUID();

                System.out.println("Indiquez votre adresse courriel electronique");
                String email = scanner.nextLine();

                System.out.println("Indiquez le nom de votre entreprise");
                String companyName = scanner.nextLine();
                
                System.out.println("Veuillez entrer votre numéro de téléphone (ex: 111-111-1111)");
                String phoneNumber = scanner.nextLine();

                RobotFleet robotFleet = new RobotFleet(new ArrayList<Robot>());

                ArrayList<Component> inventory = new ArrayList<Component>();

                float wallet = 0;

                ArrayList<String> interests = new ArrayList<String>();

                try {
                    if(userType.equals("client")){

                    System.out.println("Quelles catégories parmi les suivantes vous intéresse?(selectionnez le numéro correspondant à la catégorie en les separant par des virgules) \n");

                    for (int i = 0; i < Database.getInterests().size(); i++){
                        System.out.println( "[" + i + "]" + Database.getInterests().get(i));
                    }
                    String[] interestsArray = scanner.nextLine().split(",");
                    for (int j = 0; j < interestsArray.length; j++){
                        interests.add(Database.getInterests().get(Integer.parseInt(interestsArray[j])));
                    }
                    
                }
                } catch (Exception e) {
                    System.out.println("Entrée Invalide \n");
                    createAccount();
                }

                int productionCapacity = 0;
                try {
                    if(userType.equals("fournisseur")){
                        System.out.println("Quelle est votre capacité de production?");
                        productionCapacity = scanner.nextInt();
                    }
                } catch (Exception e) {
                    System.out.println("Entrée Invalide \n");
                    createAccount();
                }


                ArrayList<Component> storage = new ArrayList<Component>();

                ArrayList<Activity> activities = new ArrayList<Activity>();

                ArrayList<User> Followers = new ArrayList<User>();

                ArrayList<User> Following = new ArrayList<User>();

                ArrayList<Order> orders = new ArrayList<Order>();

                if (userType.equals("fournisseur")){
                    Supplier newUser = new Supplier(firstName, lastName, userName, passWord, userID,
                    email, companyName, phoneNumber,wallet, robotFleet, activities,
                    Followers, Following,productionCapacity, storage, orders);
                    Database.getAllUsers().add(newUser);
                    Database.getAllSuppliers().add(newUser);
                    System.out.println("user successfully created");
                    Menu.displayLoginPage();
                }

                else if (userType.equals("client")){
                    Client newUser = new Client(firstName, lastName, userName, passWord, userID,
                    email, companyName, phoneNumber,wallet, robotFleet,inventory, interests, activities,
                    Followers, Following, orders);
                    Database.getAllUsers().add(newUser);
                    Database.getAllClients().add(newUser);
                    System.out.println("user successfully created");
                    Menu.displayLoginPage();
                }
                scanner.close();
    }
        catch (InputMismatchException e){
            System.out.println("wrong type of input please try again \n");
        }
}

public static void searchClient(){
    try {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom d'utilisateur du client que vous cherchez");
        String userName = scanner.nextLine();
        for (Client client : Database.getAllClients()){
            if (client.getUserName().equalsIgnoreCase(userName)){
                System.out.println(client.getFirstName()+ client.getLastName()+ " existe dans la base de données");
                Menu.displayPublicOrders();
            }
        }
        System.out.println("Client introuvable");
    } catch (Exception e) {
        System.out.println("Entrée invalide, veuillez réessayer");
        searchClient();
    }


}

public static void searchSupplier(){
    try {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom d'utilisateur du fournisseur que vous cherchez");
        String userName = scanner.nextLine();
        for (Supplier supplier : Database.getAllSuppliers()){
            if (supplier.getUserName().equalsIgnoreCase(userName)){
                System.out.println(supplier.getFirstName()+ supplier.getLastName()+ " existe dans la base de données");
                Menu.displayPublicOrders();
            }
        }
        System.out.println("Fournisseur introuvable");
    } catch (Exception e) {
        System.out.println("Entrée invalide, veuillez réessayer");
        searchSupplier();
    }

        
    
}

public static void displayClientProfile(){
    try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom d'utilisateur du client dont vous voulez voir le profil");
        String userName = scanner.nextLine();
        for (Client client : Database.getAllClients()){
            if (client.getUserName().equalsIgnoreCase(userName)){
                client.userData();
                Menu.displayPublicOrders();
            }
        }
        System.out.println("Client introuvable");
    } catch (Exception e) {
        System.out.println("Entrée invalide, veuillez réessayer");
        displayClientProfile();
    }

}
public static void displaySupplierProfile(){
    try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom d'utilisateur du fournisseur dont vous voulez voir le profil");
        String userName = scanner.nextLine();
        for (Supplier supplier : Database.getAllSuppliers()){
            if (supplier.getUserName().equalsIgnoreCase(userName)){
                supplier.userData();
                Menu.displayPublicOrders();
            }
        }
        System.out.println("Fournisseur introuvable");
    } catch (Exception e) {
        System.out.println("Entrée invalide, veuillez réessayer");
        displaySupplierProfile();
    }


}

public static void searchComponent(){
    try {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom de la composante que vous cherchez");
        String componentName = scanner.nextLine();
            for (Component component : Database.getAllComponents()){
                if (component.getName().equalsIgnoreCase(componentName)){
                    System.out.println("cette composante existe dans la base de données");
                    Menu.displayPublicOrders();
                }
            }
        System.out.println("Composante introuvable");
    } catch (Exception e) {
        System.out.println("Entrée invalide, veuillez réessayer");
        searchComponent();


    }
}
}