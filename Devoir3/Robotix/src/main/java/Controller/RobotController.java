//package Controller;
//
//import Model.Activity;
//import Model.Robot;
//import Model.Task;
//import Model.TypeOfUsers.Client;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class RobotController {
//    public VBox DisplayRobots;
//
//    @FXML
//    private Label noRobotList;
//    @FXML
//    private Label robotWelcome;
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
//
//    @FXML
//    Client client;
//    @FXML
////    public void initialize() {
////        System.out.println("ActivityController initialized: " + (activityWelcome != null));
////    }
//
//    public void displayMessage(String message, boolean isError) {
//        if (!isError) {
//            robotWelcome.setText(message);
//        } else {
//            robotWelcome.setText("Error: " + message);
//        }
//    }
//    public void setUserActivity(Client client){
//        this.client = client;
//        displayMessage("Welcome to your robots!", false);
//    }
//
//    public void displayRobots(Client client){
//        DisplayRobot.setSpacing(10);
////        Button buttonAdd = new Button("Add");
////        DisplayActivities.getChildren().add(buttonAdd);
//        AtomicInteger numberOfActivity = new AtomicInteger();
//        ArrayList<Integer> numbersRemoved = new ArrayList<Integer>();
//        for (int i = 0; i < client.getMyActivities().size(); i++) {
//
//            VBox everything = new VBox(10);
//            Robot robot = client.getMyRobots().get(i);
//            //VBox activityAndModify = new VBox(1);
//            Label newRobot = new Label(robot.getName());
//
//            TextArea newTask = new TextArea();
//            newTask.setVisible(false);
//            newTask.setPrefHeight(10);
//            newTask.setMaxWidth(550);
//
//            //activityAndModify.getChildren().addAll(newActivity, newTask);
//
//            newRobot.getStyleClass().add("activities");
//
//
//            HBox buttonBox = new HBox(5);
//
//            Button buttonRemove = new Button("Remove");
//            int index = i;
//            buttonRemove.setOnAction((actionEvent -> buttonRemove(actionEvent, index, numbersRemoved)));
//
//            Button buttonConfirm = new Button("Confirm");
//            buttonConfirm.setVisible(false);
//
//            buttonConfirm.setOnAction((actionEvent -> buttonConfirm(buttonConfirm, newTask, numberOfActivity.get())));
//
//            Button buttonModify = new Button("Modify");
//            buttonModify.setOnAction((actionEvent) -> {
//                numberOfActivity.set(buttonModify(index, newTask, buttonConfirm));
//            });
//
//
//            buttonBox.getChildren().addAll(buttonRemove, buttonModify, buttonConfirm);
//
//            everything.getChildren().addAll(newRobot, buttonBox, newTask);
//
//            DisplayRobots.getChildren().add(everything);
//
//            //DisplayActivities.getChildren().add(buttonBox);
//        }
//        if (client.getMyRobots().size() == 0){
//            noRobotList.setText("You have no activities");
//        }
//
//    }
//
//    private void buttonConfirm(Button buttonConfirm, TextArea newTask, int activityPlace) {
//
//        ArrayList<Integer> taskIndex = new ArrayList<Integer>();
//        ArrayList<Integer> instructionsIndex = new ArrayList<Integer>();
//
//        if (!buttonConfirm.isVisible()){
//            return;
//        }
//        buttonConfirm.setVisible(false);
//        newTask.setVisible(false);
//        String[] newText = newTask.getText().split("\n");
//        for (int i = 0; i < newText.length; i++){
//            if (newText[i].contains("task name is")){
//                taskIndex.add(i);
//            }
//            else if (newText[i].contains("instructions are ")){
//                instructionsIndex.add(i);
//            }
//
//            newText[i] = newText[i].replaceAll("task name is ", "").replaceAll
//                    ("instructions are ", "");
//
//        }
//
//        for (int i = 0; i < taskIndex.size(); i++){
//            client.getMyActivities().get(activityPlace).getTasks().get(i).setName(newText[taskIndex.get(i)]);
//        }
//        //System.out.println(Arrays.toString(newText));
//        for (int j = 0; j < taskIndex.size(); j++){
//            for (int i = 0; i < client.getMyActivities().get(activityPlace).getTasks().size(); i++) {
//                if (j == 0) {
//                    client.getMyActivities().get(activityPlace).getTasks().get(j).
//                            getInstructions().set(i, newText[instructionsIndex.get(i)]);
//                }
//                else{
//                    client.getMyActivities().get(activityPlace).getTasks().get(j).
//                            getInstructions().set(i, newText[instructionsIndex.get(i + taskIndex.get(j) - 1 )]);
//                }
//            }
//            //  System.out.println(client.getMyActivities().get(activityPlace).getTasks().get(0).getInstructions().toString());
//        }
//
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){
//
//            Client [] Clients = gson.fromJson(reader, Client[].class);
//            for (int i = 0; i < Clients.length; i++){
//                //System.out.println("this is client id " + client.getId() + " and this is the clientlist Id " + Clients[i].getId());
//                //System.out.println(i + " this is " + (Clients[i].getId().equals()client.getId()));
//                if (Clients[i].getId().equals(client.getId())){
//                    Clients[i] = client;
//                    break;
//                }
//            }
//            try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")){
//                gson.toJson(Clients, writer);
//            }
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    private int buttonModify (int index, TextArea newTask, Button buttonConfirm) {
//        VBox modifyBox = (VBox) DisplayActivities.getChildren().get(index);
//        Label modify = (Label) modifyBox.getChildren().get(0);
//        String modifyText = modify.getText();
//        int activityPlace = -1;
//
//        for (Activity modifyNode : client.getMyActivities()) {
//            activityPlace++;
//            if (modifyNode.getName().equals(modifyText)) {
//
//                String tasks = "";
//                for (Task task : modifyNode.getTasks()) {
//                    tasks += "task name is " + task.getName() + "\n";
//                    for (String instructions : task.getInstructions()) {
//                        tasks += "instructions are " + instructions + "\n";
//                    }
//                }
//
//                newTask.setText(tasks);
//                newTask.setVisible(true);
//                buttonConfirm.setVisible(true);
//                return activityPlace;
//
//            }
//        }
//        return activityPlace;
//    }
//
//    private void buttonRemove (ActionEvent event, int index, ArrayList<Integer> numbersRemoved){
//
//        int smaller= 0;
//        for (int number : numbersRemoved){
//            if (number < index){
//                smaller++;
//            }
//        }
//
//        VBox removeBox = (VBox) DisplayActivities.getChildren().get(index-smaller);
//        DisplayActivities.getChildren().remove(removeBox);
//        Label remove = (Label) removeBox.getChildren().get(0);
//        String removeText = remove.getText();
//        numbersRemoved.add(index);
//
//        for (Activity removeNode : client.getMyActivities()) {
//            if (removeNode.getName().equals(removeText)) {
//                client.getMyActivities().remove(removeNode);
//                break;
//            }
//        }
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){
//
//            Client [] Clients = gson.fromJson(reader, Client[].class);
//            for (int i = 0; i < Clients.length; i++){
//                //System.out.println("this is client id " + client.getId() + " and this is the clientlist Id " + Clients[i].getId());
//                //System.out.println(i + " this is " + (Clients[i].getId().equals()client.getId()));
//                if (Clients[i].getId().equals(client.getId())){
//                    Clients[i] = client;
//                    break;
//                }
//            }
//            try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")){
//                gson.toJson(Clients, writer);
//            }
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void createActivity() {
//        try {
//            ArrayList<String> interests = new ArrayList<String>();
//            for (int i = 1; i <= 10; i++) {
//                CheckBox interest = (CheckBox) getClass().getDeclaredField("Interest" + i).get(this);
//                if (interest.isSelected()) {
//                    interests.add(interest.getText());
//                }
//            }
//            Activity newActivity = new Activity(activityName.getText(),null , activityStartDate.getText(), activityEndDate.getText(),
//                    activityPoints.getText(), interests, client, new ArrayList<Task>(), activityDescription.getText(), "Not Started");
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            String json = gson.toJson(newActivity);
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
//
//
//    public void handleGoBack() {
//        try {
//            Stage stage = (Stage) activityWelcome.getScene().getWindow();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
//            Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
//            homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Activity.css").toExternalForm());
//            homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
//            stage.setTitle("Homepage");
//            HomepageController homepageController = fxmlLoader.getController();
//            homepageController.setUserHomepage(client);
//            stage.setScene(homepageMenu);
//            stage.show();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//}
