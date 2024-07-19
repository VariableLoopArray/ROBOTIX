package Controller;

import Model.TypeOfUsers.Client;
import Model.User;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HomepageController {
    public StackPane client;
    public StackPane supplier;
    @FXML
    private Label messageLabel1;
    @FXML
    private Label messageLabel2;
    private User user;
    public HomepageController() {
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
    public void initializeActivity() throws IOException {
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/ActivityMenu.fxml"));
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
