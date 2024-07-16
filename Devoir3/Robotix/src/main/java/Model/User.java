package Model;

import Model.RobotFleet;
import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UUID id;
    private String email;
    private String companyName;
    private String phoneNumber;
    private ArrayList<User> followers;
    private ArrayList<User> followings;
    private RobotFleet robotFleet;
    private ArrayList<Activity> myActivities;

    public User(){}

    public User(String firstName,String lastName,String username, String password, String email,
                String companyName, String phoneNumber){

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.id = UUID.randomUUID();
        this.email = email;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.followers = new ArrayList<User>();
        this.followings = new ArrayList<User>();

    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
