package Controller;

import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController {
    @FXML
    private Button goBack;
    private User user;
    public ProfileController() {
    }
    @FXML
    public void initialize() {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void handleGoBack() {
        try {
            Stage stage = (Stage) goBack.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepage = new Scene(fxmlLoader.load(), 1024, 768);
            homepage.getStylesheets().remove(getClass().getResource("/CssFiles/Profile.css").toExternalForm());
            homepage.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setUserHomepage(user);
            stage.setTitle("Homepage");
            stage.setScene(homepage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
