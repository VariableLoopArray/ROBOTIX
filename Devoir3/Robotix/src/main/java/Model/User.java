package Model;

import java.time.LocalDate;
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
    private ArrayList<Component> storage;
    private ArrayList<String> notifications;
    private ArrayList<String> emailInbox;
    private boolean toggleEmail;
    private String confirmationLink;

    public User(){}

    public User(String firstName,String lastName,String username, String password, String email,
                String companyName, String phoneNumber, ArrayList<Component> storage, ArrayList<String> notifications, ArrayList<String> emailInbox,
                boolean toggleEmail, String confirmationLink){

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.id = UUID.randomUUID();
        this.email = email;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.storage = storage;
        this.notifications = notifications;

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
        return storage;
    }
    public void setStorage(ArrayList<Component> storage) {
        this.storage = storage;
    }
    public ArrayList<String> getNotifications() {
        return notifications;
    }
    public void setNotifications(ArrayList<String> notifications) {
        this.notifications = notifications;
    }
    public ArrayList<String> getEmailInbox() {
        return emailInbox;
    }
    public void setEmailInbox(ArrayList<String> emailInbox) {
        this.emailInbox = emailInbox;
    }
    public boolean isToggleEmail() {
        return toggleEmail;
    }
    public void setToggleEmail(boolean toggleEmail) {
        this.toggleEmail = toggleEmail;
    }
    public String getConfirmationLink() {
        return confirmationLink;
    }
    public void setConfirmationLink(String confirmationLink) {
        this.confirmationLink = confirmationLink;
    }


}
