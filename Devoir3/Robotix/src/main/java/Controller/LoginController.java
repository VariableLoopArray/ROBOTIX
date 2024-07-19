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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class LoginController {
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
    private List<User> users;

    public LoginController(){
        users = loadUsers();
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
            homepageController.displayMessage("Welcome " + user.getUsername() + "!", false);
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
    public void displayMessage(String message, boolean isError) {
        if (!isError) {
            messageLabel1.setText(message);
        } else {
            messageLabel2.setText(message);
        }
    }
}