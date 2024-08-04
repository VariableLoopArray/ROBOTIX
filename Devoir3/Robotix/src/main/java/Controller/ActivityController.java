package Controller;
import Model.Activity;
import Model.Task;
import Model.TypeOfUsers.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityController {
    public VBox DisplayActivities;
    @FXML
    public GridPane activityGrid;
    @FXML
    public Button create;
    @FXML
    public VBox Tasks;
    @FXML
    private Label activityWelcome;
    @FXML
    private TextField activityName;
    @FXML
    private TextField activityStartDate;
    @FXML
    private TextField activityEndDate;
    @FXML
    private TextField activityPoints;
    @FXML
    private Label successLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField activityDescription;
    @FXML
    Client client;
    @FXML
    public void displayMessage(String message, boolean isError) {
        if (!isError) {
            activityWelcome.setText(message);
        } else {
            activityWelcome.setText("Error: " + message);
        }
    }
    public void setUserActivity(Client client){
        try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){
            Gson gson = new Gson();
            Client [] Clients = gson.fromJson(reader, Client[].class);
            for (int i = 0; i < Clients.length; i++){
                if (Clients[i].getId().equals(client.getId())){
                    this.client = Clients[i];
                    break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        displayMessage("Welcome to your activities !", false);
    }

    public void displayActivities(Client client){
        DisplayActivities.setSpacing(10);
        AtomicInteger numberOfActivity = new AtomicInteger();
        ArrayList<Integer> numbersRemoved = new ArrayList<Integer>();
        for (int i = 0; i < client.getMyActivities().size(); i++) {

            VBox everything = new VBox(10);
            Activity activity = client.getMyActivities().get(i);
            Label newActivity = new Label("Activity " + activity.getName());
            newActivity.getStyleClass().add("label-activity");
            TextArea newTask = new TextArea();
            newTask.getStyleClass().add("textarea");
            newTask.setManaged(false);
            newTask.setVisible(false);
            newTask.setPrefHeight(10);
            newTask.setMaxWidth(550);
            List<Integer> confirmPlaces = new ArrayList<Integer>();

            HBox buttonBox = new HBox(5);

            Button buttonRemove = new Button("Remove");
            int index = i;
            buttonRemove.setOnAction((actionEvent -> buttonRemove(actionEvent, index, numbersRemoved)));

            buttonRemove.getStyleClass().add("buttons");

            Button buttonAddTask = new Button("Add Task");
            buttonAddTask.setVisible(false);

            buttonAddTask.getStyleClass().add("buttons");

            Button buttonModify = new Button("Modify");
            buttonModify.setOnAction((actionEvent) -> {
                numberOfActivity.set(buttonModify(index, newTask, everything, buttonBox, confirmPlaces, buttonAddTask));
            });

            buttonModify.getStyleClass().add("buttons");


            Label description = new Label("Description : " + activity.getDescription());
            description.getStyleClass().add("label-description");
            buttonBox.getChildren().addAll(buttonRemove, buttonModify, buttonAddTask);
            everything.getChildren().addAll(newActivity, buttonBox, description);
            DisplayActivities.getChildren().add(everything);

        }

    }

    private void buttonConfirm(Button buttonConfirm, VBox everything, int activityPlace, List<Integer> confirmPlaces,
                               Button buttonAddTask, HBox buttonBox) {


        for (int e = 0; e < confirmPlaces.size(); e ++){

                TextArea newInstructions = (TextArea) everything.getChildren().get(confirmPlaces.get(e));
                TextArea newTask = (TextArea) everything.getChildren().get(confirmPlaces.get(e)-1);

                newInstructions.getStyleClass().add("textarea");
                newTask.getStyleClass().add("textarea");



                buttonConfirm.setVisible(false);
                newInstructions.setVisible(false);
                newInstructions.setManaged(false);

                String[] newTextTab = newInstructions.getText().split("\n");
                String newTaskTab = newTask.getText();

                newTaskTab = newTaskTab.replaceAll("task is ", "");


                client.getMyActivities().get(activityPlace).getTasks().get(e).setName(newTaskTab);

                List<String> newText = Arrays.asList(newTextTab);
                client.getMyActivities().get(activityPlace).getTasks().get(e).getInstructions().clear();

                for (int i = 0; i < newText.size(); i++) {
                    client.getMyActivities().get(activityPlace).getTasks().get(e).getInstructions().add(newText.get(i));
                }


        }

        if (everything.getChildren().size() > 3) {
            everything.getChildren().subList(3, everything.getChildren().size()).clear();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
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


        buttonAddTask.setVisible(false);
        buttonBox.getChildren().remove(buttonConfirm);


    }

    private int buttonModify (int index, TextArea newTask, VBox everything, HBox buttonBox, List<Integer> confirmPlaces,
                              Button buttonAddTask) {

        if (buttonAddTask.isVisible()){
            return -1;
        }
        confirmPlaces.clear();
        VBox modifyBox = (VBox) DisplayActivities.getChildren().get(index);
        Label modify = (Label) modifyBox.getChildren().get(0);
        String modifyText = modify.getText().replace("Activity ", "");
        AtomicInteger activityPlace = new AtomicInteger(-1);
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.getStyleClass().add("buttons");

        buttonAddTask.setVisible(true);
        for (int k = 0; k < client.getMyActivities().size(); k++) {

            int newI = k;
            activityPlace.incrementAndGet();

            if (client.getMyActivities().get(k).getName().equals(modifyText)) {


                ArrayList<Integer> removeList = new ArrayList<Integer>();

                int[] indexe = {-1};
                ArrayList<Integer> removedNumbers = new ArrayList<Integer>(); 
                int[] removeds = {-1};


                buttonAddTask.setOnAction((actionEvent -> {


                    client.getMyActivities().get(newI).getTasks().add(new Task("new task", new ArrayList<String>()));
                    Button buttonRemoveTask = new Button("Remove Task");
                    buttonRemoveTask.getStyleClass().add("button");
                    everything.getChildren().add(buttonRemoveTask);
                    int removeTaskPlace = everything.getChildren().size()-1;
                    removeList.add(removeTaskPlace);


                    buttonRemoveTask.setOnAction(event -> {

                        removeds[0]++;
                        indexe[0]++;
                        int captureInde = indexe[0];
                        int removed = 0;
                        for (int number : removedNumbers){
                            if (captureInde > number){
                                removed++;
                            }
                        }




                        removedNumbers.add(captureInde);

                        client.getMyActivities().get(newI).getTasks().removeLast();

                        for (int i = 0; i < 3; i++){
                            everything.getChildren().remove((int) removeList.get(captureInde - removed));
                        }

                        confirmPlaces.remove(Integer.valueOf(removeList.get(captureInde - removed) + 2));


                        for (int j = 0; j < confirmPlaces.size(); j++){

                            if (confirmPlaces.get(j) > removeList.get(captureInde - removed) + 1){
                                confirmPlaces.set(j, confirmPlaces.get(j) - 3);
                            }
                        }

                        for (int i = 0; i < removeList.size(); i++){
                            if (removeList.get(i) > removeList.get(captureInde - removed)){
                                removeList.set(i, removeList.get(i) - 3);
                            }
                        }
                        removeList.remove(removeList.get(captureInde - removed));

                    });


                    TextArea newTask1 = new TextArea("new task");
                    newTask1.getStyleClass().add("textarea");

                    newTask1.setMaxHeight(35);
                    newTask1.setMaxWidth(150);
                    TextArea newInstructions1 = new TextArea();

                    newInstructions1.getStyleClass().add("textarea");
                    newInstructions1.setMaxHeight(100);
                    newInstructions1.setMaxWidth(500);
                    everything.getChildren().addAll(newTask1, newInstructions1);
                    confirmPlaces.add(everything.getChildren().size() - 1);




                }));
                newTask.setVisible(true);
                newTask.setManaged(true);
                buttonBox.getChildren().add(buttonConfirm);
                int currentActivityPlace = activityPlace.get();




                for(Task task : client.getMyActivities().get(k).getTasks()){

                    indexe[0]++;

                    int captureInde = indexe[0];
                    Button buttonRemoveTask = new Button("Remove Task");
                    buttonRemoveTask.getStyleClass().add("button");
                    everything.getChildren().add(buttonRemoveTask);
                    int removeTaskPlace = everything.getChildren().size()-1;
                    removeList.add(removeTaskPlace);



                    buttonRemoveTask.setOnAction(event -> {
                        removeds[0]++;
                        removedNumbers.add(captureInde);

                        int removed = 0;
                        for (int number : removedNumbers){
                            if (captureInde > number){
                                removed++;
                            }
                        }

                        client.getMyActivities().get(newI).getTasks().removeLast();

                        for (int i = 0; i < 3; i++){

                            everything.getChildren().remove((int) removeList.get(captureInde - removed));
                        }

                        confirmPlaces.remove(Integer.valueOf(removeList.get(captureInde - removed) + 2));

                        for (int j = 0; j < confirmPlaces.size(); j++){
                            if (confirmPlaces.get(j) > removeList.get(captureInde - removed) + 1){
                                confirmPlaces.set(j, confirmPlaces.get(j) - 3);
                            }
                        }

                        for (int i = 0; i < removeList.size(); i++){

                            if (removeList.get(i) > removeList.get(captureInde - removed)){
                                removeList.set(i, removeList.get(i) - 3);
                            }
                        }
                        removeList.remove(removeList.get(captureInde - removed));

                    });

                    String instructions = "";
                    TextArea taskNameField = new TextArea("task is " + task.getName());
                    taskNameField.getStyleClass().add("textarea");

                    taskNameField.setMaxHeight(35);
                    taskNameField.setMaxWidth(150);
                    everything.getChildren().add(taskNameField);
                    TextArea newInstructions = new TextArea();
                    newInstructions.setMaxHeight(100);
                    newInstructions.setMaxWidth(500);
                    newInstructions.getStyleClass().add("textarea");

                    for (String instruction : task.getInstructions()){
                        instructions += instruction + "\n";
                    }
                    newInstructions.setText(instructions);
                    everything.getChildren().add(newInstructions);
                    confirmPlaces.add(everything.getChildren().size() - 1);


                }
                buttonConfirm.setOnAction((actionEvent -> buttonConfirm(buttonConfirm, everything,
                        currentActivityPlace,confirmPlaces, buttonAddTask, buttonBox)));

                activityPlace.set(0);
                break;
            }

        }

        return activityPlace.get();
    }



    private void buttonRemove (ActionEvent event,int index, ArrayList<Integer> numbersRemoved){

        int smaller= 0;
            for (int number : numbersRemoved){
                if (number < index){
                    smaller++;
                }
            }

        VBox removeBox = (VBox) DisplayActivities.getChildren().get(index-smaller);
        DisplayActivities.getChildren().remove(removeBox);
        Label remove = (Label) removeBox.getChildren().get(0);
        String removeText = remove.getText().replace("Activity ", "");
        numbersRemoved.add(index);

        for (Activity removeNode : client.getMyActivities()) {
            if (removeNode.getName().equals(removeText)) {
                client.getMyActivities().remove(removeNode);
                break;
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openAddActivity(){
        if (activityGrid.isVisible()){
            activityGrid.setVisible(false);
        } else {
            activityGrid.setVisible(true);
        }
        successLabel.setText("");
        errorLabel.setText("");
    }
    public void createActivity() {
        try {
            ArrayList<String> interests = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                CheckBox interest = (CheckBox) activityGrid.lookup("#Interest" + i);
                if (interest.isSelected()) {
                    interests.add(interest.getText());
                }
            }

            int taskIndex = 0;
            ArrayList<Task> taskList = new ArrayList<Task>();
            for (int i = 0; i < Tasks.getChildren().size() / 2; i++) {
                String[] instructions = ((TextArea) Tasks.getChildren().get(2 * i + 1)).getText().split("\n");
                Task newTask = new Task(((TextArea) Tasks.getChildren().get(2 * i)).getText(),
                        new ArrayList<String>(Arrays.asList(instructions)));
                if (((TextArea) Tasks.getChildren().get(2 * i)).getText().isEmpty()) {
                    newTask.setName(((TextArea) Tasks.getChildren().get(2 * i)).getPromptText());
                }

                if (!((TextArea) Tasks.getChildren().get(2 * i + 1)).getText().isEmpty()) {
                    taskList.add(newTask);
                }
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(activityStartDate.getText(), formatter);
            LocalDate endDate = LocalDate.parse(activityEndDate.getText(), formatter);
            String status = "No defined status";
            try(Reader reader = new FileReader("src/main/JsonFiles/currentDate.json")){
                Gson gson = new Gson();
                LocalDate currentDate = gson.fromJson(reader, LocalDate.class);
                if (currentDate == null){
                    currentDate = LocalDate.now();
                }
                if (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)){
                    status = "In Progress";
                }
                else if (currentDate.isBefore(startDate)){
                    status = "Upcoming";
                }
                else if(currentDate.isAfter(endDate)){
                    status = "Finished";
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Activity newActivity = new Activity(activityName.getText(), null, startDate, endDate,
                    activityPoints.getText(), interests, client.getId(), UUID.randomUUID(), taskList, activityDescription.getText(),status);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<Activity> activities = new ArrayList<>();

            try (Reader reader = new FileReader("src/main/JsonFiles/activities.json")) {
                Type activityListType = new TypeToken<List<Activity>>() {
                }.getType();
                activities = gson.fromJson(reader, activityListType);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (activities == null) {
                activities = new ArrayList<>();
            }
            activities.add(newActivity);

            try (Writer writer = new FileWriter("src/main/JsonFiles/activities.json")) {
                gson.toJson(activities, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Client> clients = new ArrayList<>();
            try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
                Type clientListType = new TypeToken<List<Client>>() {
                }.getType();
                clients = gson.fromJson(reader, clientListType);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (clients == null) {
                clients = new ArrayList<>();
            }
            for (Client client : clients) {
                for (String interest : newActivity.getInterests()) {
                    if (client.getMyInterests().contains(interest)) {
                        client.getNotifications().add("A new activity that may interest you has been created in Robotix.\nStay ahead and check it out now!" + "\n" + "Name of the activity: " + newActivity.getName());
                        break;
                    }
                }
            }

            try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                gson.toJson(clients, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            successLabel.setText("Activity successfully created for Robotix! (Go back to homepage to join activities)");
            activityGrid.setVisible(false);
        } catch (Exception e) {
            errorLabel.setText("Error: Activity could not be created. Please check your inputs.");
            activityGrid.setVisible(false);
        }
    }


    public void handleGoBack() {
        try {
            Stage stage = (Stage) activityWelcome.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
            homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Activity.css").toExternalForm());
            homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            stage.setTitle("Homepage");
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setUserHomepage(client);
            homepageController.displayRobotixActivities();
            homepageController.displayCorrectMenu();
            stage.setScene(homepageMenu);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();

                }
    }




    // TESTS FUNCTIONS



}
