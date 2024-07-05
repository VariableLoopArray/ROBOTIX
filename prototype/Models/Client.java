package Models;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

import Views.UserMenu;
public class Client extends User{
    private ArrayList<Component> inventory;
    private ArrayList<String> interests;
    
    public Client(String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber,float wallet ,RobotFleet RobotFleet, ArrayList<Component> inventory,
    ArrayList<String>interests, ArrayList<Activity> activities, ArrayList<User> Followers, 
    ArrayList<User> Following, ArrayList<Order> orders){

        super( firstName, lastName,  username,  password, userID, email, companyName, phoneNumber, wallet, RobotFleet,
        activities, Followers, Following, orders);
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
                                    for (Component component : this.inventory){
                                        if (component.getName().equals("CPU")){
                                            this.inventory.remove(component);
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
                                    for (Component component : this.inventory){
                                        if (component.getName().equals("Roue")){
                                            this.inventory.remove(component);
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
                                    for (Component component : this.inventory){
                                        if (component.getName().equals("Bras")){
                                            this.inventory.remove(component);
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
                                    for (Component component : this.inventory){
                                        if (component.getName().equals("Hélice")){
                                            this.inventory.remove(component);
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
                                    for (Component component : this.inventory){
                                        if (component.getName().equals("Caméra")){
                                            this.inventory.remove(component);
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
                                    for (Component component : this.inventory){
                                        if (component.getName().equals("Haut-Parleur")){
                                            this.inventory.remove(component);
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
                                    for (Component component : this.inventory){
                                        if (component.getName().equals("Microphone")){
                                            this.inventory.remove(component);
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
                                    for (Component component : this.inventory){
                                        if (component.getName().equals("Écran")){
                                            this.inventory.remove(component);
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