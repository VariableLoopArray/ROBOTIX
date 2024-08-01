package Model.TypeOfUsers;
import Model.*;

import java.util.ArrayList;
import java.util.UUID;

public class Client extends User{
    private ArrayList<Robot> fleet;
    private ArrayList<Activity> myActivities;
    private ArrayList<String> myInterests;
    private ArrayList<Order> orders;

    public Client(String firstName,String lastName,String username, String password, String email,
                  String companyName, String phoneNumber, ArrayList<Robot> fleet,
                  ArrayList<String> myInterests){
        super(firstName, lastName, username, password, email, companyName, phoneNumber);
        this.fleet = fleet;
        this.myActivities = new ArrayList<Activity>();
        this.myInterests = myInterests;
        this.orders = new ArrayList<Order>();
    }

    public ArrayList<Robot> getFleet() {
        return fleet;
    }

    public void setFleet(ArrayList<Robot> fleet) {
        this.fleet = fleet;
    }

    public ArrayList<Activity> getMyActivities() {
        return myActivities;
    }

    public void setMyActivities(ArrayList<Activity> myActivities) {
        this.myActivities = myActivities;
    }

    public ArrayList<String> getMyInterests() {
        return myInterests;
    }

    public void setInterests(ArrayList<String> myinterests) {
        myInterests = myinterests;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

/*    public void addRobot(Robot robot){
        myRoborts.add(robot);
    }*/

    public void addActivity(Activity activity){
        myActivities.add(activity);
    }
    public ArrayList<UUID> getActivitiesId(){
        ArrayList<UUID> activitiesId = new ArrayList<UUID>();
        for (Activity activity: myActivities){
            activitiesId.add(activity.getActivityID());
        }
        return activitiesId;
    }
}
