package Controller;

import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import Model.User;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HomepageController {
    @FXML
    private Label messageLabel1;
    @FXML
    private HBox menuProfile;
    @FXML
    private Button activityMenu;
    @FXML
    private Button robotMenu;
    @FXML
    private Button shopMenu;
    @FXML
    private Button notificationMenu;
    private Client client;
    private Supplier supplier;

    public HomepageController() {
    }
    @FXML
    public void initialize(){
        menuProfile.alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
        menuProfile.setPadding(new Insets(10, 10, 10, 10));
        if (client != null) {
            activityMenu.setVisible(true);
            robotMenu.setVisible(true);
            shopMenu.setVisible(true);
            notificationMenu.setVisible(true);
        } else if (supplier != null) {
            activityMenu.setVisible(false);
            robotMenu.setVisible(false);
            activityMenu.managedProperty().bind(activityMenu.visibleProperty());
            robotMenu.managedProperty().bind(robotMenu.visibleProperty());

        }
    }


    public void setUserHomepage(User user){
        if (user instanceof Client) {
            client = (Client) user;
        } else if (user instanceof Supplier) {
            supplier = (Supplier) user;
        }
        messageLabel1.setText("Welcome"+ user.getUsername()+ "!");
    }

    public void goToMyProfile(){
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/ProfileMenu.fxml"));
            Scene profileScene = new Scene(fxmlLoader.load(), 1024, 768);
            profileScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            profileScene.getStylesheets().add(getClass().getResource("/CssFiles/Profile.css").toExternalForm());
            ProfileController profileController = fxmlLoader.getController();
            if (client != null) {
                profileController.setUser(client);
            } else if (supplier != null) {
                profileController.setUser(supplier);
            }
            profileController.displayUserInfo();
            stage.setTitle("My Profile");
            stage.setScene(profileScene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initializeActivity() throws IOException {
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/ActivityMenu.fxml"));
            Scene activityScene = new Scene(fxmlLoader.load(), 1024, 768);
            activityScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            activityScene.getStylesheets().add(getClass().getResource("/CssFiles/Activity.css").toExternalForm());
            ActivityController activityController = fxmlLoader.getController();
            activityController.setUserActivity(client);
            activityController.displayActivities(client);
            stage.setTitle("My Activities");
            stage.setScene(activityScene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();        }
    }


    public void Logout(){
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/loginMenu.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load(), 720, 540);
            loginScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            loginScene.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
            stage.setTitle("Login");
            stage.setScene(loginScene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
