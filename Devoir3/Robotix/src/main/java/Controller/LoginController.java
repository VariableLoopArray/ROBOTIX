package Controller;

import Model.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;


public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel1;
    @FXML
    private Label messageLabel2;

    private static final String userFile = "src/main/JsonFiles/users.json";
    private List<User> users;

    public LoginController(){
        users = loadUsers();
    }

    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isUserValid(username, password)) {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepageScene = new Scene(fxmlLoader.load());
            homepageScene.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.displayMessage("Welcome " + username + "!", false);
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

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setTitle("Create New Account");
        stage.setScene(createAccountScene);
        stage.show();
    }

    private boolean isUserValid(String username, String password){
        return users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    private List<User> loadUsers() {
        try (InputStream inputStream = new FileInputStream(userFile)) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>() {}.getType();
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
}