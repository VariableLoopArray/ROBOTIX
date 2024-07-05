package Models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import javax.xml.crypto.Data;

import Database.Database;
import Views.UserMenu;
public class Supplier extends User{

    private int productionCapacity;
    private ArrayList<Component> storage;
    
    public Supplier(String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber, float wallet, RobotFleet RobotFleet,ArrayList<Activity> activities, 
    ArrayList<User> Followers, ArrayList<User> Following, int productionCapacity, ArrayList<Component> storage, ArrayList<Order> orders){

        super( firstName, lastName,  username,  password, userID, email, companyName, phoneNumber,wallet, RobotFleet, 
        activities, Followers, Following, orders);
        this.productionCapacity = productionCapacity;
        storage.add(new Component("CPU", new ArrayList<String>(Arrays.asList("haute performance","léger")), 350.0f, 1.0f, 1.0f, 1.0f, this ,UUID.randomUUID()));
        this.storage = storage;
    }

    @Override
    public void userData(){
        System.out.println("Nom: " + this.getFirstName());
        System.out.println("Prénom: " + this.getLastName());
        System.out.println("Nom d'utilisateur: " + this.getUserName());
        System.out.println("Mot de passe: " + this.getPassword());
        System.out.println("ID: " + this.getUserID());
        System.out.println("Courriel: " + this.getEmail());
        System.out.println("Nom de l'entreprise: " + this.getCompanyName());
        System.out.println("Numéro de téléphone: " + this.getPhoneNumber());
        System.out.println("Portefeuille: " + this.getWallet());
        System.out.println("\nFlotte de robots: ");
        for (Robot robot : this.getRobotFleet().getRobots()){
            System.out.println(robot.getName());
        }
        System.out.println("\nActivités: ");
        for (Activity activity : this.getActivities()){
            System.out.println(activity.getName());
        }
        System.out.println("\nAbonnés: ");
        for (User follower : this.getFollowers()){
            System.out.println(follower.getUserName());
        }
        System.out.println("\nAbonnements: ");
        for (User following : this.getFollowing()){
            System.out.println(following.getUserName());
        }
        System.out.println("\nCapacité de production: " + this.productionCapacity);
    }

    public void showInventory(){
        int CPUCount = 0;
        int roueCount = 0;
        int brasCount = 0;
        int heliceCount = 0;
        int cameraCount = 0;
        int hautParleurCount = 0;
        int microCount = 0;
        int ecranCount = 0;

        for (Component component : this.getStorage()){
            if (component.getName().contains("CPU")){
                CPUCount++;
            }
            if (component.getName().contains("Roue")){
                roueCount++;
            }
            if (component.getName().contains("Bras")){
                brasCount++;
            }
            if (component.getName().contains("Hélice")){
                heliceCount++;
            }
            if (component.getName().contains("Caméra")){
                cameraCount++;
            }
            if (component.getName().contains("Haut-Parleur")){
                hautParleurCount++;
            }
            if (component.getName().contains("Microphone")){
                microCount++;
            }
            if (component.getName().contains("Écran")){
                ecranCount++;
            }
        }

        System.out.println(CPUCount + " CPU");
        System.out.println(roueCount + " Roues");
        System.out.println(brasCount + " Bras");
        System.out.println(heliceCount + " Hélices");
        System.out.println(cameraCount + " Caméras");
        System.out.println(hautParleurCount + " Haut-Parleurs");
        System.out.println(microCount + " Microphone");
        System.out.println(ecranCount + " Écran");

    }

    public void addComponents(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ajout de composantes\n\n");
        for (int i = 0; i < Database.getAllComponents().size(); i++){
            System.out.println("[" + i + "]" + Database.getAllComponents().get(i).getName());
        }
        System.out.println("Entrez le numéro de la composante que vous voulez ajouter");
        int componentNumber = Integer.parseInt(scanner.nextLine());
        String componentName = Database.getAllComponents().get(componentNumber).getName();
        System.out.println("Entrez les tags de la composante separés par des virgules(ex: tag1,tag2,tag3)");
        String [] tags = scanner.nextLine().split(",");
        System.out.println("Entrez le prix de la composante (en $)");
        float price = Float.parseFloat(scanner.nextLine());
        System.out.println("Entrez la largeur de la composante (en cm)");
        float width = Float.parseFloat(scanner.nextLine());
        System.out.println("Entrez la longueur de la composante (en cm)");
        float length = Float.parseFloat(scanner.nextLine());
        System.out.println("Entrez le poids de la composante (en kg)");
        float weight = Float.parseFloat(scanner.nextLine());
        UUID serialNumber = UUID.randomUUID();
        Component component = new Component(componentName, new ArrayList<String>(List.of(tags)), price, width, length, weight,this, serialNumber);
        this.getStorage().add(component);
        System.out.println("La composante a été ajoutée avec succès");
        
    }

