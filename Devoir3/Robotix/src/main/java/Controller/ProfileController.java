package Controller;

import Model.Activity;
import Model.Component;
import Model.Robot;
import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

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
    private Label interestLabel;
    @FXML
    private VBox vboxRobots;
    @FXML
    private Label robotLabel;
    @FXML
    private VBox vboxActivities;
    @FXML
    private Label activityLabel;
    @FXML
    private Label productionCapacity;
    @FXML
    private Label capacityLabel;
    @FXML
    private VBox vboxStorage;
    @FXML
    private Label storageLabel;
    @FXML
    private GridPane profileInfo;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField previousPasswordField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField companyNameField;
    @FXML
    private Label errorMessage;
    @FXML
    private Label successMessage;

    private final String clientFile = "src/main/JsonFiles/Client.json";
    private final String supplierFile = "src/main/JsonFiles/Supplier.json";

    private Client client;
    private Supplier supplier;


    public ProfileController() {
    }
    @FXML
    public void initialize() {
        errorMessage.setText("");
        successMessage.setText("");
    }
    public void displayUserInfo() {
        for (int i = 0; i < profileInfo.getRowConstraints().size(); i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            profileInfo.getRowConstraints().set(i, rowConstraints);
        }
        if (client != null) {
            fullNameLabel.setText(client.getFirstName() + " " + client.getLastName());
            usernameLabel.setText(client.getUsername());
            emailLabel.setText(client.getEmail());
            phoneNumberLabel.setText(client.getPhoneNumber());
            companyNameLabel.setText(client.getCompanyName());
            if (client.getMyActivities() != null && !client.getMyActivities().isEmpty()) {
                for (Activity activity : client.getMyActivities()) {
                    vboxActivities.getChildren().add(new Label(" - " + activity.getName() + "\n"));
                }
            }

            if (client.getMyInterests() != null && !client.getMyInterests().isEmpty()) {
                for (String interest : client.getMyInterests()) {
                    vboxInterests.getChildren().add(new Label(" - " + interest + "\n"));
                }
            }

            if (client.getFleet() != null && !client.getFleet().isEmpty()) {
                for (Robot robot : client.getFleet()) {
                    vboxRobots.getChildren().add(new Label(" - " + robot.getName() + "\n"));
                }
            }

        } else if (supplier != null) {
            System.out.println("supplier");
            vboxActivities.setVisible(false);
            activityLabel.setVisible(false);
            vboxInterests.setVisible(false);
            interestLabel.setVisible(false);
            vboxRobots.setVisible(false);
            robotLabel.setVisible(false);
            productionCapacity.setVisible(true);
            capacityLabel.setVisible(true);
            vboxStorage.setVisible(true);
            storageLabel.setVisible(true);
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
                homepageController.displayRobotixActivities();
            } else if (supplier != null) {
                homepageController.setUserHomepage(supplier);
            }
            homepageController.displayCorrectMenu();
            stage.setTitle("Homepage");
            stage.setScene(homepage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void handleSaveChanges() {
        errorMessage.setText("");
        successMessage.setText("");
        String dateRegex = "\\d{3}-\\d{3}-\\d{4}";
        if (client != null) {
            if (!previousPasswordField.getText().equals(client.getPassword())) {
                errorMessage.setText("password did not match");
            } else {
                if (!usernameField.getText().isEmpty()) {
                    client.setUsername(usernameField.getText());
                    updateUserJson(client.getId(), "username", usernameField.getText(), client);
                }
                if (!emailField.getText().isEmpty() && emailField.getText().contains("@")) {
                    client.setEmail(emailField.getText());
                    updateUserJson(client.getId(), "email", emailField.getText(), client);
                }
                if (!phoneNumberField.getText().isEmpty() && phoneNumberField.getText().matches(dateRegex)) {
                    client.setPhoneNumber(phoneNumberField.getText());
                    updateUserJson(client.getId(), "phoneNumber", phoneNumberField.getText(), client);
                }
                if (!companyNameField.getText().isEmpty()) {
                    client.setCompanyName(companyNameField.getText());
                    updateUserJson(client.getId(), "companyName", companyNameField.getText(), client);
                }
                if (!passwordField.getText().isEmpty() && passwordField.getText().length() >= 8) {
                    client.setPassword(passwordField.getText());
                    updateUserJson(client.getId(), "password", passwordField.getText(), client);
                }
                successMessage.setText("Changes saved successfully");
            }
        } else if (supplier != null) {
            if (!previousPasswordField.getText().equals(supplier.getPassword())) {
                errorMessage.setText("password did not match");
            } else {
                if (!usernameField.getText().isEmpty()) {
                    supplier.setUsername(usernameField.getText());
                    updateUserJson(supplier.getId(), "username", usernameField.getText(), supplier);
                }
                if (!emailField.getText().isEmpty() && emailField.getText().contains("@")) {
                    supplier.setEmail(emailField.getText());
                    updateUserJson(supplier.getId(), "email", emailField.getText(), supplier);
                }
                if (!phoneNumberField.getText().isEmpty() && phoneNumberField.getText().matches(dateRegex)) {
                    supplier.setPhoneNumber(phoneNumberField.getText());
                    updateUserJson(supplier.getId(), "phoneNumber", phoneNumberField.getText(), supplier);
                }
                if (!companyNameField.getText().isEmpty()) {
                    supplier.setCompanyName(companyNameField.getText());
                    updateUserJson(supplier.getId(), "companyName", companyNameField.getText(), supplier);
                }
                if (!passwordField.getText().isEmpty() && passwordField.getText().length() >= 8) {
                    supplier.setPassword(passwordField.getText());
                    updateUserJson(supplier.getId(), "password", passwordField.getText(), supplier);
                }
                successMessage.setText("Changes saved successfully");
            }
        }
    }

    public void updateUserJson(UUID id, String attribute, String newValue, User user) {
        try {
            Gson gson = new Gson();
            List<Client> allClients = null;
            List<Supplier> allSuppliers = null;

            // Read JSON file
            if (user instanceof Client) {
                try (FileReader reader = new FileReader(clientFile)) {
                    Type userListType = new TypeToken<List<Client>>() {}.getType();
                    allClients = gson.fromJson(reader, userListType);
                }
            } else if (user instanceof Supplier) {
                try (FileReader reader = new FileReader(supplierFile)) {
                    Type userListType = new TypeToken<List<Supplier>>() {}.getType();
                    allSuppliers = gson.fromJson(reader, userListType);
                }
            }

            if (user instanceof Client) {
                for (Client client : allClients) {
                    if (client.getId().equals(id)) {
                        updateClientDetails(client, attribute, newValue);
                    }
                }
                try (FileWriter writer = new FileWriter(clientFile)) {
                    gson.toJson(allClients, writer);
                }
            } else if (user instanceof Supplier) {
                for (Supplier supplier : allSuppliers) {
                    if (supplier.getId().equals(id)) {
                        updateSupplierDetails(supplier, attribute, newValue);
                    }
                }
                try (FileWriter writer = new FileWriter(supplierFile)) {
                    gson.toJson(allSuppliers, writer);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateClientDetails(Client client, String attribute, String newValue) {
        switch (attribute) {
            case "username":
                client.setUsername(newValue);
                break;
            case "email":
                client.setEmail(newValue);
                break;
            case "phoneNumber":
                client.setPhoneNumber(newValue);
                break;
            case "companyName":
                client.setCompanyName(newValue);
                break;
            case "password":
                client.setPassword(newValue);
                break;
        }
    }

    private void updateSupplierDetails(Supplier supplier, String attribute, String newValue) {
        switch (attribute) {
            case "username":
                supplier.setUsername(newValue);
                break;
            case "email":
                supplier.setEmail(newValue);
                break;
            case "phoneNumber":
                supplier.setPhoneNumber(newValue);
                break;
            case "companyName":
                supplier.setCompanyName(newValue);
                break;
            case "password":
                supplier.setPassword(newValue);
                break;
        }
    }
}
