package Controller;
import Model.Component;
import Model.Robot;
import Model.TypeOfUsers.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
public class RobotController {
    @FXML
    public Label RobotWelcome;
    @FXML
    public VBox DisplayRobots;
    @FXML
    public GridPane tableInfo;
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
    @FXML
    public Button supprime;

    Client client;

    public void displayMessage(String message, boolean isError) {
        // Si le message n'est pas une erreur, on supprime la classe "error-text" et on ajoute la classe "default-text"
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
        // Affiche un message de bienvenue
        displayMessage("Welcome to your robots!", false);
    }

    public void showRobot(ActionEvent actionEvent) {
        Label robotlist = new Label();
        robotlist.getStyleClass().add("label-robotlist");
        DisplayRobots.getChildren().add(robotlist);
        afficherRobot(robotlist); // affiche la liste des robots
    }

    public void afficherRobot(Label robotlist) {
        supprime.setVisible(false);
        supprime.setManaged(false);
        DisplayRobots.getChildren().clear(); // on efface le contenu de la page
        affiche.setVisible(false);
        affiche.setManaged(false);
        create.setVisible(true);
        create.setManaged(true);

        String robot = "";
        Label norobot = new Label("You have no robot!");

        if(client.getFleet().size() == 0){
            norobot.getStyleClass().add("label-no-robots");
            supprime.setVisible(false);
            supprime.setManaged(false);
            DisplayRobots.getChildren().add(norobot);
            return;
        }
        else{
            norobot.setVisible(false);
            norobot.setManaged(false);
        }

        try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){

            VBox robotbox2 = new VBox(10);
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pour avoir un affichage plus joli
            Client [] Clients = gson.fromJson(reader, Client[].class); // on récupère les clients du fichier json

            for (int i = 0; i < Clients.length; i++){
                // comparer si c'est le client qui est connecté
                if (Clients[i].getId().equals(client.getId())){
                    for(Robot unit : Clients[i].getFleet()){
                        Label labelrobot = new Label();
                        HBox robotbox = new HBox(10);

                        Button remove = new Button ("Remove"); // bouton pour supprimer le robot
                        remove.setOnAction(event -> {
                            client.getFleet().remove(unit); // on supprime le robot du client

                            try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")){
                                gson.toJson(Clients, writer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            robotbox.getChildren().remove(remove); // on supprime le bouton du robot
                            robotbox.getChildren().remove(robotlist); // on supprime le robot du robotbox
                            robotbox2.getChildren().remove(robotbox); // on supprime le robotbox du robotbox2
                            });
                        robotbox.getChildren().add(remove); // on ajoute le bouton au robotbox
                        robotbox.getStyleClass().add("remove-button"); // on ajoute la classe css au robotbox

                        // on affiche les informations du robot
                        robot += "Robot Name : " + unit.getName() + "\n";
                        robot += "Robot Type : " + unit.getType() + "\n";
                        robot += "Robot Battery : " + unit.getBattery() + "\n";
                        robot += "Robot Speed : " + unit.getSpeed() + "\n";
                        robot += "Robot CpuUsage : " + unit.getCpuUsage() + "\n";
                        robot += "Robot Memory : " + unit.getMemory() + "\n";
                        robot += "Robot SerialNumber : " + unit.getSerialNumber() + "\n";
                        robot += "Robot Components : " + unit.getComponents() + "\n";
                        robot += "Robot Location : " + unit.getLocation()[0]+ unit.getLocation()[1]
                                + unit.getLocation()[2] + "\n";
                        robot += "\n";
                        labelrobot.setText(robot); // on affiche le robot dans un label
                        robot = ""; // on réinitialise le robot
                        robotbox.getChildren().add(labelrobot); // on ajoute le label au robotbox
                        robotbox2.getChildren().add(robotbox); // on ajoute le robotbox au robotbox2
                    }
                    DisplayRobots.getChildren().add(robotbox2);
                    client = Clients[i];
                    break;
                }
            }
        }
        catch (IOException ec){
            ec.printStackTrace();
        }
    }

    public void confirmRobot(ActionEvent actionEvent,Label robotlist) {

        Robot robot = new Robot(name.getText(), type.getText(),new ArrayList<Component>(), battery.getText(),
                new float[] {0.0f, 0.0f, 0.0f}, Float.valueOf(speed.getText()),Float.valueOf(cpuusage.getText()),
                Float.valueOf(memory.getText()));
        // crée un objet gson  pour écrire dans le fichier .json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        client.getFleet().add(robot); // ajoute le robot au client
        tableInfo.setVisible(false);
        Label success = new Label("Robot created successfully!");
        success.getStyleClass().add("label-success");
        DisplayRobots.getChildren().add(success); // display le label de success

        for (Node node : tableInfo.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).clear(); // clear les textfields
            }
        }

        try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){
            Client [] Clients = gson.fromJson(reader, Client[].class);
            for (int i = 0; i < Clients.length; i++){
                if (Clients[i].getId().equals(client.getId())){
                    Clients[i] = client; // on remplace le client dans le fichier .json par le client modifié
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
        success.setManaged(true); // Show the success label
        affiche.setManaged(true); // Show the show list button
        affiche.setVisible(true);
    }
    public void creatRobot(ActionEvent actionEvent) {
        // Clear the content of the display robots pane
        DisplayRobots.getChildren().clear();
        affiche.setManaged(false); // Hide the show list button
        affiche.setVisible(false); // Hide the show list button
        Label robotlist = new Label();
        tableInfo.setVisible(true); // Show the infos table
        create.setVisible(false); // Hide the create button
        create.setManaged(false); // Hide the create button

        // Create a new button Confirm and save
        Button confirm = new Button("Confirm and save");
        confirm.getStyleClass().add("button-confirm");
        // on appelle la fonction confirmRobot quand on clique sur le bouton confirm
        confirm.setOnAction(e -> confirmRobot(e,robotlist));

        tableInfo.getChildren().add(confirm); // on ajoute le bouton confirm au tableau des Infos
        tableInfo.setRowIndex(confirm, 9);
        tableInfo.setColumnIndex(confirm, 1); // on positionne le bouton confirm dans le tableau des infos

        String robot = "";
        Label norobot = new Label("You have no robot!");
        if(client.getFleet().size() == 0){
            norobot.setVisible(false);
            norobot.setManaged(false);
        }
        else{
            DisplayRobots.getChildren().remove(norobot);
        }
    }

    public void removeRobot() {
        Label robotlist = new Label();
        supprime.setVisible(false);
        supprime.setManaged(false);
        afficherRobot(robotlist);
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
