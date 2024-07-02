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
    private float wallet;
    

    private RobotFleet RobotFleet;
    private ArrayList<String> interests;
    private ArrayList<Activity> activities;
    private ArrayList<User> Followers;
    private ArrayList<User> Following; 

    public User( String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber,float wallet, RobotFleet RobotFleet, ArrayList<Activity> activities, 
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

    public void printUserDetails() {
        System.out.println("User ID: " + userID);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Username: " + userName);
        System.out.println("Email: " + email);
        System.out.println("Company: " + companyName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println();
    }





    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getUserName() {
        return userName;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public UUID getUserID() {
        return userID;
    }



    public void setUserID(UUID userID) {
        this.userID = userID;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getCompanyName() {
        return companyName;
    }



    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }



    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public float getWallet() {
        return wallet;
    }



    public void setWallet(float wallet) {
        this.wallet = wallet;
    }



    public RobotFleet getRobotFleet() {
        return RobotFleet;
    }



    public void setRobotFleet(RobotFleet robotFleet) {
        RobotFleet = robotFleet;
    }



    public ArrayList<String> getInterests() {
        return interests;
    }



    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }



    public ArrayList<Activity> getActivities() {
        return activities;
    }



    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }



    public ArrayList<User> getFollowers() {
        return Followers;
    }



    public void setFollowers(ArrayList<User> followers) {
        Followers = followers;
    }



    public ArrayList<User> getFollowing() {
        return Following;
    }



    public void setFollowing(ArrayList<User> following) {
        Following = following;
    }
    
    public User(){

    }
}
