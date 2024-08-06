package Controller;

import Model.Activity;
import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * The LoginController class handles the login functionality for clients and suppliers
 * by validating the email and password entered by the user and loading the respective
 */
public class LoginController {

    @FXML
    public Label messageLabel3;

    @FXML
    public StackPane supplierLogin;

    @FXML
    public Label messageLabel4;

    @FXML
    public TextField emailFieldSupplier;

    @FXML
    public PasswordField passwordFieldSupplier;

    @FXML
    public ChoiceBox<String> supplierLoginChoice;
    @FXML
    public ChoiceBox<String> clientLoginChoice;
    @FXML
    public StackPane clientLogin;
    @FXML
    public Label errorMessage;
    @FXML
    public Label errorMessageSupplier;

    @FXML
    private TextField emailFieldClient;

    @FXML
    private PasswordField passwordFieldClient;

    @FXML
    public Label messageLabel1;

    @FXML
    private Label messageLabel2;
    private Stage infoStage;
    private Stage manageDate;
    private boolean dateBool = false;

    Path clientRelativePath = Paths.get("src/main/JsonFiles/client.json");
    Path clientFile = clientRelativePath.toAbsolutePath().normalize();

    Path supplierRelativePath = Paths.get("src/main/JsonFiles/supplier.json");
    Path supplierFile = supplierRelativePath.toAbsolutePath().normalize();
    private List<Client> clients;
    private List<Supplier> suppliers;

    //Costructor of LoginController class
    public LoginController(){
        clients = loadClients();
        suppliers = loadSuppliers();
    }

    /**
     * Loads and displays the account creation menu.
     * This method is used when the user wants to create a new account.
     * It loads the AccountCreationMenu FXML file
     * @throws IOException if the FXML file cannot be loaded.
     */
    @FXML
    private void handleCreateAccount() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/AccountCreationMenu.fxml"));
        Scene createAccountScene = new Scene(fxmlLoader.load(),1024, 768);
        createAccountScene.getStylesheets().add(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());

