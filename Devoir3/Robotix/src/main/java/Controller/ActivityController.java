package Controller;
import Model.Activity;
import Model.Robot;
import Model.TypeOfUsers.Client;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class ActivityController {
    public VBox DisplayActivities;
    @FXML
    private Label activityWelcome;
    @FXML
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
        displayMessage("Welcome to your activities!", false);
    }

    public void displayActivities(Client client){
        DisplayActivities.setSpacing(10);
        for (int i = 0; i < client.getMyActivities().size(); i++) {
            Activity activity = client.getMyActivities().get(i);
            Label newActivity = new Label(activity.getName());
            newActivity.getStyleClass().add("activities");
            HBox buttonBox = new HBox(10);
            Button buttonRemove = new Button("Remove");
            buttonRemove.setOnAction(this::buttonRemove, i);
            Button button2 = new Button("hello2");
            Button button3 = new Button("hello3");
            buttonBox.getChildren().addAll(buttonRemove, button2, button3);
            DisplayActivities.getChildren().add(newActivity);
            DisplayActivities.getChildren().add(buttonBox);
        }

    }

    private void buttonRemove(ActionEvent event, int i) {
        Button button = (Button) event.getSource();
        HBox hbox = (HBox) button.getParent();
        Label label = (Label) hbox.getParent().getChildrenUnmodifiable().get(0);
        String activityName = label.getText();
        client.getMyActivities().remove(activityName);

        for (Activity activity : client.getMyActivities()) {
        System.out.println(activity.getName());
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


        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
