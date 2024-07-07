package Models;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import Views.UserMenu;
public class Client extends User{
    private ArrayList<Component> inventory;
    private ArrayList<String> interests;
    
    public Client(String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber,float wallet ,RobotFleet RobotFleet, ArrayList<Component> inventory,
    ArrayList<String>interests, ArrayList<Activity> activities, ArrayList<User> Followers, 
    ArrayList<User> Following, ArrayList<Order> orders,ArrayList<String> notifs){

        super( firstName, lastName,  username,  password, userID, email, companyName, phoneNumber, wallet, RobotFleet,
        activities, Followers, Following, orders, notifs);
        this.inventory = inventory;
        this.interests = interests;
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
        System.out.println("\nIntérêts: ");
        for (String interest : this.interests){
            System.out.println(interest);
        }

    }

    @Override
    public void deleteInterest(String interest) {
        this.interests.remove(interest);
    }

    @Override
	public void addInterest(String interest) {
		this.interests.add(interest);
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

        System.out.println(CPUCount + " CPU");
        System.out.println(roueCount + " Roues");
        System.out.println(brasCount + " Bras");
        System.out.println(heliceCount + " Hélices");
        System.out.println(cameraCount + " Caméras");
        System.out.println(hautParleurCount + " Haut-Parleurs");
        System.out.println(microCount + " Microphone");
        System.out.println(ecranCount + " Écran");

    }

    @Override
    public void deleteInventory() {
    if (this.getInventory().isEmpty()) {
        System.out.println("Votre inventaire est vide");
    } else {
        System.out.println("Voici votre inventaire: ");
        for (Component component : this.getInventory()) {
            System.out.println(component.getName());
        }
        
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> componentCountMap = new HashMap<>();
        String[] componentTypes = {"CPU", "Roue", "Bras", "Hélice", "Caméra", "Haut-Parleur", "Microphone", "Écran"};
        
        for (String type : componentTypes) {
            componentCountMap.put(type, 0);
        }

        for (Component component : this.getInventory()) {
            for (String type : componentTypes) {
                if (component.getName().contains(type)) {
                    componentCountMap.put(type, componentCountMap.get(type) + 1);
                    break;
                }
            }
        }

        try {
            System.out.println("Entrez le numéro de la composante que vous voulez enlever une fois:");
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
                    for (Component component : this.getInventory()) {
                        if (component.getName().equals(selectedType)) {
                            this.getInventory().remove(component);
                            break;
                        }
                    }
                }
            } else if (choice == 8) {
                UserMenu.displayManageInventory(this);
            } else {
                System.out.println("Entrée non connue, veuillez réessayez");
                UserMenu.displayManageInventory(this);
            }
            
        } catch (Exception e) {
            System.out.println("Entrée Invalide, veuillez réessayer");
            UserMenu.displayManageInventory(this);
        }
    }
    
    UserMenu.displayManageInventory(this);
}
    
    public void showOrders(){
        for (Order order : this.getOrders()){
            System.out.println("ID de la commande: " + order.getOrderNumber());
            System.out.println("Date de la commande: " + order.getOrderDate());
            System.out.println("Date de livraison: " + order.getArrivalDate());
            System.out.println("Composantes: ");
            for (Component component : order.getComponents()){
                System.out.println(component.getName());
            }
            System.out.println("Statut: " + order.getStatus());
            System.out.println("Fournisseur: " + order.getSupplier().getUserName()+"\n\n\n");
        }
    }



    @Override
    public ArrayList<String> getInterests(){
        return interests;
    }
    @Override
    public void setInterests(ArrayList<String> interests){
        this.interests = interests;
    }
    @Override
    public ArrayList<Component> getInventory(){
        return inventory;
    }
    public void setInventory(ArrayList<Component> inventory){
        this.inventory = inventory;
    }
}