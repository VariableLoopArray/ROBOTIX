package Controller;

import Model.Activity;
import Model.Component;
import Model.Robot;
import Model.Task;
import Model.TypeOfUsers.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class RobotController {
//    @FXML
//    public VBox DisplayRobots;
    @FXML
    public Label RobotWelcome;
//    @FXML
//    public GridPane robotGrid;
//    @FXML
//    public CheckBox Robot1;
//    @FXML
//    public CheckBox Robot2;
//    @FXML
//    public CheckBox Robot3;
//    @FXML
//    public CheckBox Robot4;
//    @FXML
//    public CheckBox Robot5;
//    @FXML
//    public CheckBox Robot6;
//    @FXML
//    public CheckBox Robot7;
//    @FXML
//    public CheckBox Robot8;
//    @FXML
//    public CheckBox Robot9;
//    @FXML
//    public CheckBox Robot10;
//
//    @FXML
//    private Label noRobotList;
//    @FXML
//    private TextField robotName;
//    @FXML
//    private TextField type;
//    @FXML
//    private TextField components;
//    @FXML
//    private TextField serialNumber;
//    @FXML
//    private TextField battery;
//    @FXML
//    private TextField location;
//    @FXML
//    private TextField speed;
//    @FXML
//    private TextField cpuUsage;
//    @FXML
//    private TextField memory;
//
//    @FXML
//    private TextArea robotDescription;

    Client client;

//    public void initialize() {
//        System.out.println("ActivityController initialized: " + (activityWelcome != null));
//    }

    public void displayMessage(String message, boolean isError) {
        if (!isError) {
            RobotWelcome.setText(message);
        } else {
            RobotWelcome.setText("Error: " + message);
        }
    }
    public void setUserRobot(Client client){
        this.client = client;
        displayMessage("Welcome to your robots!", false);
    }

//    public void displayRobots(Client client){
//        DisplayRobots.setSpacing(10);
////        Button buttonAdd = new Button("Add");
////        DisplayActivities.getChildren().add(buttonAdd);
//        AtomicInteger numberOfActivity = new AtomicInteger();
//        ArrayList<Integer> numbersRemoved = new ArrayList<Integer>();
//        for (int i = 0; i < client.getMyActivities().size(); i++) {
//
//            VBox everything = new VBox(10);
//            Robot robot = client.getFleet().get(i);
//            //VBox activityAndModify = new VBox(1);
//            Label newRobot = new Label(robot.getName());
//
//            //activityAndModify.getChildren().addAll(newActivity, newTask);
//
//            newRobot.getStyleClass().add("activities");
//
//
//            HBox buttonBox = new HBox(5);
//
//
//            DisplayRobots.getChildren().add(everything);
//
//            //DisplayActivities.getChildren().add(buttonBox);
//        }
//        if (client.getFleet().size() == 0){
//            noRobotList.setText("You have no activities");
//        }
//
//    }

//    public void createRobot() {
//        try {
//            ArrayList<String> robots = new ArrayList<String>();
//            for (int i = 1; i <= 10; i++) {
//                CheckBox robot = (CheckBox) getClass().getDeclaredField("Robot" + i).get(this);
//                if (robot.isSelected()) {
//                    robots.add(robot.getText());
//                }
//            }
//
//
//
////String name,String type,ArrayList< Component >components, String battery, float[] location, float speed, float cpuUsage, float memory
//
//            Robot newRobot = new Robot(robotName.getText(),type.getText(), new ArrayList<Component>(),
//                    battery.getText(), new float[]{0.0f, 0.0f, 0.0f},Float.valueOf(speed.getText()),Float.valueOf(cpuUsage.getText()),Float.valueOf(memory.getText()));
//
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            String json = gson.toJson(newRobot);
//            try (Writer writer = new FileWriter("src/main/JsonFiles/activities.json")) {
//                writer.write(json);
//            }
//
//
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException | IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public void handleGoBack() {
        try {
            Stage stage = (Stage) RobotWelcome.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
            homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Robot.css").toExternalForm());
            homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            stage.setTitle("Homepage");
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setUserHomepage(client);
            stage.setScene(homepageMenu);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
//
//    public void setUserActivity(Client client) {
//    }
}
