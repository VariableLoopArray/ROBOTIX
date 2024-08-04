package Controller;

import Model.Activity;
import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class LoginController {

    @FXML
    public Label messageLabel3;

    @FXML
    public StackPane supplierLogin;

    @FXML
    public Label messageLabel4;

    @FXML
    public TextField emailFieldSupplier;

    @FXML
    public PasswordField passwordFieldSupplier;

    @FXML
    public ChoiceBox<String> supplierLoginChoice;
    @FXML
    public ChoiceBox<String> clientLoginChoice;
    @FXML
    public StackPane clientLogin;
    @FXML
    public Label errorMessage;
    @FXML
    public Label errorMessageSupplier;

    @FXML
    private TextField emailFieldClient;

    @FXML
    private PasswordField passwordFieldClient;

    @FXML
    public Label messageLabel1;

    @FXML
    private Label messageLabel2;
    private Stage infoStage;
    private Stage manageDate;
    private boolean dateBool = false;

    Path clientRelativePath = Paths.get("src/main/JsonFiles/client.json");
    Path clientFile = clientRelativePath.toAbsolutePath().normalize();

    Path supplierRelativePath = Paths.get("src/main/JsonFiles/supplier.json");
    Path supplierFile = supplierRelativePath.toAbsolutePath().normalize();
    private List<Client> clients;
    private List<Supplier> suppliers;

    public LoginController(){
        clients = loadClients();
        suppliers = loadSuppliers();
    }


    @FXML
    private void handleCreateAccount() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/AccountCreationMenu.fxml"));
        Scene createAccountScene = new Scene(fxmlLoader.load(),1024, 768);
        createAccountScene.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());

        Stage stage = (Stage) emailFieldClient.getScene().getWindow();
        stage.setTitle("Create New Account");
        stage.setScene(createAccountScene);
        stage.show();
    }


     private Client isClientValid(String email, String password){
        if (clients != null) {
            if (clients.stream().anyMatch(client -> client.getEmail().equals(email) && client.getPassword().equals(password)))
                return clients.stream().filter(clients -> clients.getEmail().equals(email) && clients.getPassword().equals(password)).findFirst().get();
        }
        return null;
    }



    private Supplier isSupplierValid(String email, String password){
        if (suppliers != null) {
            if (suppliers.stream().anyMatch(Supplier -> Supplier.getEmail().equals(email) && Supplier.getPassword().equals(password)))
                return suppliers.stream().filter(Suppliers -> Suppliers.getEmail().equals(email) && Suppliers.getPassword().equals(password)).findFirst().get();
        }
        return null;
    }

    private List<Client> loadClients() {

        try (InputStream inputStream = new FileInputStream(String.valueOf(clientFile))) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type clientListType = new TypeToken<List<Client>>() {}.getType();
            return gson.fromJson(reader, clientListType);

        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private List<Supplier> loadSuppliers() {
        try (InputStream inputStream = new FileInputStream(String.valueOf(supplierFile))) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type supplierListType = new TypeToken<List<Supplier>>() {}.getType();
            return gson.fromJson(reader, supplierListType);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }


    public void clientLogin() {
        String email = emailFieldClient.getText();
        String password = passwordFieldClient.getText();
        Client client = isClientValid(email, password);
        messageLabel1.setText("");

        if (client != null) {

            try {
                Stage stage = (Stage) emailFieldClient.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
                Scene homepageScene = new Scene(fxmlLoader.load(), 1024, 768);
                homepageScene.getStylesheets().remove(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                homepageScene.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
                HomepageController homepageController = fxmlLoader.getController();
                homepageController.setUserHomepage(client);
                homepageController.displayRobotixActivities();
                homepageController.displayCorrectMenu();
                stage.setTitle("Homepage");
                stage.setScene(homepageScene);
                stage.show();

            }
            catch (IOException e){
                e.printStackTrace();
            }


        } else {
            messageLabel2.setText("Invalid username or password");
        }

    }

    public void supplierLogin() {
        String email = emailFieldSupplier.getText();
        String password = passwordFieldSupplier.getText();
        Supplier supplier = isSupplierValid(email, password);
        messageLabel3.setText("");

        if (supplier != null) {
            try {
                Stage stage = (Stage) emailFieldSupplier.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
                Scene homepageScene = new Scene(fxmlLoader.load(), 1024, 768);
                homepageScene.getStylesheets().remove(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                homepageScene.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
                HomepageController homepageController = fxmlLoader.getController();
                homepageController.setUserHomepage(supplier);
                homepageController.displayCorrectMenu();
                stage.setTitle("Homepage");
                stage.setScene(homepageScene);
                stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }


        } else {
            messageLabel4.setText("Invalid username or password");
        }

    }

    public void clientSwitchForm() {
        String clientSelectedForm = clientLoginChoice.getValue();
        if (clientSelectedForm.equals("Supplier")) {
            clientLogin.setVisible(false);
            supplierLogin.setVisible(true);
            supplierLoginChoice.setValue("Supplier");
            clientLoginChoice.setValue("Client");
            errorMessage.setText("");
        }
    }

    public void supplierSwitchForm() {
        String supplierSelectedForm = supplierLoginChoice.getValue();
        if (supplierSelectedForm.equals("Client")) {
            clientLogin.setVisible(true);
            supplierLogin.setVisible(false);
            clientLoginChoice.setValue("Client");
            supplierLoginChoice.setValue("Supplier");
            errorMessageSupplier.setText("");
        }
    }
    public void showInfoPopup() {
        if (infoStage == null || !infoStage.isShowing()) {
            infoStage = new Stage();
            infoStage.setTitle("Information");

            Label infoLabel = new Label("This is an informational message.");
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> infoStage.close());

            VBox vbox = new VBox(infoLabel, closeButton);
            vbox.setSpacing(10);
            Scene infoScene = new Scene(vbox, 300, 150);

            infoStage.setScene(infoScene);
            infoStage.show();
        }
    }
    public void changeDate(){
        if (!dateBool || !manageDate.isShowing()) {
            manageDate = new Stage();
            manageDate.setTitle("Date Management");
            LocalDate currentDate = LocalDate.now();
            try(Reader reader = new FileReader("src/main/JsonFiles/currentDate.json")) {
                Gson gson = new Gson();
                currentDate = gson.fromJson(reader, LocalDate.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            VBox header = new VBox();
            Label title = new Label("Change Date");
            Label currentDateLabel = new Label("Current Date: " + currentDate.format(formatter));
            Label infoLabel = new Label("By changing the date, you can simulate the system on a different date.");
            infoLabel.setWrapText(true);
            header.getChildren().addAll(title,currentDateLabel, infoLabel);
            header.setSpacing(10);
            header.setAlignment(javafx.geometry.Pos.CENTER);

            TextField dateInput = new TextField();
            dateInput.setPromptText("Enter new date (yyyy-MM-dd)");

            Button confirmButton = new Button("Confirm");
            confirmButton.setOnAction(e -> {
                try {

                    LocalDate newDate = LocalDate.parse(dateInput.getText(), formatter);
                    ArrayList<Activity> activities = new ArrayList<>();
                    try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
                        Gson gson = new Gson();
                        clients = gson.fromJson(reader, new TypeToken<List<Client>>() {}.getType());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try(Reader reader = new FileReader("src/main/JsonFiles/activities.json")) {
                        Gson gson = new Gson();
                        activities = gson.fromJson(reader, new TypeToken<List<Activity>>() {}.getType());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    for(Client client : clients){
                        for (int i = 0; i < client.getMyActivities().size(); i++) {
                            if (client.getMyActivities().get(i).getStartDate().isBefore(newDate) && client.getMyActivities().get(i).getEndDate().isAfter(newDate)) {
                                client.getMyActivities().get(i).setStatus("In Progress");
                            }
                            else if (client.getMyActivities().get(i).getEndDate().isBefore(newDate)) {
                                client.getMyActivities().get(i).setStatus("Completed");
                            }
                            else if (client.getMyActivities().get(i).getStartDate().isAfter(newDate)) {
                                client.getMyActivities().get(i).setStatus("Upcoming");
                            }
                        }
                    }
                    for (Activity activity : activities) {
                        if (activity.getStartDate().isBefore(newDate) && activity.getEndDate().isAfter(newDate)) {
                            activity.setStatus("In Progress");
                        }
                        else if (activity.getEndDate().isBefore(newDate)) {
                            activity.setStatus("Finished");
                        }
                        else if (activity.getStartDate().isAfter(newDate)) {
                            activity.setStatus("Upcoming");
                        }
                    }
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                        gson.toJson(clients, writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try (Writer writer = new FileWriter("src/main/JsonFiles/activities.json")) {
                        gson.toJson(activities, writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try (Writer writer = new FileWriter("src/main/JsonFiles/currentDate.json")) {
                        gson.toJson(newDate, writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                    manageDate.close();
                    dateBool = false;
                } catch (DateTimeParseException ex) {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid date format. Please enter a date in yyyy-MM-dd format.");
                    alert.show();
                }
            });



            VBox vbox = new VBox(header, dateInput, confirmButton);
            vbox.setPadding(new javafx.geometry.Insets(10));
            vbox.setSpacing(10);
            Scene infoScene = new Scene(vbox, 300, 200);

            manageDate.setScene(infoScene);
            manageDate.show();
            dateBool = true;
        }
    }
}