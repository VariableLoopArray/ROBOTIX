package Controller;
import Model.Activity;
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
    public void setClientActivity(Client client){
        this.client = client;
        displayMessage("Welcome " + "!", false);
    }

    public void displayActivities(Client client){
        System.out.println(client.getMyActivities());
    }
}
