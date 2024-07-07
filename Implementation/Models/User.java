package Models;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.UUID;

import Database.Database;
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
    private ArrayList<Activity> activities;
    private ArrayList<User> Followers;
    private ArrayList<User> Following; 
    private ArrayList<Order> orders;
    private ArrayList<String> notifs;

    public User( String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber,float wallet, RobotFleet RobotFleet,
    ArrayList<Activity> activities, ArrayList<User> Followers, ArrayList<User> Following, ArrayList<Order> orders, ArrayList<String> notifs
    ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = username;
        this.password = password;

        this.userID = userID;
        this.email = email;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.wallet = wallet;

        this.RobotFleet = RobotFleet;
        this.activities = activities;
        this.Followers = Followers;
        this.Following = Following; 
        this.orders = orders;
        this.notifs = notifs;


    }

    public void showOrders(){
    }

    public void addActivity(Activity activity){
        this.activities.add(activity);
        ActivityMenu.displayManageActivities(this);
    }
	public void removeActivity(Activity activity) {
        this.activities.remove(activity);
        ActivityMenu.displayManageActivities(this);

	}
    public void deleteInterest(String interest) {
    }
    public void addInterest(String interest) {
    }

    public void userData(){
    }

    public void showInventory(){
        
    }
    public void deleteInventory(){

    }

    public ArrayList<Component> getInventory(){
        return new ArrayList<Component>();
    }

    public ArrayList<String> getInterests(){
        return new ArrayList<String>();
    }
    public void setInterests(ArrayList<String> interests){
    }

    public void addFollower(User user){
        this.Followers.add(user);
    }

    public void removeFollower(User user){
        this.Following.remove(user);
    }

    public void addMoney(double money){
        this.wallet += money;
    }
	public boolean changePassword(String oldPass, String newPass) {
        return true;
	}

    public void changeProfile(){

    }

    public void displayNotifs(){

        for (Robot robot : this.RobotFleet.getRobots()){
            if (robot.getBattery() < 1){
                System.out.println(robot.getName() + " n'a plus de batterie.");
            }
        }

        for (Activity activity : this.activities){
            if ((activity.getEndDate().minusDays(5).isBefore(Database.getTime()) || activity.getEndDate().isEqual(Database.getTime())) && activity.getEndDate().isAfter(Database.getTime())){
                if (ChronoUnit.DAYS.between(activity.getEndDate(), Database.getTime()) == 0){
                System.out.println(activity.getName() + " Cette activité s'achève aujourd'hui ");}
                else{
                    System.out.println(activity.getName() + " Cette activité s'achève dans " + ChronoUnit.DAYS.between(Database.getTime(),activity.getEndDate()) + " jour");
                }
            }
        }

        for (String notif : this.getNotifs()){
            if (notif.contains("ActivityInterest")){
                String activityName = notif.split(" ")[1];
                System.out.println("Une activité qui correspond à vos intérêts a été créé | " + activityName );
            }

            else if(notif.contains("NewFollower")){
                String folowerName = notif.split(" ")[1];
                System.out.println("Vous avez un nouveau follower | " + folowerName );
            }

            else if (notif.contains("ActivityFollower")){
                String activityFollowerName = notif.split(" ")[1];
                System.out.println("Quelqu'un a ajouté l'une de vos activités | "+ activityFollowerName );
            }
            else if (this instanceof Supplier){
                if (notif.contains("bought")){
                    String componentName = notif.split(" ")[1];
                    String clientName = notif.split(" ")[2];
                    System.out.println("Un client a acheté une de vos composantes | Composante achetée: " + componentName +" | Nom du Client: " + clientName);
                }
            }
        }

        

        


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
        this.Followers = followers;
    }



    public ArrayList<User> getFollowing() {
        return Following;
    }



    public void setFollowing(ArrayList<User> following) {
        Following = following;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
    
    public ArrayList<String> getNotifs() {
        return notifs;
    }

    public void setNotifs(ArrayList<String> notifs) {
        this.notifs = notifs;
    }

    
    public User(){

    }

}
