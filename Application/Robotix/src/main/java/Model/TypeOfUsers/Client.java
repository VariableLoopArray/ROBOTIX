package Model.TypeOfUsers;
import Model.*;

import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDate;

/**
 * The Client class represents a client user, extending the User class.
 * It contains a fleet of robots, a list of activities, and a list of interests
 * of the user.
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
                  ArrayList<String> myInterests, boolean toggleEmail, String confirmationLink){
        super(firstName, lastName, username, password, email, companyName, phoneNumber, new ArrayList<Component>(),
                new ArrayList<String>(),new ArrayList<String>(), toggleEmail, confirmationLink);
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
     * Adds an activity to the list of activities.
     */
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