    @Override
    public void deleteInventory(){
        Scanner scanner = new Scanner(System.in);
        int CPUCount = 0;
                int roueCount = 0;
                int brasCount = 0;
                int heliceCount = 0;
                int cameraCount = 0;
                int hautParleurCount = 0;
                int microCount = 0;
                int ecranCount = 0;

                for (Component component : this.getInventory()){
                    if (component.getName().contains("CPU")){
                        CPUCount++;
                    }
                    if (component.getName().contains("Roue")){
                        roueCount++;
                    }
                    if (component.getName().contains("Bras")){
                        brasCount++;
                    }
                    if (component.getName().contains("Hélice")){
                        heliceCount++;
                    }
                    if (component.getName().contains("Caméra")){
                        cameraCount++;
                    }
                    if (component.getName().contains("Haut-Parleur")){
                        hautParleurCount++;
                    }
                    if (component.getName().contains("Microphone")){
                        microCount++;
                    }
                    if (component.getName().contains("Écran")){
                        ecranCount++;
                    }
                }
                    try {
                        System.out.println("Entrez le nom de la composante que vous voulez enlever");
                        System.out.println("[0] CPU");
                        System.out.println("[1] Roue");
                        System.out.println("[2] Bras");
                        System.out.println("[3] Hélice");
                        System.out.println("[4] Caméra");
                        System.out.println("[5] Haut-Parleur");
                        System.out.println("[6] Microphone");
                        System.out.println("[7] Écran");
                        System.out.println("[8] Retour");
                        
                        switch(scanner.nextLine()){
                            case "0":
                                if (CPUCount < 1){
                                    System.out.println("Vous n'avez pas de CPU à enlever.");
                                }
                                else{
                                    for (Component component : this.storage){
                                        if (component.getName().equals("CPU")){
                                            this.storage.remove(component);
                                            break;
                                        }
                                    }
                                }
                                UserMenu.displayManageInventory(this);
                                break;
                            case "1":
                                if (roueCount < 1){
                                    System.out.println("Vous n'avez pas de CPU à enlever.");
                                }
                                else{
                                    for (Component component : this.storage){
                                        if (component.getName().equals("Roue")){
                                            this.storage.remove(component);
                                            break;
                                        }
                                    }
                                }
                                UserMenu.displayManageInventory(this);
                                break;
                            case "2":
                                if (brasCount < 1){
                                    System.out.println("Vous n'avez pas de CPU à enlever.");
                                }
                                else{
                                    for (Component component : this.storage){
                                        if (component.getName().equals("Bras")){
                                            this.storage.remove(component);
                                            break;
                                        }
                                    }
                                }
                                UserMenu.displayManageInventory(this);
                                break;
                            case "3":
                                if (heliceCount < 1){
                                    System.out.println("Vous n'avez pas de CPU à enlever.");
                                }
                                else{
                                    for (Component component : this.storage){
                                        if (component.getName().equals("Hélice")){
                                            this.storage.remove(component);
                                            break;
                                        }
                                    }
                                }
                                UserMenu.displayManageInventory(this);
                                break;
                            case "4":
                                if (cameraCount < 1){
                                    System.out.println("Vous n'avez pas de CPU à enlever.");
                                }
                                else{
                                    for (Component component : this.storage){
                                        if (component.getName().equals("Caméra")){
                                            this.storage.remove(component);
                                            break;
                                        }
                                    }
                                }
                                UserMenu.displayManageInventory(this);
                                break;
                            case "5":
                                if (hautParleurCount < 1){
                                    System.out.println("Vous n'avez pas de CPU à enlever.");
                                }
                                else{
                                    for (Component component : this.storage){
                                        if (component.getName().equals("Haut-Parleur")){
                                            this.storage.remove(component);
                                            break;
                                        }
                                    }
                                }
                                UserMenu.displayManageInventory(this);
                                break;
                            case "6":
                                if (microCount < 1){
                                    System.out.println("Vous n'avez pas de Microphone à enlever.");
                                }
                                else{
                                    for (Component component : this.storage){
                                        if (component.getName().equals("Microphone")){
                                            this.storage.remove(component);
                                            break;
                                        }
                                    }
                                }
                                UserMenu.displayManageInventory(this);
                                break;
                            case "7":
                                if (ecranCount < 1){
                                    System.out.println("Vous n'avez pas de Écran à enlever.");
                                    
                                }
                                else{
                                    for (Component component : this.storage){
                                        if (component.getName().equals("Écran")){
                                            this.storage.remove(component);
                                            break;
                                        }
                                    }
                                }
                                UserMenu.displayManageInventory(this);
                                break;
                            case "8":
                                UserMenu.displayManageInventory(this);
                                break;
                            default:
                                System.out.println("Entrée non connue, veuillez réessayez");
                                UserMenu.displayManageInventory(this);
                                break;
                            }
                } catch (Exception e) {
                    System.out.println("Entrée Invalide, veuillez réessayer");
                    UserMenu.displayManageInventory(this);
                }
            
                UserMenu.displayManageInventory(this);
                
        
    }





    public int getProductionCapacity(){
        return productionCapacity;
    }

    public void setProductionCapacity(int productionCapacity){
        this.productionCapacity = productionCapacity;
    }
    
    public ArrayList<Component> getStorage(){
        return storage;
    }

    public void setStorage(ArrayList<Component> storage){
        this.storage = storage;
    }
}