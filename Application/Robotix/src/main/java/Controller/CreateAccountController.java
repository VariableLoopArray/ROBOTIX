package Controller;
import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * This controller manage user actions in Account Creation Menu
 * It allows the user to create an account as a client or as a supplier
 */
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
    private boolean handleClientCreateAccount(){
        boolean problem = true;

        List<Client> clients = loadClients();
        if (clients == null) {
            clients = new ArrayList<>();
        }
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
        if (clients.stream().anyMatch(client -> client.getEmail().equals(newEmail))) {
            displayMessage("Email already in use", clientForm);
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
        if (clients.stream().anyMatch(client -> client.getUsername().equals(newUsername))) {
            displayMessage("Username already in use", clientForm);
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
            newUser.add("myStorage", new JsonArray());
            newUser.add("notifications", new JsonArray());
            JsonArray emailInbox = new JsonArray();
            emailInbox.add("Click on the following button to confirm your account if not done in the next 24 hours your account will be deleted");
            newUser.add("emailInbox", emailInbox);
            for (int i = 1; i <= 10; i++) {
                CheckBox interest = (CheckBox) clientForm.lookup("#Interest" + i);
                if (interest.isSelected()) {
                    newUser.getAsJsonArray("myInterests").add(interest.getText());
                }
            }
            newUser.addProperty("toggleEmail", false);
            try(Reader reader = new FileReader("src/main/JsonFiles/currentDate.json")) {
                JsonObject currentDate = new Gson().fromJson(reader, JsonObject.class);

                if (currentDate == null) {
                    newUser.addProperty("confirmationLink", LocalDate.now().toString());
                } else{
                    int year = currentDate.get("year").getAsInt();
                    int month = currentDate.get("month").getAsInt();
                    int day = currentDate.get("day").getAsInt();
                    LocalDate timeNow = LocalDate.of(year, month, day);
                    newUser.addProperty("confirmationLink", timeNow.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            JsonArray usersArray = new JsonArray();
            if (new File(clientFile).length() == 0) {
                try (Writer writer = new FileWriter(clientFile)) {
                    writer.write("[]");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
                Scene loginMenu = new Scene(fxmlLoader.load(), 1024, 768);
                LoginController Controller = fxmlLoader.getController();
                Controller.messageLabel1.setText("Account Created Successfully");
                loginMenu.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                stage.setTitle("Login");
                stage.setScene(loginMenu);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        clearFields();
        return problem;
    }
    @FXML
    private void handleSupplierCreateAccount(){
        boolean problem = true;
        List<Supplier> suppliers = loadSuppliers();
        if (suppliers == null) {
            suppliers = new ArrayList<>();
        }
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
        if (suppliers.stream().anyMatch(supplier -> supplier.getEmail().equals(newEmail))) {
            displayMessage("Email already in use", supplierForm);
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
        if (suppliers.stream().anyMatch(supplier -> supplier.getUsername().equals(newUsername))) {
            displayMessage("Username already in use", supplierForm);
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
            newUser.addProperty("firstName", newFirstName);
            newUser.addProperty("lastName", newLastName);
            newUser.addProperty("username", newUsername);
            newUser.addProperty("password", newPassword);
            newUser.addProperty("id", UUID.randomUUID().toString());
            newUser.addProperty("email", newEmail);
            newUser.addProperty("companyName", newCompany);
            newUser.addProperty("phoneNumber", newPhoneNumber);
            newUser.add("storage", new JsonArray());
            newUser.add("notifications", new JsonArray());
            JsonArray emailInbox = new JsonArray();
            emailInbox.add("Click on the following button to confirm your account if not done in the next 24 hours your account will be deleted");
            newUser.add("emailInbox", emailInbox);
            newUser.addProperty("productionCapacity", productionCapacity);
            newUser.addProperty("toggleEmail", false);
            try(Reader reader = new FileReader("src/main/JsonFiles/currentDate.json")) {
                JsonObject currentDate = new Gson().fromJson(reader, JsonObject.class);
                if (currentDate == null) {
                    newUser.addProperty("confirmationLink", LocalDate.now().toString());
                } else{
                    int year = currentDate.get("year").getAsInt();
                    int month = currentDate.get("month").getAsInt();
                    int day = currentDate.get("day").getAsInt();
                    LocalDate timeNow = LocalDate.of(year, month, day);
                    newUser.addProperty("confirmationLink", timeNow.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            JsonArray usersArray = new JsonArray();
            if (new File(supplierFile).length() == 0) {
                try (Writer writer = new FileWriter(supplierFile)) {
                    writer.write("[]");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
                Scene loginMenu = new Scene(fxmlLoader.load(), 1024, 768);
                LoginController Controller = fxmlLoader.getController();
                Controller.messageLabel1.setText("Account Created Successfully");
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
            Scene loginMenu = new Scene(fxmlLoader.load(), 1024, 768);
            loginMenu.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
            stage.setTitle("Login");
            stage.setScene(loginMenu);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Switches the form view between client and supplier forms based on the selected value from the  clientFormChoiceBox
     */
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

    /**
     * Switches the form view between client and supplier forms based on the selected value from the  clientFormChoiceBox
     */
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
    private List<Client> loadClients() {
        try (InputStream inputStream = new FileInputStream(String.valueOf(clientFile))) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type clientListType = new TypeToken<List<Client>>() {}.getType();
            return gson.fromJson(reader, clientListType);

        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private List<Supplier> loadSuppliers() {
        try (InputStream inputStream = new FileInputStream(String.valueOf(supplierFile))) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type supplierListType = new TypeToken<List<Supplier>>() {}.getType();
            return gson.fromJson(reader, supplierListType);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    //TESTS FUNCTIONS

    public boolean handleClientCreateAccountTest(String firstName, String lastName, String username, String password,
                                                String email, String companyName,String phoneNumber){

        clientFirstNameField.setText(firstName);
        clientLastNameField.setText(lastName);
        clientUsernameField.setText(username);
        clientPasswordField.setText(password);

        clientEmailField.setText(email);
        clientCompanyNameField.setText(companyName);
        clientPhoneNumberField.setText(phoneNumber);




        return handleClientCreateAccount();
    }

    public int clearFieldsTest(){
        clientFirstNameField.setText("hello");
        clientLastNameField.setText("hello");
        clientUsernameField.setText("hello");
        clientPasswordField.setText("hello");

        clientEmailField.setText("hello");
        clientCompanyNameField.setText("hello");
        clientPhoneNumberField.setText("hello");

        clearFields();
        return (clientFirstNameField.getText().length() + clientLastNameField.getText().length() +
                clientUsernameField.getText().length() + clientPasswordField.getText().length() +
                clientEmailField.getText().length() + clientCompanyNameField.getText().length() +
                clientPhoneNumberField.getText().length());
    }

}
