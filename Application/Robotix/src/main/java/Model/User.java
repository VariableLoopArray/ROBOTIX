package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The User class represents a user.
 * It contains the first name, last name, username, password, email, company name, phone number, storage, notifications, email inbox, toggle email, and confirmation link of the user.
 */
public class User {
    /**The first name of the user*/
    private String firstName;

    /**The last name of the user*/
    private String lastName;

    /**The username of the user*/
    private String username;

    /**The password of the user*/
    private String password;

    /**The ID of the user*/
    private UUID id;
    /**The email  of the user*/
    private String email;

    /**The company name of the user.*/
    private String companyName;

    /**The phone number of the user*/
    private String phoneNumber;

    /**The storage components of the  user.*/
    private ArrayList<Component> storage;
    /**The notifications of the user*/
    private ArrayList<String> notifications;
    /**The email Inbox list of the user*/
    private ArrayList<String> emailInbox;
    private boolean toggleEmail;
    private String confirmationLink;

    public User(){

    }

    /**Constructor of the user*/
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

    /**
     * Gets the username of the user.
     * @return the  username of the user
     */
    public String getUsername(){
        return username;
    }

    /**
     * Gets the password of the user.
     * @return password of the user
     */
    public String getPassword(){
        return password;
    }
    /**
     * Sets the username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Sets the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email of the user.
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the first name of the user.
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * Gets the last name of the user.
     * @return the first name of the user
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Gets the id of the user.
     * @return the id of the user
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the first name of the user.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the company name of the user.
     * @return the company name of the user
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name of the user.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets the phone number of the user.
     * @return the phone number of the user
     */

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the list of storage the user.
     * @return the list of storage of the user
     */
    public ArrayList<Component> getStorage() {
        return storage;
    }

    /**
     * Sets the storage list of the user.
     */
    public void setStorage(ArrayList<Component> storage) {
        this.storage = storage;
    }

    /**
     * Gets the list of notifications the user.
     * @return the notification's list of the user
     */
    public ArrayList<String> getNotifications() {
        return notifications;
    }


    /**
     * Gets the list of email inbox the user.
     * @return the list of email inbox of the user
     */
    public ArrayList<String> getEmailInbox() {
        return emailInbox;
    }

    /**
     * gets the email inbox state to receive notifications through email.
     */
    public boolean isToggleEmail() {
        return toggleEmail;
    }
    /**
     * Sets the email inbox to receive notifications through email.
     */
    public void setToggleEmail(boolean toggleEmail) {
        this.toggleEmail = toggleEmail;
    }
    /**
     * Gets the confirmation link of the user.
     * @return the confirmation link of the user
     */
    public String getConfirmationLink() {
        return confirmationLink;
    }
    /**
     * Sets the confirmation link of the user.
     */
    public void setConfirmationLink(String confirmationLink) {
        this.confirmationLink = confirmationLink;
    }


}
