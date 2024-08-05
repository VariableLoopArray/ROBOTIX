package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
public class Robotix extends Application {
    LocalDate currentDate;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Robotix.class.getResource("/FxmlPages/LoginMenu.fxml"));
        Scene loginMenu = new Scene(fxmlLoader.load(), 1024, 768);
        loginMenu.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
        stage.setTitle("Login");
        stage.setScene(loginMenu);
        stage.show();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = new FileReader("src/main/JsonFiles/currentDate.json")) {
            currentDate = gson.fromJson(reader, LocalDate.class);
            if (currentDate == null) {
                currentDate = LocalDate.now();
                saveCurrentDate(gson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCurrentDate(Gson gson) {
        try (Writer writer = new FileWriter("src/main/JsonFiles/currentDate.json")) {
            gson.toJson(currentDate, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }


}