package Models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

import Models.typeOfRobots.AerialRobot;
import Models.typeOfRobots.AquaticRobot;
import Models.typeOfRobots.TerrestrialRobot;
import Views.ActivityMenu;
import Views.RobotFleetMenu;

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

    public User( String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber,float wallet, RobotFleet RobotFleet, ArrayList<Activity> activities, ArrayList<User> Followers, ArrayList<User> Following ){
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


    public ArrayList<String> getInterests(){
        return null;
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
    
    public User(){

    }
}
