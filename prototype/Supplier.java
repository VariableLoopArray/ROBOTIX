import java.util.ArrayList;
import java.util.UUID;
public class Supplier extends User{

    private int productionCapacity;
    private ArrayList<Component> storage;
    
    public Supplier(String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber, RobotFleet RobotFleet, ArrayList<Activity> activities, 
    ArrayList<User> Followers, ArrayList<User> Following, int productionCapacity, ArrayList<Component> storage){

        super( firstName, lastName,  username,  password, userID, email, companyName, phoneNumber, RobotFleet, activities, Followers, Following );
        this.productionCapacity = productionCapacity;
        this.storage = storage;
    }
}