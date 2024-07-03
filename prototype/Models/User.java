package Models;
import java.util.ArrayList;
import java.util.UUID;
import Views.ActivityMenu;

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

    public void addActivity(Activity activity){
        this.activities.add(activity);
        ActivityMenu.displayManageActivities(this);
    }
	public void removeActivity(Activity activity) {
        for (int i = 0; i < this.activities.size(); i++) {
            if (this.activities.get(i).equals(activity)) {
                this.activities.remove(i);
            }
        }
	}

    public void addFollower(User user){
        this.Followers.add(user);
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

	public boolean changePassword(String oldPass, String newPass) {
        return true;
	}

    public void userData(){
        System.out.println("first name: " + this.firstName);
        System.out.println("last name: " + this.lastName);
        System.out.println("username: " + this.userName);
        System.out.println("password: " + this.password);
        System.out.println("userID: " + this.userID);
        System.out.println("email: " + this.email);
        System.out.println("company name: " + this.companyName);
        System.out.println("phone number: " + this.phoneNumber);
        System.out.println("wallet: " + this.wallet);
        System.out.println("robot fleet: " + (this.RobotFleet).toString());
        System.out.println("interests: " + this.interests);
        System.out.println("activities: " + this.activities);
        System.out.println("Followers: " + this.Followers);
        System.out.println("Following: " + this.Following);

    }

    public void changeProfile(){

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
