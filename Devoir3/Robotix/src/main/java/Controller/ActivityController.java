package Controller;
import Model.Activity;
import Model.Robot;
import Model.TypeOfUsers.Client;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class ActivityController {
    @FXML
    private Label activityWelcome;
    @FXML
    private Label Activity1;
    Client client;
    @FXML
//    public void initialize() {
//        System.out.println("ActivityController initialized: " + (activityWelcome != null));
//    }

    public void displayMessage(String message, boolean isError) {
        if (!isError) {
            activityWelcome.setText(message);
        } else {
            activityWelcome.setText("Error: " + message);
        }
    }
    public void setUserActivity(Client client){
        this.client = client;
        displayMessage("Welcome " + "!", false);
    }

    public void displayActivities(Client client){
        for (Activity activity : client.getMyActivities()) {
            Activity1.setText(client.getFleet().get(0).getName() + "hello");
        }
    }
    public void activityGoBack() {
        try {
            Stage stage = (Stage) activityWelcome.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
            homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Activity.css").toExternalForm());
            homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            stage.setTitle("Homepage");
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setClientHomepage(client);
            stage.setScene(homepageMenu);
            stage.show();
            stage.setFullScreen(true);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
