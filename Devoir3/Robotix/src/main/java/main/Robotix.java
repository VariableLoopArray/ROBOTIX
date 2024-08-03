package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Robotix extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Robotix.class.getResource("/FxmlPages/LoginMenu.fxml"));
        Scene loginMenu = new Scene(fxmlLoader.load(), 1024, 768);
        loginMenu.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
        stage.setTitle("Login");
        stage.setScene(loginMenu);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}