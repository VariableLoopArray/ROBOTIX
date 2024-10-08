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
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *  The ProfileController class is responsible for handling the profile view of the user.
 *  It displays the user's information and allows the user to update their information.
 */

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
    @FXML
    private VBox displaySearchedSupplier;
    @FXML
    private TextField searchSupplier;
    @FXML
    private VBox displaySearchedSupplierMoreInfo;

    private final String clientFile = "src/main/JsonFiles/Client.json";
    private final String supplierFile = "src/main/JsonFiles/Supplier.json";

    private Client client;
    private Supplier supplier;


    public ProfileController() {
    }

    /**
     * Initializes the profile controller by clearing all error or success messages.
     */
    @FXML
    public void initialize() {
        errorMessage.setText("");
        successMessage.setText("");
    }

/**
 * Displays the user's information in the profile view with all the attributs of user's class attributs
 */
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
            try (Reader reader = new FileReader(clientFile)) {
                Gson gson = new Gson();
                List<Client> clients = gson.fromJson(reader, new TypeToken<List<Client>>() {
                }.getType());
                for (Client client : clients) {
                    if (client.getId().equals(user.getId())) {
                        this.client = client;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (user instanceof Supplier) {
            try (Reader reader = new FileReader(supplierFile)) {
                Gson gson = new Gson();
                List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>() {
                }.getType());
                for (Supplier supplier : suppliers) {
                    if (supplier.getId().equals(user.getId())) {
                        this.supplier = supplier;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the user for the profile controller based on a user or supplier and
     * loads data and update the JSON file
     *
     */
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


    /**
     * Updates the details of a user in the corresponding JSON file by remplacing the old attribute by the new one
     */
    public void updateUserJson(UUID id, String attribute, String newValue, User user) {
        try {
            Gson gson = new Gson();
            List<Client> allClients = null;
            List<Supplier> allSuppliers = null;

            // Read JSON file
            if (user instanceof Client) {
                try (FileReader reader = new FileReader(clientFile)) {
                    Type userListType = new TypeToken<List<Client>>() {
                    }.getType();
                    allClients = gson.fromJson(reader, userListType);
                }
            } else if (user instanceof Supplier) {
                try (FileReader reader = new FileReader(supplierFile)) {
                    Type userListType = new TypeToken<List<Supplier>>() {
                    }.getType();
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

    /**
     * Searches for a supplier based on the input provided by the client in the searchSupplier TextField.
     * and displays the information of the supplier that matches what the user inputs
     * If no supplier is found, displays a message that 0 supplier was found
     */
    public void searchSupplier() {
        // Clear the previous search results
        displaySearchedSupplier.getChildren().clear();
        displaySearchedSupplierMoreInfo.getChildren().clear();

        try (Reader reader = new FileReader(supplierFile)) {
            Gson gson = new Gson();
            List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>() {
            }.getType());

            Supplier supplierSearchedFor = findSupplier(suppliers, searchSupplier.getText());

            if (supplierSearchedFor == null) {
                displaySearchedSupplier.getChildren().add(new Label("No supplier found"));
            } else {
                displaySupplierInfo(displaySearchedSupplier, supplierSearchedFor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Supplier findSupplier(List<Supplier> suppliers, String searchText) {
        for (Supplier supplier : suppliers) {
            if (supplier.getUsername().equals(searchText)) {
                return supplier;
            }
            for (Component component : supplier.getStorage()) {
                if (component.getType().contains(searchText)) {
                    return supplier;
                }
            }
        }
        return null;
    }

    private void displaySupplierInfo(VBox displaySearchedSupplier, Supplier supplierSearchedFor) {
        displaySearchedSupplier.getChildren().add(new Label("Supplier: " + supplierSearchedFor.getUsername()));
        displaySearchedSupplier.getChildren().add(new Label("Storage: "));

        FlowPane storagePane = new FlowPane();
        storagePane.setPrefWrapLength(300); // Set the preferred width for wrapping

        for (Component component : supplierSearchedFor.getStorage()) {
            VBox componentInfo = new VBox();
            componentInfo.getChildren().add(new Label(component.getName() + "\n"));

            VBox typeInfo = new VBox();
            for (String type : component.getType()) {
                typeInfo.getChildren().add(new Label(" - " + type + "\n"));
            }
            componentInfo.getChildren().add(typeInfo);

            storagePane.getChildren().add(componentInfo);
        }

        displaySearchedSupplier.getChildren().add(storagePane);

        Button moreInfo = new Button("More Info");
        moreInfo.setOnAction(e -> toggleMoreSupplierInfo(supplierSearchedFor));
        displaySearchedSupplier.getChildren().add(moreInfo);
    }

    private void toggleMoreSupplierInfo(Supplier supplierSearchedFor) {
        if (displaySearchedSupplierMoreInfo.getChildren().isEmpty()) {
            displayMoreSupplierInfo(supplierSearchedFor);
        } else {
            displaySearchedSupplierMoreInfo.getChildren().clear();
        }
    }

    private void displayMoreSupplierInfo(Supplier supplierSearchedFor) {
        try {
            displaySearchedSupplierMoreInfo.getChildren().add(new Label("Name: " + supplierSearchedFor.getFirstName() + " " + supplierSearchedFor.getLastName()));
            displaySearchedSupplierMoreInfo.getChildren().add(new Label("Email: " + supplierSearchedFor.getEmail()));
            displaySearchedSupplierMoreInfo.getChildren().add(new Label("Phone Number: " + supplierSearchedFor.getPhoneNumber()));
            displaySearchedSupplierMoreInfo.getChildren().add(new Label("Company Name: " + supplierSearchedFor.getCompanyName()));
            displaySearchedSupplierMoreInfo.getChildren().add(new Label("Production Capacity: " + supplierSearchedFor.getProductionCapacity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the Go Back action by navigating the user to the homepage.
     */
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

    // TEST FUNCTIONS

    public ArrayList<Object> displayProfileTest() {
        ArrayList<Object> userInfo = new ArrayList<>();
        try (Reader reader = new FileReader("src/main/JsonFiles/Client.json")) {
            Gson gson = new Gson();
            List<Client> clients = gson.fromJson(reader, new TypeToken<List<Client>>() {
            }.getType());
            Client testClient = clients.get(0);
            setUser(testClient);
            displayUserInfo();
            for (int i = 1; i < profileInfo.getChildren().size() - 10; i = i + 2) {
                Label info = (Label) profileInfo.getChildren().get(i);
                userInfo.add(info.getText());
            }
            VBox activities = (VBox) profileInfo.getChildren().get(11);
            ArrayList<String> activitiesList = new ArrayList<>();
            for (int i = 0; i < activities.getChildren().size(); i++) {
                Label activity = (Label) activities.getChildren().get(i);
                activitiesList.add(activity.getText());
            }
            userInfo.add(activitiesList);
            VBox interests = (VBox) profileInfo.getChildren().get(13);
            ArrayList<String> interestsList = new ArrayList<>();
            for (int i = 0; i < interests.getChildren().size(); i++) {
                Label interest = (Label) interests.getChildren().get(i);
                interestsList.add(interest.getText());
            }
            userInfo.add(interestsList);
            VBox robots = (VBox) profileInfo.getChildren().get(15);
            ArrayList<String> robotsList = new ArrayList<>();
            for (int i = 0; i < robots.getChildren().size(); i++) {
                Label robot = (Label) robots.getChildren().get(i);
                robotsList.add(robot.getText());
            }
            userInfo.add(robotsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;

    }

    public Client handleSaveChangesTest(Client clientTest, String username, String email, String companyName,
                                        String phoneNumber, String previousPassword, String password) {

        client = clientTest;

        usernameField.setText(username);
        emailField.setText(email);
        companyNameField.setText(companyName);
        phoneNumberField.setText(phoneNumber);
        passwordField.setText(password);
        previousPasswordField.setText(previousPassword);

        handleSaveChanges();
        return client;
    }
}
