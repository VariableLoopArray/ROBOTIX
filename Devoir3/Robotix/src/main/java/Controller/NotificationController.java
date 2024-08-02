package Controller;

import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NotificationController {
    @FXML
    private Client client;
    @FXML
    private Supplier supplier;
    @FXML
    private Button goBack;

    public void setUserNotification(User user) {
        if (user instanceof Client) {
            client = (Client) user;
        } else if (user instanceof Supplier) {
            supplier = (Supplier) user;
        }
    }
    public void handleGoBack() {
        try {
            Stage stage = (Stage) goBack.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
            homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Robot.css").toExternalForm());
            homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            stage.setTitle("Homepage");
            HomepageController homepageController = fxmlLoader.getController();
            if (client != null) {
                homepageController.setUserHomepage(client);
                homepageController.displayCorrectMenu();
                homepageController.displayRobotixActivities();
            } else if (supplier != null) {
                homepageController.setUserHomepage(supplier);
                homepageController.displayCorrectMenu();
            }
            stage.setScene(homepageMenu);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
