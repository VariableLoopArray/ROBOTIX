package Controller;

import Model.Robot;
import Model.TypeOfUsers.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.*;
import java.util.ArrayList;

/**
 * This is the controller that manages user actions in RobotMenu
 */
public class RobotController {

    @FXML
    private Label RobotWelcome;
    @FXML
    private FlowPane DisplayRobots;
    @FXML
    private GridPane tableInfo;
    @FXML
    private TextField name;
    @FXML
    private TextField type;
    @FXML
    private Label noRobotList;
    @FXML
    private TextField battery;
    @FXML
    private TextField speed;
    @FXML
    private TextField cpuusage;
    @FXML
    private TextField memory;
    @FXML
    private TextField components;
    @FXML
    private Button create;
    @FXML
    private Button affiche;
    @FXML
    private Button supprime;
    @FXML
    private Label errorMessage;
    @FXML
    private ScrollPane ScrollPaneRobots;
    @FXML
    private Client client;
    private Gson gson;

    public RobotController() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     *
     * @param client put the client into the controller
     */
    public void setUserRobot(Client client) {
        this.client = client;
        RobotWelcome.setText("Welcome to your robots !");
    }


    /**
     * when the button Show Robot is clicked this method is called, it serves to display the robots
     */
    @FXML
    private void displayRobots() {
//        errorMessage.setVisible(false);
//        errorMessage.setManaged(false);
//        supprime.setVisible(false);
//        supprime.setManaged(false);
        DisplayRobots.getChildren().clear();
//        affiche.setVisible(false);
//        affiche.setManaged(false);
//        create.setVisible(true);
//        create.setManaged(true);
//        tableInfo.setVisible(false);
//        tableInfo.setManaged(false);

        if (client.getFleet().isEmpty()) {
            Label noRobotLabel = new Label("You have no robot!");
            noRobotLabel.getStyleClass().add("label-no-robots");
            DisplayRobots.getChildren().add(noRobotLabel);
            return;
        }

        try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
            Client[] clients = gson.fromJson(reader, Client[].class);
            for (Client client : clients) {
                if (client.getId().equals(this.client.getId())) {
                    VBox robotBoxContainer = new VBox(10);
                    for (Robot robot : client.getFleet()) {
                        createRobotBox(robot, robotBoxContainer);
                    }
                    DisplayRobots.getChildren().add(robotBoxContainer);
                    this.client = client;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScrollPaneRobots.setVisible(false);
        ScrollPaneRobots.setManaged(false);
    }

    /**
     * This method is called to create a box that contains all the informations of each robot
     * @param robot The robot whose informations is being displayed
     * @param robotBoxContainer The container in which the robot will be displayed
     */
    private void createRobotBox(Robot robot, VBox robotBoxContainer) {
        VBox robotBox = new VBox(10);
        robotBox.setMaxWidth(200);;
        Label robotInfoLabel = new Label(getRobotInfo(robot));
        robotInfoLabel.setWrapText(true);

        Button removeButton = new Button("Remove");

        removeButton.setOnAction(event -> {
            client.getFleet().remove(robot);
            updateClientJson();
            robotBoxContainer.getChildren().remove(robotBox);
            DisplayRobots.getChildren().remove(robotBox);
        });

        robotBox.getChildren().addAll(robotInfoLabel, removeButton);
        robotBox.setPadding(new javafx.geometry.Insets(10));
        robotBoxContainer.getChildren().add(robotBox);
        robotBox.getStyleClass().add("robotBox");
        DisplayRobots.getChildren().add(robotBox);
    }

    /**
     * This method returns the information of the robot
     * @param robot The robot whose information is being returned.
     * @return
     */
    private String getRobotInfo(Robot robot) {
        return String.format("Robot Name: %s%nRobot Type: %s%nRobot Battery: %s%%%nRobot Speed: %sm/s%n" +
                        "Robot CpuUsage: %s%%%nRobot Memory: %sGB%nRobot Components: %s%n" +
                        "Robot Location: %s%nSerialNumber: %s%n",
                robot.getName(), robot.getType(), Integer.parseInt(robot.getBattery()), robot.getSpeed(),
                (int)robot.getCpuUsage(), robot.getMemory(), robot.getComponents(),
                formatLocation(robot.getLocation()), robot.getSerialNumber());
    }

    private String formatLocation(float[] location) {
        return IntStream.range(0, location.length)
                .mapToObj(i -> Float.toString(location[i]))
                .collect(Collectors.joining(", "));
    }


    /**
     * This method is called when creating a robot and the confirm button is pressed.
     * The method creates a robot and adds it to the client list of robots.
     */
    public void confirmRobot() {
        try {
            ArrayList<String> allComponents = new ArrayList<>();
            String[] components = this.components.getText().replace(" ", "").split(",");
            Collections.addAll(allComponents, components);
            Robot newRobot = new Robot(
                    name.getText(), type.getText(), allComponents,
                    battery.getText(), new float[]{0.0f, 0.0f, 0.0f},
                    Float.parseFloat(speed.getText()), Float.parseFloat(cpuusage.getText()),
                    Float.parseFloat(memory.getText())
            );
            client.getFleet().add(newRobot);
            updateClientJson();
            tableInfo.setVisible(false);
            errorMessage.setVisible(false);
            errorMessage.setManaged(false);
            displaySuccessMessage("Robot created successfully!");

        } catch (NumberFormatException e) {
            errorMessage.setText("Please enter valid inputs!");
            errorMessage.setManaged(true);
            errorMessage.setVisible(true);
        }
    }

    private void updateClientJson() {
        try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
            Client[] clients = gson.fromJson(reader, Client[].class);
            for (int i = 0; i < clients.length; i++) {
                if (clients[i].getId().equals(client.getId())) {
                    clients[i] = client;
                    break;
                }
            }
            try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                gson.toJson(clients, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void displaySuccessMessage(String message) {
        Label successLabel = new Label(message);
        successLabel.getStyleClass().add("label-success");
        DisplayRobots.getChildren().add(successLabel);
        clearTextFields();
        affiche.setManaged(true);
        affiche.setVisible(true);
    }

    private void clearTextFields() {
        for (Node node : tableInfo.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).clear();
            }
        }
    }

    /**
     * This method is called when clicking the create robot button, it serves to display the fields to create a robot.
     */
    public void createRobot() {
        DisplayRobots.getChildren().clear();
        tableInfo.setVisible(true);
//        create.setVisible(false);
//        create.setManaged(false);
//        affiche.setVisible(true);
//        affiche.setManaged(true);
//        supprime.setVisible(false);
//        supprime.setManaged(false);
        ScrollPaneRobots.setVisible(true);
        ScrollPaneRobots.setManaged(true);

        Button confirmButton = new Button("Confirm & Save");
        confirmButton.getStyleClass().add("button-confirm");
        confirmButton.setOnAction(e -> confirmRobot());

        tableInfo.add(confirmButton, 1, 7);
    }

    /**
     * This method is called when clicking the Show Partial button, it serves to
     * display limited information about the robots, such as its name, type and battery
     */

    public void showPartial() {
        DisplayRobots.getChildren().clear();
        if (client.getFleet().isEmpty()) {
            Label noRobotLabel = new Label("You have no robot!");
            noRobotLabel.getStyleClass().add("label-no-robots");
            DisplayRobots.getChildren().add(noRobotLabel);
            return;
        }

        try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
            Client[] clients = gson.fromJson(reader, Client[].class);
            for (Client client : clients) {
                if (client.getId().equals(this.client.getId())) {
                    VBox robotBoxContainer = new VBox(10);
                    for (Robot robot : client.getFleet()) {
                        VBox robotBox = new VBox(10);
                        robotBox.setMaxWidth(150);
                        robotBox.setMaxHeight(225);
                        String newRobot = String.format("Robot Name: %s%nRobot Type: %s%nRobot Battery: %s%%%n",
                                robot.getName(), robot.getType(), Integer.parseInt(robot.getBattery()));

                        Label robotInfoLabel = new Label(newRobot);
                        robotInfoLabel.setWrapText(true);

                        Button removeButton = new Button("Remove");

                        removeButton.setOnAction(event -> {
                            client.getFleet().remove(robot);
                            updateClientJson();
                            robotBoxContainer.getChildren().remove(robotBox);
                            DisplayRobots.getChildren().remove(robotBox);
                        });

                        robotBox.getChildren().addAll(robotInfoLabel, removeButton);
                        robotBox.setPadding(new javafx.geometry.Insets(10));
                        robotBoxContainer.getChildren().add(robotBox);
                        robotBox.getStyleClass().add("robotBox");
                        DisplayRobots.getChildren().add(robotBox);
                    }
                    DisplayRobots.getChildren().add(robotBoxContainer);
                    this.client = client;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScrollPaneRobots.setVisible(false);
        ScrollPaneRobots.setManaged(false);

    }

    /**
     * This method is called when pressing the go back button, it takes the user back to the Homepage Menu
     */
    public void handleGoBack() {
        try {
            Stage stage = (Stage) RobotWelcome.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene scene = new Scene(loader.load(), 1024, 768);
            scene.getStylesheets().remove(getClass().getResource("/CssFiles/Robot.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());

            stage.setTitle("Homepage");
            HomepageController homepageController = loader.getController();
            homepageController.setUserHomepage(client);
            homepageController.displayCorrectMenu();
            homepageController.displayRobotixActivities();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TEST FUNCTIONS
    public String GetRobotInfoTest(Robot robot){
        String result = getRobotInfo(robot);
        return result;
    }
}