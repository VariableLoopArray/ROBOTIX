package Controller;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class HomepageController {
    @FXML
    private Label messageLabel1;
    @FXML
    private Label messageLabel2;

    public HomepageController() {
    }
    public void displayMessage(String message, boolean isError) {
        if (!isError) {
            messageLabel1.setText(message);
        } else {
            messageLabel2.setText(message);
        }
    }
    public void goToActivity() throws IOException {
        try {
            Stage stage = (Stage) messageLabel1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/Activity.fxml"));
            Scene activityScene = new Scene(fxmlLoader.load(), 1920, 1080);
            activityScene.getStylesheets().remove(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            activityScene.getStylesheets().add(getClass().getResource("/CssFiles/Activity.css").toExternalForm());
            ActivityController activityController = fxmlLoader.getController();
            activityController.displayMessage("Welcome " + "!", false);
            stage.setTitle("Homepage");
            stage.setScene(activityScene);
            stage.show();
            stage.setFullScreen(true);
        }
        catch (IOException e) {
            e.printStackTrace();        }
    }

}
