package Controller;

import Model.TypeOfUsers.Supplier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class ComponentController {
    @FXML
    Supplier supplier;
    @FXML
    private Label ComponentWelcome;

    public void setUserComponent(Supplier supplier) {
        this.supplier = supplier;
        ComponentWelcome.setText("Welcome " + supplier.getCompanyName());
    }
    public void displayComponents(Supplier supplier){

    }
    public void handleGoBack() {
        try {
            Stage stage = (Stage) ComponentWelcome.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
            homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Activity.css").toExternalForm());
            homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            stage.setTitle("Homepage");
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setUserHomepage(supplier);
            homepageController.displayRobotixActivities();
            homepageController.displayCorrectMenu();
            stage.setScene(homepageMenu);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
