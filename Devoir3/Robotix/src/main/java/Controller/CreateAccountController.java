package Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.*;
import java.util.Scanner;
import java.util.UUID;


public class CreateAccountController {
    @FXML
    private TextField clientFirstNameField;
    @FXML
    private TextField clientLastNameField;
    @FXML
    private TextField clientUsernameField;
    @FXML
    private PasswordField clientPasswordField;
    @FXML
    private TextField clientEmailField;
    @FXML
    private TextField clientCompanyNameField;
    @FXML
    private TextField clientPhoneNumberField;
    @FXML
    private TextField supplierFirstNameField;
    @FXML
    private TextField supplierLastNameField;
    @FXML
    private TextField supplierUsernameField;
    @FXML
    private PasswordField supplierPasswordField;
    @FXML
    private TextField supplierEmailField;
    @FXML
    private TextField supplierCompanyNameField;
    @FXML
    private TextField supplierPhoneNumberField;
    @FXML
    private StackPane supplierForm;
    @FXML
    private StackPane clientForm;
    @FXML
    private ChoiceBox<String> clientFormChoiceBox;
    @FXML
    private ChoiceBox<String> supplierFormChoiceBox;
    @FXML
    private Label errorMessage1;
    @FXML
    private Label errorMessage2;
    @FXML
    private TextField supplierProductionCapacity;

