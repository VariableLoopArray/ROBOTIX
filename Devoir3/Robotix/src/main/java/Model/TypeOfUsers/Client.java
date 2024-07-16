package Model.TypeOfUsers;
import Model.Activity;
import Model.Order;
import Model.RobotFleet;
import Model.User;

import java.util.ArrayList;

public class Client extends User{
    private RobotFleet fleet;
    private ArrayList<Activity> myActivities;
    private ArrayList<String> Interests;
    private ArrayList<Order> orders;

    public Client(String firstName,String lastName,String username, String password, String email,
                  String companyName, String phoneNumber, RobotFleet fleet, ArrayList<Activity> myActivities,
                  ArrayList<String> Interests){
        super(firstName, lastName, username, password, email, companyName, phoneNumber);
        this.fleet = fleet;
        this.myActivities = new ArrayList<Activity>();
        this.Interests = Interests;
        this.orders = new ArrayList<Order>();
    }
}
