package Controller;

import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import Model.User;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HomepageController {
    @FXML
    public StackPane clientPane;
//    public StackPane supplierPane;
    @FXML
    private Label messageLabel1;
    @FXML
    private Label messageLabel2;
    @FXML
    private HBox menuProfile;
    private Client client;
    private Supplier supplier;
    public HomepageController() {
    }
    @FXML
    public void initialize(){
        menuProfile.alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
        menuProfile.setPadding(new Insets(10, 10, 10, 10));
    }


    public void setClientHomepage(Client client){
        this.client = client;
        displayMessage("Welcome " + client.getUsername() + "!", false);
    }

    public void setSupplierHomepage(Supplier supplier){
        this.supplier = supplier;
        displayMessage("Welcome " + supplier.getUsername() + "!", false);
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
            Scene profileScene = new Scene(fxmlLoader.load(), 1024, 768);
            profileScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            profileScene.getStylesheets().add(getClass().getResource("/CssFiles/Profile.css").toExternalForm());
            ProfileController profileController = fxmlLoader.getController();
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


}
