package Model;

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
    private ArrayList<Component> Storage;

    public User(){}

    public User(String firstName,String lastName,String username, String password, String email,
                String companyName, String phoneNumber, ArrayList<Component> Storage){

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.id = UUID.randomUUID();
        this.email = email;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.Storage = Storage;

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
    public ArrayList<Component> getStorage() {
        return Storage;
    }
    public void setStorage(ArrayList<Component> storage) {
        Storage = storage;
    }


}
