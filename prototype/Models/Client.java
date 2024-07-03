package Models;
import java.util.ArrayList;
import java.util.UUID;
public class Client extends User{

    private ArrayList<String> interests;
    private ArrayList<Order> orders;
    
    public Client(String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber,float wallet ,RobotFleet RobotFleet,ArrayList<String> interests, ArrayList<Activity> activities, 
    ArrayList<User> Followers, ArrayList<User> Following, ArrayList<Order> orders){

        super( firstName, lastName,  username,  password, userID, email, companyName, phoneNumber, wallet, RobotFleet, interests,
        activities, Followers, Following);
        this.orders = orders;
    }

    
}