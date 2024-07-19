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

            Activity activity = client.getMyActivities().get(i);
            Label newActivity = new Label(activity.getName());
            newActivity.getStyleClass().add("activities");
            HBox buttonBox = new HBox(10);

            Button buttonRemove = new Button("Remove");
            int index = i;
            buttonRemove.setOnAction((actionEvent -> buttonRemove(actionEvent, index)));

            Button buttonModify = new Button("Modify");
            buttonModify.setOnAction((actionEvent -> buttonModify(actionEvent, index)));

            buttonBox.getChildren().addAll(buttonRemove, buttonModify);
            DisplayActivities.getChildren().add(newActivity);
            DisplayActivities.getChildren().add(buttonBox);
        }

    }

    private void buttonModify(ActionEvent actionEvent, int index) {
        Label modify = (Label) DisplayActivities.getChildren().get(2 * index + 1);
        String modifyText = modify.getText();

        for (Activity modifyNode : client.getMyActivities()) {
            if (modifyNode.getName().equals(modifyText)) {
                TextArea newTask = new TextArea();
                String tasks = "";
                for (Task task : modifyNode.getTasks()) {
                    tasks += task.getName() + "\n";
                    for (String instructions : task.getInstructions()) {
                        tasks += instructions + "\n";
                        newTask.setText(tasks);
                        DisplayActivities.getChildren().add(newTask);
                    }
                }
                break;
            }
        }
    }

            private void buttonRemove (ActionEvent event,int index){


                Label remove = (Label) DisplayActivities.getChildren().get(2 * index);
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

            public void activityGoBack () {
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

