package Model.TypeOfUsers;
import Model.*;

import java.util.ArrayList;
import java.util.UUID;


/**
 * The Client class represents a client user, extending the User class.
 * It contains a fleet of robots, a list of activities, and a list of interests
 * of the user.
 * @Dawson
 */
public class Client extends User{
    //List of Robots
    private ArrayList<Robot> fleet;
    //List of Activities
    private ArrayList<Activity> myActivities;
    //List of user interest
    private ArrayList<String> myInterests;


    public Client(String firstName,String lastName,String username, String password, String email,
                  String companyName, String phoneNumber, ArrayList<Robot> fleet,
                  ArrayList<String> myInterests){
        super(firstName, lastName, username, password, email, companyName, phoneNumber, new ArrayList<Component>(), new ArrayList<String>());
        this.fleet = fleet;
        this.myActivities = new ArrayList<Activity>();
        this.myInterests = myInterests;
    }


    /**
     * Gets the fleet of robots.
     * @return The fleet of robots.
     */
    public ArrayList<Robot> getFleet() {
        return fleet;
    }

    /**
     * Sets the fleet of robots.
     */
    public void setFleet(ArrayList<Robot> fleet) {
        this.fleet = fleet;
    }

    /**
     * Gets the list of activities.
     * @return The list of activities.
     */
    public ArrayList<Activity> getMyActivities() {
        return myActivities;
    }

    /**
     * Sets the list of activities.
     */
    public void setMyActivities(ArrayList<Activity> myActivities) {
        this.myActivities = myActivities;
    }

    /**
     * Gets the list of interests.
     * @return The list of interests.
     */
    public ArrayList<String> getMyInterests() {
        return myInterests;
    }

    /**
     * Sets the list of interests.
     */
    public void setInterests(ArrayList<String> myinterests) {
        myInterests = myinterests;
    }

/*    public void addRobot(Robot robot){
        myRoborts.add(robot);
    }*/

    /**
     * Adds an activity to the list of activities.
     */
    public void addActivity(Activity activity){
        myActivities.add(activity);
    }

    /**
     * Gets  list of activity IDs.
     * @return  list of activity IDs.
     */
    public ArrayList<UUID> getActivitiesId(){
        ArrayList<UUID> activitiesId = new ArrayList<UUID>();
        for (Activity activity: myActivities){
            activitiesId.add(activity.getActivityID());
        }
        return activitiesId;
    }
}
