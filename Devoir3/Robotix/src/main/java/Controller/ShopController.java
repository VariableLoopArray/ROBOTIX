package Controller;

import Model.TypeOfUsers.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class ShopController {
    @FXML
    private Label shopWelcome;
    @FXML
    private CheckBox filter1;
    @FXML
    private CheckBox filter2;
    @FXML
    private CheckBox filter3;
    @FXML
    private CheckBox filter4;
    @FXML
    private CheckBox filter5;
    @FXML
    private CheckBox filter6;
    @FXML
    private CheckBox filter7;
    @FXML
    private CheckBox filter8;
    @FXML
    private TextField filter9;
    @FXML
    private TextField filter10;
    @FXML
    private Client client;
    public void setUserShop(Client client) {
        this.client = client;
        shopWelcome.setText("Welcome to the shop " + client.getUsername());
    }
    public void handleGoBack() {
        try {
            Stage stage = (Stage) shopWelcome.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
            homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Component.css").toExternalForm());
            homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            stage.setTitle("Homepage");
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setUserHomepage(client);
            homepageController.displayCorrectMenu();
            stage.setScene(homepageMenu);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void applyFilters() {
    }
}
