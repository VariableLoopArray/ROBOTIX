package Controller;

import Model.Activity;
import Model.Component;
import Model.Robot;
import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ProfileController {
    @FXML
    private Button goBack;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label companyNameLabel;
    @FXML
    private VBox vboxInterests;
    @FXML
    private VBox vboxRobots;
    @FXML
    private VBox vboxActivities;
    @FXML
    private Label productionCapacity;
    @FXML
    private VBox vboxStorage;
    @FXML
    private GridPane clientInfo;
    private Client client;
    private Supplier supplier;


    public ProfileController() {
    }
    @FXML
    public void initialize() {
    }
    public void displayUserInfo() {
        for (int i = 0; i < clientInfo.getRowConstraints().size(); i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            clientInfo.getRowConstraints().set(i, rowConstraints);
        }
        if (client != null) {
            fullNameLabel.setText(client.getFirstName() + " " + client.getLastName());
            usernameLabel.setText(client.getUsername());
            emailLabel.setText(client.getEmail());
            phoneNumberLabel.setText(client.getPhoneNumber());
            companyNameLabel.setText(client.getCompanyName());
            if (client.getMyActivities() != null && !client.getMyActivities().isEmpty()) {
                for (Activity activity : client.getMyActivities()) {
                    vboxActivities.getChildren().add(new Label(STR." - \{activity.getName()}\n"));
                }
            }

            if (client.getMyInterests() != null && !client.getMyInterests().isEmpty()) {
                for (String interest : client.getMyInterests()) {
                    vboxInterests.getChildren().add(new Label(STR." - \{interest}\n"));
                }
            }

            if (client.getFleet() != null && !client.getFleet().isEmpty()) {
                for (Robot robot : client.getFleet()) {
                    vboxRobots.getChildren().add(new Label(STR." - \{robot.getName()}\n"));
                }
            }

        } else if (supplier != null) {
            vboxActivities.setVisible(false);
            vboxInterests.setVisible(false);
            vboxRobots.setVisible(false);
            productionCapacity.setVisible(true);
            vboxInterests.setVisible(true);
            fullNameLabel.setText(supplier.getFirstName() + " " + supplier.getLastName());
            usernameLabel.setText(supplier.getUsername());
            emailLabel.setText(supplier.getEmail());
            phoneNumberLabel.setText(supplier.getPhoneNumber());
            companyNameLabel.setText(supplier.getCompanyName());
            productionCapacity.setText(String.valueOf(supplier.getProductionCapacity()));

            if (supplier.getStorage() != null && !supplier.getStorage().isEmpty()) {
                for (Component component : supplier.getStorage()) {
                    vboxStorage.getChildren().add(new Label(component.getName() + "\n"));
                }
            }
        }
    }
    public void setUser(User user) {
        if (user instanceof Client) {
            this.client = (Client) user;
        } else if (user instanceof Supplier) {
            this.supplier = (Supplier) user;
        }
    }

    public void handleGoBack() {
        try {
            Stage stage = (Stage) goBack.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepage = new Scene(fxmlLoader.load(), 1024, 768);
            homepage.getStylesheets().remove(getClass().getResource("/CssFiles/Profile.css").toExternalForm());
            homepage.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            HomepageController homepageController = fxmlLoader.getController();
            if (client != null) {
                homepageController.setUserHomepage(client);
            } else if (supplier != null) {
                homepageController.setUserHomepage(supplier);
            }
            stage.setTitle("Homepage");
            stage.setScene(homepage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