    private static final String clientFile = "src/main/JsonFiles/client.json";
    private static final String supplierFile = "src/main/JsonFiles/supplier.json";
    @FXML
    private void handleClientCreateAccount(){
        boolean problem = true;

        String newPhoneNumber = clientPhoneNumberField.getText();
        String dateRegex = "\\d{3}-\\d{3}-\\d{4}";
        if (!newPhoneNumber.matches(dateRegex)) {
            displayMessage("Phone number not valid", clientForm);
            problem = false;
        }

        String newCompany = clientCompanyNameField.getText();

        String newEmail = clientEmailField.getText();
        if (!newEmail.contains("@")) {
            displayMessage("Email not valid", clientForm);
            problem = false;
        }

        String newPassword = clientPasswordField.getText();
        if (newPassword.length() < 8){
            displayMessage("Password at least 8 characters long", clientForm);
            problem = false;
        }

        String newUsername = clientUsernameField.getText();
        if (newUsername.isEmpty()) {
            displayMessage("Username is required", clientForm);
            problem = false;
        }

        String newLastName = clientLastNameField.getText();
        if (newLastName.isEmpty()) {
            displayMessage("Last Name is required", clientForm);
            problem = false;
        }

        String newFirstName = clientFirstNameField.getText();
        if (newFirstName.isEmpty()) {
            displayMessage("First Name is required", clientForm);
            problem = false;
        }


        if (problem) {
            JsonObject newUser = new JsonObject();
            newUser.addProperty("typeOfAccount", "Client");
            newUser.addProperty("firstName", newFirstName);
            newUser.addProperty("lastName", newLastName);
            newUser.addProperty("username", newUsername);
            newUser.addProperty("password", newPassword);
            newUser.addProperty("id", UUID.randomUUID().toString());
            newUser.addProperty("email", newEmail);
            newUser.addProperty("companyName", newCompany);
            newUser.addProperty("phoneNumber", newPhoneNumber);
            newUser.add("fleet", new JsonArray());
            newUser.add("myActivities", new JsonArray());
            newUser.add("myInterests", new JsonArray());
            for (int i = 1; i <= 10; i++) {
                CheckBox interest = (CheckBox) clientForm.lookup("#Interest" + i);
                if (interest.isSelected()) {
                    newUser.getAsJsonArray("myInterests").add(interest.getText());
                }
            }


            JsonArray usersArray = new JsonArray();
            try (InputStream inputStream = new FileInputStream(clientFile);
                 Scanner scanner = new Scanner(inputStream)) {

                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }

                usersArray = new Gson().fromJson(stringBuilder.toString(), JsonArray.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            usersArray.add(newUser);

            try (Writer writer = new FileWriter(clientFile)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(usersArray, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Stage stage = (Stage) clientUsernameField.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/LoginMenu.fxml"));
                Scene loginMenu = new Scene(fxmlLoader.load(), 720, 540);
                LoginController Controller = fxmlLoader.getController();
                Controller.displayMessage("Account Created Successfully", false);
                loginMenu.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                stage.setTitle("Login");
                stage.setScene(loginMenu);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        clearFields();
    }
    @FXML
    private void handleSupplierCreateAccount(){
        boolean problem = true;

        String productionCapacity = supplierProductionCapacity.getText();
        if (productionCapacity.isEmpty()) {
            displayMessage("Production Capacity is required for Suppliers", supplierForm);
            problem = false;
        }
        String newPhoneNumber = supplierPhoneNumberField.getText();
        String dateRegex = "\\d{3}-\\d{3}-\\d{4}";
        if (!newPhoneNumber.matches(dateRegex)) {
            displayMessage("Phone number not valid", supplierForm);
            problem = false;
        }
        String newCompany = supplierCompanyNameField.getText();

        String newEmail = supplierEmailField.getText();
        if (!newEmail.contains("@")) {
            displayMessage("Email not valid", supplierForm);
            problem = false;
        }
        String newPassword = supplierPasswordField.getText();
        if (newPassword.length() < 8){
            displayMessage("Password at least 8 characters long", supplierForm);
            problem = false;
        }
        String newUsername = supplierUsernameField.getText();
        if (newUsername.isEmpty()) {
            displayMessage("Username is required", supplierForm);
            problem = false;
        }
        String newLastName = supplierLastNameField.getText();
        if (newLastName.isEmpty()) {
            displayMessage("Last Name is required", supplierForm);
            problem = false;
        }
        String newFirstName = supplierFirstNameField.getText();
        if (newFirstName.isEmpty()) {
            displayMessage("First Name is required", supplierForm);
            problem = false;
        }

        if (problem) {
            JsonObject newUser = new JsonObject();
            newUser.addProperty("typeOfAccount", "Supplier");
            newUser.addProperty("firstName", newFirstName);
            newUser.addProperty("lastName", newLastName);
            newUser.addProperty("username", newUsername);
            newUser.addProperty("password", newPassword);
            newUser.addProperty("id", UUID.randomUUID().toString());
            newUser.addProperty("email", newEmail);
            newUser.addProperty("companyName", newCompany);
            newUser.addProperty("phoneNumber", newPhoneNumber);
            newUser.add("followers", new JsonArray());
            newUser.add("followings", new JsonArray());


            JsonArray usersArray = new JsonArray();
            try (InputStream inputStream = new FileInputStream(supplierFile);
                 Scanner scanner = new Scanner(inputStream)) {

                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }

                usersArray = new Gson().fromJson(stringBuilder.toString(), JsonArray.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            usersArray.add(newUser);

            try (Writer writer = new FileWriter(supplierFile)) {
                new Gson().toJson(usersArray, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Stage stage = (Stage) clientUsernameField.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/LoginMenu.fxml"));
                Scene loginMenu = new Scene(fxmlLoader.load(), 720, 540);
                LoginController Controller = fxmlLoader.getController();
                Controller.displayMessage("Account Created Successfully", false);
                loginMenu.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                stage.setTitle("Login");
                stage.setScene(loginMenu);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        clearFields();

    }

    @FXML
    private void handleGoBack() {
        try {
            Stage stage = (Stage) clientUsernameField.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/LoginMenu.fxml"));
            Scene loginMenu = new Scene(fxmlLoader.load(), 720, 540);
            loginMenu.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
            stage.setTitle("Login");
            stage.setScene(loginMenu);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void clientSwitchForm() {
        String clientSelectedForm = clientFormChoiceBox.getValue();
        if (clientSelectedForm.equals("Supplier")) {
            clientForm.setVisible(false);
            supplierForm.setVisible(true);
            supplierFormChoiceBox.setValue("Supplier");
            clientFormChoiceBox.setValue("Client");
            errorMessage2.setText("");
            clearFields();
        }
    }
    public void supplierSwitchForm() {
        String SupplierSelectedForm = supplierFormChoiceBox.getValue();

        if (SupplierSelectedForm.equals("Client")) {
            supplierForm.setVisible(false);
            clientForm.setVisible(true);
            clientFormChoiceBox.setValue("Client");
            supplierFormChoiceBox.setValue("Supplier");
            errorMessage1.setText("");
            clearFields();
        }
    }

    @FXML
    private void displayMessage(String message, StackPane form){
        if (form.equals(clientForm)){
            errorMessage1.setText(message);
        } else {
            errorMessage2.setText(message);
        }
    }
    private void clearFields() {
        clientFirstNameField.clear();
        clientLastNameField.clear();
        clientUsernameField.clear();
        clientPasswordField.clear();
        clientEmailField.clear();
        clientCompanyNameField.clear();
        clientPhoneNumberField.clear();
        supplierFirstNameField.clear();
        supplierLastNameField.clear();
        supplierUsernameField.clear();
        supplierPasswordField.clear();
        supplierEmailField.clear();
        supplierCompanyNameField.clear();
        supplierPhoneNumberField.clear();
        for (int i = 1; i <= 10; i++) {
            CheckBox interest = (CheckBox) clientForm.lookup("#Interest" + i);
            interest.setSelected(false);
        }
        supplierProductionCapacity.clear();
    }
}
