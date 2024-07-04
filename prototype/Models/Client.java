package Models;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
public class Client extends User{

    private ArrayList<String> interests;
    private ArrayList<Order> orders;
    
    public Client(String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber,float wallet ,RobotFleet RobotFleet,ArrayList<String> interests, ArrayList<Activity> activities, 
    ArrayList<User> Followers, ArrayList<User> Following, ArrayList<Order> orders){

        super( firstName, lastName,  username,  password, userID, email, companyName, phoneNumber, wallet, RobotFleet,
        activities, Followers, Following);
        this.interests = interests;
        this.orders = orders;
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
        System.out.println("\nCommandes: ");
        for (Order order : this.orders){
            System.out.println(order.getOrderNumber());
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




    @Override
    public ArrayList<String> getInterests(){
        return interests;
    }
    @Override
    public void setInterests(ArrayList<String> interests){
        this.interests = interests;
    }
    public ArrayList<Order> getOrders(){
        return orders;
    }
    public void setOrders(ArrayList<Order> orders){
        this.orders = orders;
    }

    
}