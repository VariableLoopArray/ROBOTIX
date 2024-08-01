package Controller;

import Model.Activity;
import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


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
    private Button componentMenu;
    @FXML
    private Button shopMenu;
    @FXML
    private Button notificationMenu;
    @FXML
    private FlowPane robotixActivities;
    private Client client;
    private Supplier supplier;

    public HomepageController() {
    }
    @FXML
    public void displayCorrectMenu(){
        menuProfile.alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
        menuProfile.setPadding(new Insets(10, 10, 10, 10));
        if (client != null) {
            activityMenu.setVisible(true);
            robotMenu.setVisible(true);
            componentMenu.setVisible(false);
            shopMenu.setVisible(true);
            notificationMenu.setVisible(true);
            componentMenu.managedProperty().bind(componentMenu.visibleProperty());
        } else if (supplier != null) {
            componentMenu.setVisible(true);
            activityMenu.setVisible(false);
            robotMenu.setVisible(false);
            activityMenu.managedProperty().bind(activityMenu.visibleProperty());
            robotMenu.managedProperty().bind(robotMenu.visibleProperty());

        }
    }

    public void displayRobotixActivities(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        robotixActivities.getChildren().clear();
        Type activityListType = new TypeToken<List<Activity>>(){}.getType();

        try(FileReader fileReader = new FileReader("src/main/JsonFiles/activities.json")) {
            List<Activity> activities = gson.fromJson(fileReader, activityListType);

            if (activities == null) {
                activities = new ArrayList<>();
            }
            for(Activity activity : activities){

                if (!client.getActivitiesId().contains(activity.getActivityID())) {
                    VBox activityContainer = new VBox();
                    HBox activitySubContainer = new HBox();
                    Label activityName = new Label("Name: " + activity.getName());
                    activityContainer.getChildren().add(activityName);
                    activityContainer.getChildren().add(activitySubContainer);
                    activityName.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
                    VBox activityInfo = new VBox();
                    activitySubContainer.getChildren().add(activityInfo);
                    Label startDate = new Label("Start Date: " + activity.getStartDate());
                    activityInfo.getChildren().add(startDate);
                    Label endDate = new Label("End Date: " + activity.getEndDate());
                    activityInfo.getChildren().add(endDate);
                    Label points = new Label("Points: " + activity.getPoints());
                    activityInfo.getChildren().add(points);
                    Label description = new Label("Description: " + activity.getDescription());
                    activityInfo.getChildren().add(description);

                    Button joinButton = new Button("Join");
                    joinButton.getStyleClass().add("joinButton");
                    activityContainer.getChildren().add(joinButton);
                    joinButton.setOnAction(e -> {
                        client.addActivity(activity);

                        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
                        Type clientListType = new TypeToken<List<Client>>(){}.getType();
                        try(FileReader fileReader1 = new FileReader("src/main/JsonFiles/client.json")) {
                            List<Client> clients = gson1.fromJson(fileReader1, clientListType);
                            if (clients == null) {
                                clients = new ArrayList<>();
                            }
                            for (Client c : clients) {
                                if (c.getId().equals(client.getId())) {
                                    c.setMyActivities(client.getMyActivities());
                                    break;
                                }
                            }
                            try (FileWriter fileWriter = new FileWriter("src/main/JsonFiles/client.json")) {
                                gson.toJson(clients, fileWriter);
                            } catch (IOException w) {
                                w.printStackTrace();
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                        displayRobotixActivities();

                    });
                    activityContainer.setSpacing(20);
                    activityContainer.setPadding(new Insets(10, 10, 10, 10));
                    activityContainer.getStyleClass().add("vboxContainer");
                    activityName.getStyleClass().add("activityInfo");
                    startDate.getStyleClass().add("activityInfo");
                    endDate.getStyleClass().add("activityInfo");
                    points.getStyleClass().add("activityInfo");
                    description.getStyleClass().add("activityInfo");


                    robotixActivities.getChildren().add(activityContainer);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setUserHomepage(User user){
        if (user instanceof Client) {
            client = (Client) user;
        } else if (user instanceof Supplier) {
            supplier = (Supplier) user;
        }
        messageLabel1.setText("Welcome "+ user.getUsername()+ "!");
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
    public void goToMyActivity() throws IOException {
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

    public void goToMyComponent() {
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/MyComponentsMenu.fxml"));
            Scene componentScene = new Scene(fxmlLoader.load(), 1024, 768);
            componentScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            componentScene.getStylesheets().add(getClass().getResource("/CssFiles/Component.css").toExternalForm());
            ComponentController componentController = fxmlLoader.getController();
            componentController.setUserComponent(supplier);
            componentController.displayComponents(supplier);
            stage.setTitle("My Components");
            stage.setScene(componentScene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void gotoRobot() throws IOException {
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/RobotMenu.fxml"));
            Scene robotScene = new Scene(fxmlLoader.load(), 1024, 768);
            robotScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            robotScene.getStylesheets().add(getClass().getResource("/CssFiles/Robot.css").toExternalForm());
            RobotController robotController = fxmlLoader.getController();
            robotController.setUserRobot(client);
//            robotController.displayRobots(client);
            stage.setTitle("My Robots");
            stage.setScene(robotScene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();        }
    }
    public void goToShop(){
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/ShopMenu.fxml"));
            Scene shopScene = new Scene(fxmlLoader.load(), 1024, 768);
            shopScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            shopScene.getStylesheets().add(getClass().getResource("/CssFiles/Shop.css").toExternalForm());
            ShopController shopController = fxmlLoader.getController();
            shopController.setUserShop(client);
            stage.setTitle("Robotix Shop");
            stage.setScene(shopScene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
