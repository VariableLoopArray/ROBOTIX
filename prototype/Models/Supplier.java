package Models;
import java.util.ArrayList;
import java.util.UUID;
public class Supplier extends User{

    private int productionCapacity;
    private ArrayList<Component> storage;
    
    public Supplier(String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber, float wallet, RobotFleet RobotFleet,ArrayList<Activity> activities, 
    ArrayList<User> Followers, ArrayList<User> Following, int productionCapacity, ArrayList<Component> storage){

        super( firstName, lastName,  username,  password, userID, email, companyName, phoneNumber,wallet, RobotFleet, 
        activities, Followers, Following );
        this.productionCapacity = productionCapacity;
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
    
    public ArrayList<Component> getStorage(){
        return storage;
    }

    public void setStorage(ArrayList<Component> storage){
        this.storage = storage;
    }
}