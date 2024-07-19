package Controller;

import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import Model.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel1;

    @FXML
    private Label messageLabel2;

    Path relativePath = Paths.get("src/main/JsonFiles/users.json");
    Path userFile = relativePath.toAbsolutePath().normalize();

    Path ClientRelativePath = Paths.get("src/main/JsonFiles/clients.json");
    Path clientFile = relativePath.toAbsolutePath().normalize();

    Path SupplierRelativePath = Paths.get("src/main/JsonFiles/users.json");
    Path supplierFile = relativePath.toAbsolutePath().normalize();

    private List<User> users;
    private List<Client> clients;
    private List<Supplier> suppliers;

    public LoginController(){
        users = loadUsers();
        clients = loadClients();
        suppliers = loadSuppliers();
    }

    @FXML
    private void handleLogin() throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();
        User user = isUserValid(email, password);

        if (user != null) {


                Stage stage = (Stage) emailField.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
                Scene homepageScene = new Scene(fxmlLoader.load(), 1920, 1080);
                homepageScene.getStylesheets().remove(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                homepageScene.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
                HomepageController homepageController = fxmlLoader.getController();
                homepageController.setUserHomepage(user);
                stage.setTitle("Homepage");
                stage.setScene(homepageScene);
                stage.show();
                stage.setFullScreen(true);



        } else {
            displayMessage("Invalid username or password", true);
        }

    }

    @FXML
    private void handleCreateAccount() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/AccountCreationMenu.fxml"));
        Scene createAccountScene = new Scene(fxmlLoader.load(), 720, 540);
        createAccountScene.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());

        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.setTitle("Create New Account");
        stage.setScene(createAccountScene);
        stage.show();
    }

    private User isUserValid(String email, String password){
         if (users.stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password)))
             return users.stream().filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst().get();

         return null;
    }

    private Client isClientValid(String email, String password){
        if (clients.stream().anyMatch(client -> client.getEmail().equals(email) && client.getPassword().equals(password)))
            return clients.stream().filter(clients -> clients.getEmail().equals(email) && clients.getPassword().equals(password)).findFirst().get();

        return null;
    }

    private Supplier isSupplierValid(String email, String password){
        if (suppliers.stream().anyMatch(Supplier -> Supplier.getEmail().equals(email) && Supplier.getPassword().equals(password)))
            return suppliers.stream().filter(Suppliers -> Suppliers.getEmail().equals(email) && Suppliers.getPassword().equals(password)).findFirst().get();

        return null;
    }

    private List<User> loadUsers() {
        try (InputStream inputStream = new FileInputStream(String.valueOf(userFile))) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private List<Client> loadClients() {
        try (InputStream inputStream = new FileInputStream(String.valueOf(clientFile))) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<Client>>() {}.getType();
            return gson.fromJson(reader, userListType);

        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private List<Supplier> loadSuppliers() {
        try (InputStream inputStream = new FileInputStream(String.valueOf(supplierFile))) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<Supplier>>() {}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }


    public void displayMessage(String message, boolean isError) {
        if (!isError) {
            messageLabel1.setText(message);
        } else {
            messageLabel2.setText(message);
        }
    }

    public void clientLogin(ActionEvent actionEvent) {
        String email = emailField.getText();
        String password = passwordField.getText();
        Client client = isClientValid(email, password);

        if (client != null) {

            try {
                Stage stage = (Stage) emailField.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
                Scene homepageScene = new Scene(fxmlLoader.load(), 1920, 1080);
                homepageScene.getStylesheets().remove(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                homepageScene.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
                HomepageController homepageController = fxmlLoader.getController();
                homepageController.setClientHomepage(client);
                stage.setTitle("Homepage");
                stage.setScene(homepageScene);
                stage.show();
                stage.setFullScreen(true);
            }
            catch (IOException e){
                e.printStackTrace();
            }


        } else {
            displayMessage("Invalid username or password", true);
        }

    }

    public void supplierLogin(ActionEvent actionEvent) {
        String email = emailField.getText();
        String password = passwordField.getText();
        Supplier supplier = isSupplierValid(email, password);

        if (supplier != null) {

            try {
                Stage stage = (Stage) emailField.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
                Scene homepageScene = new Scene(fxmlLoader.load(), 1920, 1080);
                homepageScene.getStylesheets().remove(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                homepageScene.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
                HomepageController homepageController = fxmlLoader.getController();
                homepageController.setSupplierHomepage(supplier);
                stage.setTitle("Homepage");
                stage.setScene(homepageScene);
                stage.show();
                stage.setFullScreen(true);
            }
            catch (IOException e){
                e.printStackTrace();
            }


        } else {
            displayMessage("Invalid username or password", true);
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
}