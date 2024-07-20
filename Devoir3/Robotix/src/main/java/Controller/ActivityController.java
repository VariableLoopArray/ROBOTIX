package Controller;
import Model.Activity;
import Model.Task;
import Model.TypeOfUsers.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

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
        Button buttonAdd = new Button("Add");
        DisplayActivities.getChildren().add(buttonAdd);
        for (int i = 0; i < client.getMyActivities().size(); i++) {

            VBox everything = new VBox(10);
            Activity activity = client.getMyActivities().get(i);
            //VBox activityAndModify = new VBox(1);
            Label newActivity = new Label(activity.getName());

            TextArea newTask = new TextArea();
            newTask.setVisible(false);
            newTask.setPrefHeight(10);
            newTask.setMaxWidth(550);

            //activityAndModify.getChildren().addAll(newActivity, newTask);

            newActivity.getStyleClass().add("activities");


            HBox buttonBox = new HBox(5);

            Button buttonRemove = new Button("Remove");
            int index = i;
            buttonRemove.setOnAction((actionEvent -> buttonRemove(actionEvent, index)));

            Button buttonConfirm = new Button("Confirm");
            buttonConfirm.setVisible(false);
            buttonConfirm.setOnAction((actionEvent -> buttonConfirm(buttonConfirm, newTask, buttonModify(index, newTask, buttonConfirm))));

            Button buttonModify = new Button("Modify");
            buttonModify.setOnAction((actionEvent -> buttonModify(index, newTask, buttonConfirm)));


            buttonBox.getChildren().addAll(buttonRemove, buttonModify, buttonConfirm);

            everything.getChildren().addAll(newActivity, buttonBox, newTask);
            DisplayActivities.getChildren().add(everything);

            //DisplayActivities.getChildren().add(buttonBox);
        }

    }

    private void buttonConfirm(Button buttonConfirm, TextArea newTask, int activityPlace) {
        buttonConfirm.setVisible(false);
        newTask.setVisible(false);
        String[] newText = newTask.getText().split("\n");
        client.getMyActivities().get(activityPlace).getTasks().get(0).setName(newText[0]);
        System.out.println(Arrays.toString(newText));
        for (int i = 0; i < newText.length-2; i++){
            client.getMyActivities().get(activityPlace).getTasks().get(0).getInstructions().set(i,newText[i+1]);
        }

    }

    private int buttonModify (int index, TextArea newTask, Button buttonConfirm) {
        VBox modifyBox = (VBox) DisplayActivities.getChildren().get(index+1);
        Label modify = (Label) modifyBox.getChildren().get(0);
        String modifyText = modify.getText();
        int activityPlace = -1;

        for (Activity modifyNode : client.getMyActivities()) {
            activityPlace++;
            if (modifyNode.getName().equals(modifyText)) {

                String tasks = "";
                for (Task task : modifyNode.getTasks()) {
                    tasks += task.getName() + "\n";
                    for (String instructions : task.getInstructions()) {
                        tasks += "instructions are " + instructions + "\n";
                    }
                }

                newTask.setText(tasks);
                newTask.setVisible(true);
                buttonConfirm.setVisible(true);
                return activityPlace;

            }
        }
        return activityPlace;
    }

            private void buttonRemove (ActionEvent event,int index){


                VBox removeBox = (VBox) DisplayActivities.getChildren().get(3*index+1);
                Label remove = (Label) removeBox.getChildren().get(0);
                String removeText = remove.getText();

                for (Activity removeNode : client.getMyActivities()) {
                    if (removeNode.getName().equals(removeText)) {
                        client.getMyActivities().remove(removeNode);
                        break;
                    }
                }

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
            homepageController.setUserHomepage(client);
            stage.setScene(homepageMenu);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();

                }
            }


}
