package Controllers;
import Models.User;
import Views.ActivityMenu;
import Views.Menu;
import Views.UserMenu;
import Models.Activity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Database.Database;
import Models.Order;
import Models.Supplier;
import Models.Component;
import Models.Client;
import java.util.UUID;


public class UserController {
    


    public static void manageFollowers (User user){

    }
    
    public static void manageInterests (User user){

    }

    public static void addInterest(User user){
        try{
        Scanner scanner = new Scanner(System.in);
        int interestCount = 0;
                    int interestToAdd;
                    ArrayList<String> availableInterests = new ArrayList<String>();
                    for (String interest : Database.getInterests()){
                        if (!user.getInterests().contains(interest)){
                            availableInterests.add(interest);
                        }
                    }
                    if(availableInterests.size() == 0){
                        System.out.println("Vous avez déjà tous les intérêts disponibles");
                        ActivityMenu.displayManageActivities(user);
                    }
                    while(true){
                        System.out.println("Choisissez l'intérêt que vous voulez ajouter");
                        for (String interest : Database.getInterests()){
                            if (!user.getInterests().contains(interest)){
                                System.out.println(interestCount + " " + interest);
                                interestCount ++;
                            }
                        }

                        interestToAdd = Integer.parseInt(scanner.nextLine());
                        if (interestToAdd > interestCount || interestToAdd < 0){
                            System.out.println("Vous ne pouvez pas choisir ce numéro, svp réessayez");
                        }
                        else{
                            user.addInterest(availableInterests.get(interestToAdd));
                            break;
                        }
        }
        }catch(Exception e){
            System.out.println("Erreur: Entrez un numéro valide");
        }
    }

    public static void deleteInterest(User user){
        try{
        if (user.getInterests().size() == 0){
            System.out.println("Vous n'avez pas d'intérêts à supprimer");
            ActivityMenu.displayManageActivities(user);
        }
        Scanner scanner = new Scanner(System.in);
        int userInterestCount = 0;
        for (String userInterest : user.getInterests()){
            System.out.println(userInterestCount + " " + userInterest);
            userInterestCount++;
        }
        while (true){
            System.out.println("Choisissez le numéro de l'intérêt que vous voulez enlever");
            int interestToDelete = Integer.parseInt(scanner.nextLine()); 

            if (interestToDelete > userInterestCount || interestToDelete < 0){
                System.out.println("Vous ne pouvez pas choisir ce numéro, svp réessayez");
            }
            else{
                user.deleteInterest(user.getInterests().get(interestToDelete));
                break;
            }
        }
    
        } catch(Exception e){
            System.out.println("Erreur: Entrez un numéro valide");
        }
    }

    public static void interestData(User user){
        if (user.getInterests().size() == 0){
            System.out.println("Vous n'avez pas d'intérêts");
            ActivityMenu.displayManageActivities(user);
        }
        for (String interest : user.getInterests()){
            System.out.println(interest);
        }
        ActivityMenu.displayManageActivities(user);
    }

    public static void order(User user){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Voici la liste des composantes que les fournisseurs ont à offrir:");
            for (Supplier supplier : Database.getAllSuppliers()){
                if(supplier.getStorage().size() == 0){
                    System.out.println("Le fournisseur " + supplier.getUserName() + " n'a pas de composantes à offrir");
                } else {
                    System.out.println(supplier.getUserName() + " a les composantes suivantes à offrir:");
                    supplier.showInventory();
                }
            }
            System.out.println("Entrez le nom du fournisseur et de la composante que vous voulez commander"+
            "(\"Ex: Fournisseur1, Composante1\", si vous voulez commander plusieurs composantes, séparez les par des / \"Ex: Fournisseur1, Composante1/Fournisseur2, Composante2\")");
            String [] supplierAndComponent = scanner.nextLine().split("/");
            for (String supplierComponent : supplierAndComponent){
                String[] supplierOrComponent = supplierComponent.split(",");
                for (Supplier supplier : Database.getAllSuppliers()){
                    if (supplier.getUserName().equalsIgnoreCase(supplierOrComponent[0])){
                        for (Component component : supplier.getStorage()){

                            if (component.getName().equalsIgnoreCase(supplierOrComponent[1].trim())){
                                Order order = new Order(UUID.randomUUID(),Database.getTime(),Database.getTime().plusDays(5),new ArrayList<>(Arrays.asList(component)),"En cours", (Client) user,supplier);
                                user.getOrders().add(order);
                                System.out.println("La commande a été passée avec succès");
                                UserMenu.displayManageOrders(user);
                                break;
                            }
                            
                        }
                        System.out.println("Composante introuvable");
                        UserMenu.displayManageOrders(user);
                        
                    }

                }
                System.out.println("Fournisseur introuvable");
                UserMenu.displayManageOrders(user);
                
            }
        }
        catch (Exception e) {
            System.out.println("Entrée invalide, veuillez réessayer");
            UserMenu.displayManageOrders(user);
        }
    }
}