        Stage stage = (Stage) emailFieldClient.getScene().getWindow();
        stage.setTitle("Create New Account");
        stage.setScene(createAccountScene);
        stage.show();
    }


    /**
     * Validates the provided email and password for a client.
     * This method checks if there exists a client with given email and password
     * in the list of clients.
     * @return  Client object if the email and password are valid, otherwise null
     */
     private Client isClientValid(String email, String password){
        if (clients != null) {
            if (clients.stream().anyMatch(client -> client.getEmail().equals(email) && client.getPassword().equals(password)))
                return clients.stream().filter(clients -> clients.getEmail().equals(email) && clients.getPassword().equals(password)).findFirst().get();
        }
        return null;
    }

    /**
     * Validates the provided email and password for a supplier.
     * This method checks if there exists a supplier with given email and password
     * in the list of supplier.
     * @return  Supplier object if the email and password are valid, otherwise null
     */
    private Supplier isSupplierValid(String email, String password){
        if (suppliers != null) {
            if (suppliers.stream().anyMatch(Supplier -> Supplier.getEmail().equals(email) && Supplier.getPassword().equals(password)))
                return suppliers.stream().filter(Suppliers -> Suppliers.getEmail().equals(email) && Suppliers.getPassword().equals(password)).findFirst().get();
        }
        return null;
    }


    /**
     * Loads the list of clients from a JSON file by reading the JSON file
     * and returns a list of Client objects. If an IOException
     * occurs during reading, the method catches the exception,
     * @return a list of Client objects or an empty list if an error occurs
     */
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

    /**
     * Loads the list of supplier from a JSON file by reading the JSON file
     * and returns a list of Supplier objects. If an IOException
     * occurs during reading, the method catches the exception,
     * @return a list of Supplier objects or an empty list if an error occurs
     */
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

    /**
     * Handles the client login process by retrieving the email and password from the
     * client login form, make sure it is valid and loads the client's homepage.
     * If the credentials are invalid, an error message is displayed.
     */
    public void clientLogin() {
        String email = emailFieldClient.getText();
        String password = passwordFieldClient.getText();
        Client client = isClientValid(email, password);
        messageLabel1.setText("");

        if (client != null) {

            try {
                Stage stage = (Stage) emailFieldClient.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
                Scene homepageScene = new Scene(fxmlLoader.load(), 1024, 768);
                homepageScene.getStylesheets().remove(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                homepageScene.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
                HomepageController homepageController = fxmlLoader.getController();
                homepageController.setUserHomepage(client);
                homepageController.displayRobotixActivities();
                homepageController.displayCorrectMenu();
                stage.setTitle("Homepage");
                stage.setScene(homepageScene);
                stage.show();

            }
            catch (IOException e){
                e.printStackTrace();
            }


        } else {
            messageLabel2.setText("Invalid username or password");
        }

    }

    /**
     * Handles the supplier login process by retrieving the email and password from the
     * supplier login form, make sure it is valid and loads the supplier's homepage.
     * If the credentials are invalid, an error message is displayed.
     */
    public void supplierLogin() {
        String email = emailFieldSupplier.getText();
        String password = passwordFieldSupplier.getText();
        Supplier supplier = isSupplierValid(email, password);
        messageLabel3.setText("");

        if (supplier != null) {
            try {
                Stage stage = (Stage) emailFieldSupplier.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
                Scene homepageScene = new Scene(fxmlLoader.load(), 1024, 768);
                homepageScene.getStylesheets().remove(getClass().getResource("/CssFiles/LoginAndCreate.css").toExternalForm());
                homepageScene.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
                HomepageController homepageController = fxmlLoader.getController();
                homepageController.setUserHomepage(supplier);
                homepageController.displayCorrectMenu();
                stage.setTitle("Homepage");
                stage.setScene(homepageScene);
                stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }


        } else {
            messageLabel4.setText("Invalid username or password");
        }

    }

    /**
     * Handles the switching of the login form from client to supplier using the choice box.
     * It hides the client login form and displays the supplier login form.
     */
    public void clientSwitchForm() {
        String clientSelectedForm = clientLoginChoice.getValue();
        if (clientSelectedForm.equals("Supplier")) {
            clientLogin.setVisible(false);
            supplierLogin.setVisible(true);
            supplierLoginChoice.setValue("Supplier");
            clientLoginChoice.setValue("Client");
            errorMessage.setText("");
        }
    }
    /**
     * Handles the switching of the login form from supplier to client using the choice box.
     * It hides the supplier login form and displays the client login form.
     */
    public void supplierSwitchForm() {
        String supplierSelectedForm = supplierLoginChoice.getValue();
        if (supplierSelectedForm.equals("Client")) {
            clientLogin.setVisible(true);
            supplierLogin.setVisible(false);
            clientLoginChoice.setValue("Client");
            supplierLoginChoice.setValue("Supplier");
            errorMessageSupplier.setText("");
        }
    }

    /**
     * Displays an information popup window and creates a new window with a title "Information" containing an informational message and lists for
     * displaying client and supplier information.
     */
    public void showInfoPopup() {
        if (infoStage == null || !infoStage.isShowing()) {
            infoStage = new Stage();
            infoStage.setTitle("Information");


            Label infoLabel = new Label("This is an informational message.");

            VBox vbox = new VBox(infoLabel);
            vbox.setSpacing(10);

            Gson gson = new Gson();

            HBox userList = new HBox(100);
            HBox clientList = new HBox(10);
            HBox supplierList = new HBox(10);

//            try (Reader reader = new FileReader("src/main/JsonFiles/client.json")){
//
//                Client [] Clients = gson.fromJson(reader, Client[].class);
//                for (int i = 0; i < Clients.length; i++){
//                    VBox user = new VBox(10);
//                    Label firstNameInfo = new Label(Clients[i].getFirstName());
//                    Label lastNameInfo = new Label(Clients[i].getLastName());
//                    Label usernameInfo = new Label(Clients[i].getUsername());
//                    Label passwordInfo = new Label(Clients[i].getPassword());
//                    Label emailInfo = new Label(Clients[i].getEmail());
//                    Label companyNameInfo = new Label(Clients[i].getCompanyName());
//                    Label phoneNumberInfo = new Label(Clients[i].getPhoneNumber());
//                    user.getChildren().addAll(emailInfo, passwordInfo);
//                    clientList.getChildren().add(user);
//                }
//
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }

            ListView<String> clients = new ListView<>();
            ListView<String> suppliers = new ListView<>();
            List<String> ClientList = new ArrayList<String>();
            List<String> SupplierList = new ArrayList<String>();

            ClientList.add("Type: Client, Email: johndoe@gmail.com, Password: password1");
            ClientList.add("Type: Client, Email: ZaneUnderwood@gmail.com, Password: password2");
            ClientList.add("Type: Client, Email: AnnabelleValdez@gmail.com, Password: password3");
            ClientList.add("Type: Client, Email: GianlucaGray@gmail.com, Password: password4");
            ClientList.add("Type: Client, Email: JaydonSanchez@gmail.com, Password: password5");
            ClientList.add("Type: Client, Email: RayCharles@gmail.com, Password: password6");
            ClientList.add("Type: Client, Email: FrankSinatra@gmail.com, Password: password7");
            ClientList.add("Type: Client, Email: GeorgeWashington@gmail.com, Password: password8");
            ClientList.add("Type: Client, Email: TeddyRoosevelt@gmail.com, Password: password9");
            ClientList.add("Type: Client, Email: ManfredAlbert@gmail.com, Password: password10");

            SupplierList.add("Type: Supplier, Email: MichaelOliver@gmail.com, Password: password1");
            SupplierList.add("Type: Supplier, Email: FrankieCharles@gmail.com, Password: password2");
            SupplierList.add("Type: Supplier, Email: JoshBurton@gmail.com, Password: password3");
            SupplierList.add("Type: Supplier, Email: SophieMoreno@gmail.com, Password: password4");
            SupplierList.add("Type: Supplier, Email: EdwardBlair@gmail.com, Password: password5");

//            VBox user = new VBox(10);
//            Label type = new Label("Type : Client");
//            Label email = new Label("johndoe@gmail.com");
//            Label password = new Label("password1");
//            user.getChildren().addAll(type,email, password);
//            clientList.getChildren().add(user);
//
//            VBox user2 = new VBox(10);
//            Label email2 = new Label("ZaneUnderwood@gmail.com");
//            Label password2 = new Label("password2");
//            user.getChildren().addAll(email2, password2);
//            clientList.getChildren().add(user2);
//
//            VBox user3 = new VBox(10);
//            Label email3 = new Label("AnnabelleValdez@gmail.com");
//            Label password3 = new Label("password3");
//            user.getChildren().addAll(email3, password3);
//            clientList.getChildren().add(user3);
//
//            VBox user4 = new VBox(10);
//            Label email4 = new Label("GianlucaGray@gmail.com");
//            Label password4 = new Label("password4");
//            user.getChildren().addAll(email4, password4);
//            clientList.getChildren().add(user4);
//
//            VBox user5 = new VBox(10);
//            Label email5 = new Label("JaydonSanchez@gmail.com");
//            Label password5 = new Label("password5");
//            user.getChildren().addAll(email5, password5);
//            clientList.getChildren().add(user5);
//
//            VBox user6 = new VBox(10);
//            Label email6 = new Label("RayCharles@gmail.com");
//            Label password6 = new Label("password6");
//            user.getChildren().addAll(email6, password6);
//            clientList.getChildren().add(user6);
//
//            VBox user7 = new VBox(10);
//            Label email7 = new Label("FrankSinatra@gmail.com");
//            Label password7 = new Label("password7");
//            user.getChildren().addAll(email7, password7);
//            clientList.getChildren().add(user7);
//
//            VBox user8 = new VBox(10);
//            Label email8 = new Label("GeorgeWashington@gmail.com");
//            Label password8 = new Label("password8");
//            user.getChildren().addAll(email8, password8);
//            clientList.getChildren().add(user8);
//
//            VBox user9 = new VBox(10);
//            Label email9 = new Label("TeddyRoosevelt@gmail.com");
//            Label password9 = new Label("password9");
//            user.getChildren().addAll(email9, password9);
//            clientList.getChildren().add(user9);
//
//            VBox user10 = new VBox(10);
//            Label email10 = new Label("ManfredAlbert@gmail.com");
//            Label password10 = new Label("password10");
//            user.getChildren().addAll(email10, password10);
//            clientList.getChildren().add(user10);
//
//
//            VBox user11 = new VBox(10);
//            Label type2 = new Label("Type : Supplier");
//            Label email11 = new Label("MichaelOliver@gmail.com");
//            Label password11 = new Label("password1");
//            user11.getChildren().addAll(type2 ,email11, password11);
//            supplierList.getChildren().add(user11);
//
//            VBox user12 = new VBox(10);
//            Label email12 = new Label("FrankieCharles@gmail.com");
//            Label password12 = new Label("password2");
//            user11.getChildren().addAll(email12, password12);
//            supplierList.getChildren().add(user12);
//
//            VBox user13 = new VBox(10);
//            Label email13 = new Label("JoshBurton@gmail.com");
//            Label password13 = new Label("password3");
//            user11.getChildren().addAll(email13, password13);
//            supplierList.getChildren().add(user13);
//
//            VBox user14 = new VBox(10);
//            Label email14 = new Label("SophieMoreno@gmail.com");
//            Label password14 = new Label("password4");
//            user11.getChildren().addAll(email14, password14);
//            supplierList.getChildren().add(user14);
//
//            VBox user15 = new VBox(10);
//            Label email15 = new Label("EdwardBlair@gmail.com");
//            Label password15 = new Label("password5");
//            user11.getChildren().addAll(email15, password15);
//            supplierList.getChildren().add(user15);

            clients.getItems().addAll(ClientList);
            suppliers.getItems().addAll(SupplierList);
            vbox.getChildren().addAll(clients, suppliers);
            Scene infoScene = new Scene(vbox, 500, 600);


            infoStage.setScene(infoScene);
            infoStage.show();
        }
    }

    /**
     * Opens a window that allows the user to change the  date and updates the data based on the new date. It allows
     * the user to input a new date updates the status of activities and removes clients and suppliers
     * based on the new date.
     */
    public void changeDate(){
        if (!dateBool || !manageDate.isShowing()) {
            manageDate = new Stage();
            manageDate.setTitle("Date Management");
            LocalDate currentDate = LocalDate.now();
            try(Reader reader = new FileReader("src/main/JsonFiles/currentDate.json")) {
                Gson gson = new Gson();
                currentDate = gson.fromJson(reader, LocalDate.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            VBox header = new VBox();
            Label title = new Label("Change Date");
            Label currentDateLabel = new Label("Current Date: " + currentDate.format(formatter));
            Label infoLabel = new Label("By changing the date, you can simulate the system on a different date.");
            infoLabel.setWrapText(true);
            header.getChildren().addAll(title,currentDateLabel, infoLabel);
            header.setSpacing(10);
            header.setAlignment(javafx.geometry.Pos.CENTER);

            TextField dateInput = new TextField();
            dateInput.setPromptText("Enter new date (yyyy-MM-dd)");

            Button confirmButton = new Button("Confirm");
            confirmButton.setOnAction(e -> {
                try {

                    LocalDate newDate = LocalDate.parse(dateInput.getText(), formatter);
                    ArrayList<Activity> activities = new ArrayList<>();
                    try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
                        Gson gson = new Gson();
                        clients = gson.fromJson(reader, new TypeToken<List<Client>>() {}.getType());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try(Reader reader = new FileReader("src/main/JsonFiles/activities.json")) {
                        Gson gson = new Gson();
                        activities = gson.fromJson(reader, new TypeToken<List<Activity>>() {}.getType());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    List<Client> clientsToRemove = new ArrayList<>();
                    for (Client client : clients) {
                        for (int i = 0; i < client.getMyActivities().size(); i++) {
                            if (client.getMyActivities().get(i).getStartDate().isBefore(newDate) &&
                                    client.getMyActivities().get(i).getEndDate().isAfter(newDate)) {
                                client.getMyActivities().get(i).setStatus("In Progress");
                            } else if (client.getMyActivities().get(i).getEndDate().isBefore(newDate)) {
                                client.getMyActivities().get(i).setStatus("Completed");
                            } else if (client.getMyActivities().get(i).getStartDate().isAfter(newDate)) {
                                client.getMyActivities().get(i).setStatus("Upcoming");
                            }
                        }
                        if (client.getConfirmationLink() != null && !client.getConfirmationLink().equals("null")) {
                            LocalDate dateOfCreation = LocalDate.parse(client.getConfirmationLink(), formatter);
                            if (dateOfCreation.isBefore(newDate)) {
                                clientsToRemove.add(client);
                            }
                        }
                    }
                    clients.removeAll(clientsToRemove);
                    for (Activity activity : activities) {
                        if (activity.getStartDate().isBefore(newDate) && activity.getEndDate().isAfter(newDate)) {
                            activity.setStatus("In Progress");
                        }
                        else if (activity.getEndDate().isBefore(newDate)) {
                            activity.setStatus("Finished");
                        }
                        else if (activity.getStartDate().isAfter(newDate)) {
                            activity.setStatus("Upcoming");
                        }
                    }
                    List<Supplier> suppliersToRemove = new ArrayList<>();
                    for (Supplier supplier : suppliers) {
                        if (supplier.getConfirmationLink() != null && !supplier.getConfirmationLink().equals("null")) {
                            LocalDate dateOfCreation = LocalDate.parse(supplier.getConfirmationLink(), formatter);
                            if (dateOfCreation.isBefore(newDate)) {
                                suppliersToRemove.add(supplier);
                            }
                        }
                    }
                    suppliers.removeAll(suppliersToRemove);
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                        gson.toJson(clients, writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try (Writer writer = new FileWriter("src/main/JsonFiles/activities.json")) {
                        gson.toJson(activities, writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try (Writer writer = new FileWriter("src/main/JsonFiles/currentDate.json")) {
                        gson.toJson(newDate, writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                        gson.toJson(suppliers, writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                    manageDate.close();
                    dateBool = false;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    dateInput.clear();
                    dateInput.setPromptText("Invalid date format. Please enter date in yyyy-MM-dd format.");


                }
            });



            VBox vbox = new VBox(header, dateInput, confirmButton);
            vbox.setPadding(new javafx.geometry.Insets(10));
            vbox.setSpacing(10);
            Scene infoScene = new Scene(vbox, 300, 200);

            manageDate.setScene(infoScene);
            manageDate.show();
            dateBool = true;
        }
    }
}