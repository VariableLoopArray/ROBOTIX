package Model.TypeOfUsers;
import Model.*;

import java.util.ArrayList;

public class Client extends User{
    private ArrayList<Robot> fleet;
    private ArrayList<Activity> myActivities;
    private ArrayList<String> Interests;
    private ArrayList<Order> orders;

    public Client(String firstName,String lastName,String username, String password, String email,
                  String companyName, String phoneNumber, ArrayList<Robot> fleet, ArrayList<Activity> myActivities,
                  ArrayList<String> Interests){
        super(firstName, lastName, username, password, email, companyName, phoneNumber);
        this.fleet = fleet;
        this.myActivities = new ArrayList<Activity>();
        this.Interests = Interests;
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

    public ArrayList<String> getInterests() {
        return Interests;
    }

    public void setInterests(ArrayList<String> interests) {
        Interests = interests;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
