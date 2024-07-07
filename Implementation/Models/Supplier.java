package Models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import Database.Database;
import Views.UserMenu;
public class Supplier extends User{

    private int productionCapacity;
    private ArrayList<Component> storage;
    
    public Supplier(String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber, float wallet, RobotFleet RobotFleet,ArrayList<Activity> activities, 
    ArrayList<User> Followers, ArrayList<User> Following, int productionCapacity, ArrayList<Component> storage, ArrayList<Order> orders, ArrayList<String> notifs
    ){

        super( firstName, lastName,  username,  password, userID, email, companyName, phoneNumber,wallet, RobotFleet, 
        activities, Followers, Following, orders, notifs);
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
        try {
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
        } catch (Exception e) {
            System.out.println("Entrée Invalide, veuillez réessayer");
            UserMenu.displayManageInventory(this);
        }
    }

@Override
public void deleteInventory() {
    Scanner scanner = new Scanner(System.in);

    Map<String, Integer> componentCountMap = new HashMap<>();
    String[] componentTypes = {"CPU", "Roue", "Bras", "Hélice", "Caméra", "Haut-Parleur", "Microphone", "Écran"};

    for (String type : componentTypes) {
        componentCountMap.put(type, 0);
    }

    for (Component component : this.getStorage()) {
        System.out.println(component.getName());
        for (String type : componentTypes) {
            if (component.getName().contains(type)) {
                componentCountMap.put(type, componentCountMap.get(type) + 1);
                break;
            }
        }
    }

    try {
        System.out.println("Entrez le numéro de la composante que vous voulez enlever");
        for (int i = 0; i < componentTypes.length; i++) {
            System.out.println("[" + i + "] " + componentTypes[i]);
        }
        System.out.println("[8] Retour");

        String input = scanner.nextLine();
        int choice = Integer.parseInt(input);

        if (choice >= 0 && choice < componentTypes.length) {
            String selectedType = componentTypes[choice];
            if (componentCountMap.get(selectedType) < 1) {
                System.out.println("Vous n'avez pas de " + selectedType + " à enlever.");
            } else {
                for (Component component : this.getStorage()) {
                    if (component.getName().equals(selectedType)) {
                        this.getStorage().remove(component);
                        break;
                    }
                }
            }
        } else if (choice == 8) {
            UserMenu.displayManageInventory(this);
        } else {
            System.out.println("Entrée non connue, veuillez réessayer");
        }
    } catch (Exception e) {
        System.out.println("Entrée Invalide, veuillez réessayer");
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