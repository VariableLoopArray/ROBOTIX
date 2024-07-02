import java.util.ArrayList;
import java.util.UUID;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    private UUID userID;
    private String email;
    private String companyName;
    private String phoneNumber;
    

    private RobotFleet RobotFleet;
    private ArrayList<String> interests;
    private ArrayList<Activity> activities;
    private ArrayList<User> Followers;
    private ArrayList<User> Following; 

    public User( String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber, RobotFleet RobotFleet, ArrayList<Activity> activities, 
    ArrayList<User> Followers, ArrayList<User> Following ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = username;
        this.password = password;

        this.userID = userID;
        this.email = email;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;

        this.RobotFleet = RobotFleet;
        this.activities = activities;
        this.Followers = Followers;
        this.Following = Following; 
    }
    public void addFollower(User user){
        this.Followers.add(user);
    }

    public void unfollowFollower(User user) {
        this.Followers.remove(user);
    }

    public void removeFollower(User user){
        this.Following.remove(user);
    }
    public void deleteInterest(String interest) {
        this.interests.remove(interest);
    }
	public void addInterest(String interest) {
		this.interests.add(interest);
	}
	public void removeActivity(Activity activity) {
		this.activities.remove(activity);
	}
	public boolean changePassword(String oldPass, String newPass) {
        return true;
	}
    
}
