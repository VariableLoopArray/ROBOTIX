package Controller;

import Model.Activity;
import Model.Component;
import Model.Robot;
import Model.Task;
import Model.TypeOfUsers.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
    @FXML
    public Label RobotWelcome;
    @FXML
    public VBox DisplayRobots;
    @FXML
    public GridPane Informations;
    @FXML
    public TextField name;
    @FXML
    public TextField type;
    @FXML
    public TextField components;
    @FXML
    public TextField battery;
    @FXML
    public TextField loc;
    @FXML
    public TextField speed;
    @FXML
    public TextField cpuusage;
    @FXML
    public TextField memory;
    @FXML
    public TextField serialNumber;
    @FXML
    public Button create;
    @FXML
    public Button affiche;

//    @FXML
//    private Label noRobotList;
//    @FXML
//    private TextArea robotDescription;

    Client client;

    public void displayMessage(String message, boolean isError) {
        //RobotWelcome.getStylesheets(Message);
        if (!isError) {
            RobotWelcome.setText(message);
            RobotWelcome.getStyleClass().add("default-text");
            RobotWelcome.getStyleClass().remove("error-text");
        } else {
            RobotWelcome.setText("Error: " + message);
            RobotWelcome.getStyleClass().add("error-text");
            RobotWelcome.getStyleClass().remove("default-text");
        }
    }
    public void setUserRobot(Client client){
        this.client = client;
        displayMessage("Welcome to your robots!", false);
    }
    public void showRobot(ActionEvent actionEvent) {
        Label robotlist = new Label();
        robotlist.getStyleClass().add("label-robotlist");
        DisplayRobots.getChildren().add(robotlist);
        afficherRobot(actionEvent,robotlist);

/*      GridPane gridPane = new GridPane();
        gridPane.add(robotlist, 0, 0);
        GridPane.setHAlignment(robotlist, HPos.CENTER); // 设置水平对齐方式
        gridPane.setAlignment(Pos.CENTER);*/
        //create.setAlignment(Pos.CENTER);
        //affiche.setAlignment(Pos.CENTER);
    }

    public void afficherRobot(ActionEvent actionEvent,Label robotlist) {
        affiche.setVisible(false);
        affiche.setManaged(false);
        create.setVisible(true);
        create.setManaged(true);

        String robot = "";
        Label norobot = new Label("You have no robot!");
        if(client.getFleet().size() == 0){
            //robotlist.setText("You have no robot!");
            norobot.getStyleClass().add("label-no-robots");
            DisplayRobots.getChildren().add(norobot);
            return;
        }
        else{
            norobot.setVisible(false);
            norobot.setManaged(false);
            //DisplayRobots.getChildren().remove(norobot);
        }

        try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Client [] Clients = gson.fromJson(reader, Client[].class);
            for (int i = 0; i < Clients.length; i++){
                if (Clients[i].getId().equals(client.getId())){
                    for(Robot unit : Clients[i].getFleet()){
                        robot += "Robot Name : " + unit.getName() + "\n";
                        robot += "Robot Type : " + unit.getType() + "\n";
                        robot += "Robot Battery : " + unit.getBattery() + "\n";
                        robot += "Robot Speed : " + unit.getSpeed() + "\n";
                        robot += "Robot CpuUsage : " + unit.getCpuUsage() + "\n";
                        robot += "Robot Memory : " + unit.getMemory() + "\n";
                        robot += "Robot SerialNumber : " + unit.getSerialNumber() + "\n";
                        robot += "Robot Components : " + unit.getComponents() + "\n";
                        robot += "Robot Location : " + unit.getLocation()[0]+ unit.getLocation()[1] + unit.getLocation()[2] + "\n";
                        robot += "\n";
                    }
                    client = Clients[i];
                    break;
                }
            }
            robotlist.setText(robot);
            DisplayRobots.getChildren().add(robotlist);


        }
        catch (IOException ec){
            ec.printStackTrace();
        }
    }


    public void confirmRobot(ActionEvent actionEvent,Label robotlist) {
        // 点击确认按钮后，把之前已经显示的robot列表删除，robotlist是否要对全局应用 ！！（当列表为空时不显示内容，正常时候会一直显示）
        Robot robot = new Robot(name.getText(), type.getText(),new ArrayList<Component>(), battery.getText(),
                new float[] {0.0f, 0.0f, 0.0f}, Float.valueOf(speed.getText()),Float.valueOf(cpuusage.getText()),Float.valueOf(memory.getText()));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        client.getFleet().add(robot);
        System.out.println(name.getText());
        Informations.setVisible(false);
        Label success = new Label("Robot created successfully!");
        success.getStyleClass().add("label-success");
        DisplayRobots.getChildren().add(success);

        try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){
            Client [] Clients = gson.fromJson(reader, Client[].class);
            for (int i = 0; i < Clients.length; i++){
                if (Clients[i].getId().equals(client.getId())){
                    Clients[i] = client;
                    break;
                }
            }
            try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")){
                gson.toJson(Clients, writer);
            }
        }
        catch (IOException ec){
            ec.printStackTrace();
        }
/*        Button afficher = new Button("Show My Robots");
        afficher.getStyleClass().add("button-show");
        afficher.setOnAction(e -> afficherRobot(e,robotlist));
        DisplayRobots.getChildren().add(afficher);*/

        success.setManaged(true); //点击确认后显示添加成功
        affiche.setManaged(true);
        affiche.setVisible(true);
        // TODO需显示showlist按钮！！！！
        //success.setManaged(false); TODO并更改文字为显示"Here is your robot list"的label

        //afficherRobot(actionEvent,robotlist);

    }
    public void creatRobot(ActionEvent actionEvent) {
        //.clear();
        affiche.setManaged(false); // 尝试当点击create时隐藏showlist按钮
        affiche.setVisible(false); // 并隐藏robot列表
        Label robotlist = new Label();
        Informations.setVisible(true); // Show the informations
        create.setVisible(false); // Hide the create button
        create.setManaged(false); // Hide the create button
/*        affiche.setManaged(false); // Hide the affiche button
        affiche.setVisible(false); // Hide the affiche button  无用 */
        Button confirm = new Button("Confirm");
        confirm.getStyleClass().add("button-confirm");
        confirm.setOnAction(e -> confirmRobot(e,robotlist));
        Informations.getChildren().add(confirm);
        Informations.setRowIndex(confirm, 9);
        Informations.setColumnIndex(confirm, 1);

        //afficherRobot(actionEvent,robotlist); 不可用，每点一次create就重新显示一次列表

        String robot = "";
        Label norobot = new Label("You have no robot!");
        if(client.getFleet().size() == 0){
            //robotlist.setText("You have no robot!");
//            norobot.getStyleClass().add("label-no-robots");
//            DisplayRobots.getChildren().add(norobot);
            norobot.setVisible(false);
            norobot.setManaged(false);
        }
        else{
            norobot.setVisible(false);
            norobot.setManaged(false);
            DisplayRobots.getChildren().remove(norobot);
        }
    }


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
            homepageController.displayCorrectMenu();
            homepageController.displayRobotixActivities();
            stage.setScene(homepageMenu);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
