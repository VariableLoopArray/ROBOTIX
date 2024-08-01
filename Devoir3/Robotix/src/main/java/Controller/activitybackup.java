//package Controller;
//
//import Model.Activity;
//import Model.Task;
//import Model.TypeOfUsers.Client;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.io.*;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class activitybackup {
//    package Controller;
//import Model.Activity;
//import Model.Task;
//import Model.TypeOfUsers.Client;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.io.*;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.atomic.AtomicInteger;
//
//    public class ActivityController {
//        public VBox DisplayActivities;
//        @FXML
//        public GridPane activityGrid;
//        @FXML
//        public Label create;
//        @FXML
//        private Label activityWelcome;
//        @FXML
//        private TextField activityName;
//        @FXML
//        private TextField activityStartDate;
//        @FXML
//        private TextField activityEndDate;
//        @FXML
//        private TextField activityPoints;
//        @FXML
//        private Label noActivityList;
//
//        @FXML
//        private TextArea activityDescription;
//
//        @FXML
//        Client client;
//        @FXML
////    public void initialize() {
////        System.out.println("ActivityController initialized: " + (activityWelcome != null));
////    }
//
//        public void displayMessage(String message, boolean isError) {
//            if (!isError) {
//                activityWelcome.setText(message);
//            } else {
//                activityWelcome.setText("Error: " + message);
//            }
//        }
//        public void setUserActivity(Client client){
//            this.client = client;
//            displayMessage("Welcome to your activities!", false);
//        }
//
//        public void displayActivities(Client client){
//            DisplayActivities.setSpacing(10);
////        Button buttonAdd = new Button("Add");
////        DisplayActivities.getChildren().add(buttonAdd);
//            AtomicInteger numberOfActivity = new AtomicInteger();
//            ArrayList<Integer> numbersRemoved = new ArrayList<Integer>();
//            for (int i = 0; i < client.getMyActivities().size(); i++) {
//
//                VBox everything = new VBox(10);
//                Activity activity = client.getMyActivities().get(i);
//                //VBox activityAndModify = new VBox(1);
//                Label newActivity = new Label(activity.getName());
//                TextArea newTask = new TextArea();
//                newTask.setManaged(false);
//                newTask.setVisible(false);
//                newTask.setPrefHeight(10);
//                newTask.setMaxWidth(550);
//
//                List<Integer> confirmPlaces = new ArrayList<Integer>();
//                //activityAndModify.getChildren().addAll(newActivity, newTask);
//
//                newActivity.getStyleClass().add("activities");
//
//
//                HBox buttonBox = new HBox(5);
//
//                Button buttonRemove = new Button("Remove");
//                int index = i;
//                buttonRemove.setOnAction((actionEvent -> buttonRemove(actionEvent, index, numbersRemoved)));
//
//                Button buttonAddTask = new Button("Add Task");
//                buttonAddTask.setVisible(false);
//
//                Button buttonModify = new Button("Modify");
//                buttonModify.setOnAction((actionEvent) -> {
//                    numberOfActivity.set(buttonModify(index, newTask, everything, buttonBox, confirmPlaces, buttonAddTask));
//                });
//
//
//                buttonBox.getChildren().addAll(buttonRemove, buttonModify, buttonAddTask);
//
//                everything.getChildren().addAll(newActivity, buttonBox);
//
//                for (Node child : everything.getChildren()) {
//                    System.out.println(child.getClass().getSimpleName());
//                }
//                DisplayActivities.getChildren().add(everything);
//
//                //DisplayActivities.getChildren().add(buttonBox);
//            }
////        if (client.getMyActivities().size() == 0 && client.getMyActivities() != null){
////            noActivityList.setText("You have no activities");
////        }
//
//        }
//
//        private void buttonConfirm(Button buttonConfirm, VBox everything, int activityPlace, List<Integer> confirmPlaces,
//                                   Button buttonAddTask, HBox buttonBox) {
//
//            for (int e = 0; e < confirmPlaces.size(); e ++){
//
//                ArrayList<Integer> taskIndex = new ArrayList<Integer>();
//                ArrayList<Integer> instructionsIndex = new ArrayList<Integer>();
//                for (Node child : everything.getChildren()) {
//                    System.out.println(child.getClass().getSimpleName());
//                }
//                TextArea newTask = (TextArea) everything.getChildren().get(confirmPlaces.get(e));
//                buttonConfirm.setVisible(false);
//                newTask.setVisible(false);
//                newTask.setManaged(false);
//                String[] newTextTab = newTask.getText().split("\n");
//
//                for (int i = 0; i < newTextTab.length; i++){
////            if (newTextTab[i].contains("task name is")){
////               taskIndex.add(i);
////            }
////            else if (newText[i].contains("instructions are ")){
////                instructionsIndex.add(i);
////            }
////
//                    newTextTab[i] = newTextTab[i].replaceAll("task name is ", "");
//
//                }
//
//                List<String> newText = Arrays.asList(newTextTab);
//
//                for (int i = 0; i < newText.size(); i++){
//                    client.getMyActivities().get(activityPlace).getTasks().get(i).setName(newText.get(i));
//                }
//                //System.out.println(Arrays.toString(newText));
////        for (int j = 0; j < taskIndex.size(); j++){
////            for (int i = 0; i < client.getMyActivities().get(activityPlace).getTasks().size(); i++) {
////                if (j == 0) {
////                    client.getMyActivities().get(activityPlace).getTasks().get(j).
////                            getInstructions().set(i, newText[instructionsIndex.get(i)]);
////                }
////                else{
////                    client.getMyActivities().get(activityPlace).getTasks().get(j).
////                            getInstructions().set(i, newText[instructionsIndex.get(i + taskIndex.get(j) - 1 )]);
////                }
////            }
////            //  System.out.println(client.getMyActivities().get(activityPlace).getTasks().get(0).getInstructions().toString());
////        }
//
//            }
//
//            for (int e = confirmPlaces.size()-1; e >= 0; e --){
//                Label newTask = (Label) everything.getChildren().get(confirmPlaces.get(e) - 1);
//                everything.getChildren().remove(newTask);
//            }
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//            try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){
//
//                Client [] Clients = gson.fromJson(reader, Client[].class);
//                for (int i = 0; i < Clients.length; i++){
//                    if (Clients[i].getId().equals(client.getId())){
//                        Clients[i] = client;
//                        break;
//                    }
//                }
//                try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")){
//                    gson.toJson(Clients, writer);
//                }
//            }
//            catch (IOException ec){
//                ec.printStackTrace();
//            }
//            buttonAddTask.setVisible(false);
//            buttonBox.getChildren().remove(buttonConfirm);
//        }
//
//        private int buttonModify (int index, TextArea newTask, VBox everything, HBox buttonBox, List<Integer> confirmPlaces,
//                                  Button buttonAddTask) {
//            VBox modifyBox = (VBox) DisplayActivities.getChildren().get(index);
//            Label modify = (Label) modifyBox.getChildren().get(0);
//            String modifyText = modify.getText();
//            AtomicInteger activityPlace = new AtomicInteger(-1);
//            Button buttonConfirm = new Button("Confirm");
//
//            buttonAddTask.setVisible(true);
//
//
//            for (Activity modifyNode : client.getMyActivities()) {
//
//                activityPlace.incrementAndGet();
//                int n = activityPlace.get();
//                buttonAddTask.setOnAction((actionEvent -> {
//                    Label newTask1 = new Label("new task");
//                    TextArea newInstructions1 = new TextArea();
//                    everything.getChildren().addAll(newTask1, newInstructions1);
//                    confirmPlaces.add(everything.getChildren().size() - 1);
//                    client.getMyActivities().get(n).getTasks().add(new Task("new task", new ArrayList<String>()));
//                }));
//                if (modifyNode.getName().equals(modifyText)) {
//
//                    String tasks = "";
//
////                for (Task task : modifyNode.getTasks()) {
////                    tasks += "task name is " + task.getName() + "\n";
////                }
////
////                newTask.setText(tasks);
//                    newTask.setVisible(true);
//                    newTask.setManaged(true);
//                    buttonBox.getChildren().add(buttonConfirm);
//                    for(Task task : modifyNode.getTasks()){
//                        String instructions = "";
//                        Label taskNameField = new Label("task is " + task.getName());
//                        everything.getChildren().add(taskNameField);
//                        TextArea newInstructions = new TextArea();
//                        for (String instruction : task.getInstructions()){
//                            instructions += instruction + "\n";
//                        }
//                        newInstructions.setText(instructions);
//                        everything.getChildren().add(newInstructions);
//                        confirmPlaces.add(everything.getChildren().size() - 1);
//                        int currentActivityPlace = activityPlace.get();
//
//                        buttonConfirm.setOnAction((actionEvent -> buttonConfirm(buttonConfirm, everything,
//                                currentActivityPlace,confirmPlaces, buttonAddTask, buttonBox)));
//
//                    }
//                    //buttonConfirm.setOnAction((actionEvent -> buttonConfirm(buttonConfirm, (TextArea) everything.getChildren().get(3), activityPlace.get())));
//
//                }
//
//            }
//
//
//            return activityPlace.get();
//        }
//
//        private void buttonRemove (ActionEvent event, int index, ArrayList<Integer> numbersRemoved){
//
//            int smaller= 0;
//            for (int number : numbersRemoved){
//                if (number < index){
//                    smaller++;
//                }
//            }
//
//            VBox removeBox = (VBox) DisplayActivities.getChildren().get(index-smaller);
//            DisplayActivities.getChildren().remove(removeBox);
//            Label remove = (Label) removeBox.getChildren().get(0);
//            String removeText = remove.getText();
//            numbersRemoved.add(index);
//
//            for (Activity removeNode : client.getMyActivities()) {
//                if (removeNode.getName().equals(removeText)) {
//                    client.getMyActivities().remove(removeNode);
//                    break;
//                }
//            }
//
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//            try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){
//
//                Client [] Clients = gson.fromJson(reader, Client[].class);
//                for (int i = 0; i < Clients.length; i++){
//                    //System.out.println("this is client id " + client.getId() + " and this is the clientlist Id " + Clients[i].getId());
//                    //System.out.println(i + " this is " + (Clients[i].getId().equals()client.getId()));
//                    if (Clients[i].getId().equals(client.getId())){
//                        Clients[i] = client;
//                        break;
//                    }
//                }
//                try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")){
//                    gson.toJson(Clients, writer);
//                }
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//
//        public void createActivity() {
//            ArrayList<String> interests = new ArrayList<>();
//            for (int i = 1; i <= 10; i++) {
//                CheckBox interest = (CheckBox) activityGrid.lookup("#Interest" + i);
//                if (interest.isSelected()) {
//                    interests.add(interest.getText());
//                }
//            }
//
//            Activity newActivity = new Activity(activityName.getText(), null, activityStartDate.getText(), activityEndDate.getText(),
//                    activityPoints.getText(), interests, client.getId(), UUID.randomUUID(),new ArrayList<Task>(), activityDescription.getText(), "Not Started");
//
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            List<Activity> activities = new ArrayList<>();
//
//            try (Reader reader = new FileReader("src/main/JsonFiles/activities.json")) {
//                Type activityListType = new TypeToken<List<Activity>>() {}.getType();
//                activities = gson.fromJson(reader, activityListType);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (activities == null) {
//                activities = new ArrayList<>();
//            }
//            activities.add(newActivity);
//
//            try (Writer writer = new FileWriter("src/main/JsonFiles/activities.json")) {
//                gson.toJson(activities, writer);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            displayActivities(client);
//        }
//
//
//        public void handleGoBack() {
//            try {
//                Stage stage = (Stage) activityWelcome.getScene().getWindow();
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
//                Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
//                homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Activity.css").toExternalForm());
//                homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
//                stage.setTitle("Homepage");
//                HomepageController homepageController = fxmlLoader.getController();
//                homepageController.setUserHomepage(client);
//                homepageController.displayRobotixActivities();
//                homepageController.displayCorrectMenu();
//                stage.setScene(homepageMenu);
//                stage.show();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
//
//}
