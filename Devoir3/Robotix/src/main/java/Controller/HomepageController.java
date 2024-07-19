package Controller;

import Model.TypeOfUsers.Client;
import Model.User;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


public class HomepageController {
    public StackPane client;
    public StackPane supplier;
    @FXML
    private Label messageLabel1;
    @FXML
    private Label messageLabel2;
    @FXML
    private HBox menuProfile;
    private User user;
    public HomepageController() {
    }
    @FXML
    public void initialize(){
        menuProfile.alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
        menuProfile.setPadding(new Insets(10, 10, 10, 10));
    }


    public void setUserHomepage(User user){
        this.user = user;
        displayMessage("Welcome " + user.getUsername() + "!", false);
    }
    public void displayMessage(String message, boolean isError) {
        if (!isError) {
            messageLabel1.setText(message);
        } else {
            messageLabel2.setText(message);
        }
    }
    public void goToMyProfile(){
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/ProfileMenu.fxml"));
            Scene profileScene = new Scene(fxmlLoader.load(), 1920, 1080);
            profileScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            profileScene.getStylesheets().add(getClass().getResource("/CssFiles/Profile.css").toExternalForm());
            ProfileController profileController = fxmlLoader.getController();
            stage.setTitle("My Profile");
            stage.setScene(profileScene);
            stage.show();
            stage.setFullScreen(true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initializeActivity() throws IOException {
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/ActivityMenuMenu.fxml"));
            Scene activityScene = new Scene(fxmlLoader.load(), 1920, 1080);
            activityScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            activityScene.getStylesheets().add(getClass().getResource("/CssFiles/Activity.css").toExternalForm());
            ActivityController activityController = fxmlLoader.getController();
            activityController.setClientActivity((Client) user);
            activityController.displayActivities((Client) user);
            stage.setTitle("My Activities");
            stage.setScene(activityScene);
            stage.show();
            stage.setFullScreen(true);
        }
        catch (IOException e) {
            e.printStackTrace();        }
    }

}
