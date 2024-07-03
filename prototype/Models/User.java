package Models;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;
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
    private ArrayList<String> interests;
    private ArrayList<Activity> activities;
    private ArrayList<User> Followers;
    private ArrayList<User> Following; 

    public User( String firstName, String lastName, String username, String password,
    UUID userID, String email, String companyName, String phoneNumber,float wallet, RobotFleet RobotFleet,
    ArrayList<Activity> activities, ArrayList<User> Followers, ArrayList<User> Following ){
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

    public void addRobot(){
        String name;
        Scanner scanner = new Scanner(System.in);
            while (true){

                System.out.println("Entrer le nom de votre robot\n");
                name = scanner.nextLine();
                boolean nameTaken = false;

                if(name.equals("quitter")){
                    System.out.println("nom invalide, réessayez un autre nom\n");
                    nameTaken = true;
                }

                for (Robot robot : this.getRobotFleet().getRobots()){
                    if (robot.getName().equals(name)){
                        System.out.println("nom déjà pris, svp réessayez\n");
                        nameTaken = true;
                    }
                }
                if (!nameTaken){
                    break;
                }
                
            }

            System.out.println("Entrer le type de votre robot\n"); 
            String type = scanner.nextLine();

            this.getRobotFleet().add(new Robot(name, type, new ArrayList<Component>(), UUID.randomUUID(),
            0,new double[] {0.0,0.0,0.0}, 0, 0, 0));

            System.out.println("Le robot a été créé avec succès\n");
            RobotFleetMenu.displayManageRobotFleet(this);
    }

    public void deleteRobot(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Entrer le nom du robot que vous voulez enlever " + 
                "\nou quitter pour quitter");
        while (true){
            String nameToRemove = scanner.nextLine();
            if (nameToRemove.equals("quitter")){
                break;
            }
            boolean nameInList = false;
            Iterator<Robot> robotIterator = this.getRobotFleet().getRobots().iterator();
            while (robotIterator.hasNext()){
                Robot robot = robotIterator.next();
                if (robot.getName().equals(nameToRemove)){
                    robotIterator.remove();
                    nameInList = true;
                }
            }
            if (nameInList){
                break;
            }
            System.out.println("the nom n'est pas dans votre liste de robots, svp réessyez\n");
        
        }
        RobotFleetMenu.displayManageRobotFleet(this);
    }

    public void lookRobot(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du robot que vous voulez examiner\n");
                String searchName = scanner.nextLine();
                boolean inList = false;
                for (Robot robot : this.getRobotFleet().getRobots()){
                    if (robot.getName().equals(searchName)){
                        robot.robotData();
                        inList = true;
                        break;
                    }
                }
                if (!inList){
                    System.out.println("Vous n'avez pas de robot avec ce nom.\n");
                }
                RobotFleetMenu.displayManageRobotFleet(this);
    }

    public void addMoney(double money){
        this.wallet += money;
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
