package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController {
    @FXML
    private Label profileMenu;
    public ProfileController() {
    }
    @FXML
    public void initialize() {

    }
    public void handleGoBack() {
        try {
            Stage stage = (Stage) profileMenu.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepage = new Scene(fxmlLoader.load(), 1024, 768);
            homepage.getStylesheets().remove(getClass().getResource("/CssFiles/Profile.css").toExternalForm());
            homepage.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            stage.setTitle("Homepage");
            stage.setScene(homepage);
            stage.show();
            stage.setFullScreen(true);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
