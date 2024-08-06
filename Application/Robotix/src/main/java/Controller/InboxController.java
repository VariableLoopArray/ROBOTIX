package Controller;

import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for handling the inbox interface, including notifications and emails.
 */
public class InboxController {
    @FXML
    private Client client;
    @FXML
    private Supplier supplier;
    @FXML
    private Button goBack;
    @FXML
    private ListView<String> notificationSpace;
    @FXML
    private ListView<String> emailSpace;
    @FXML
    private Button ToggleEmailOn;
    @FXML
    private Button ToggleEmailOff;
    private ObservableList<String> listItems;
    private ObservableList<String> notificationItems;
    private ObservableList<String> emailItems;



    public class NotificationListCell extends ListCell<String> {
        private final Label label;

        //constructor of the class
        public NotificationListCell() {
            label = new Label();
            label.setWrapText(true);
            label.setMaxWidth(Double.MAX_VALUE);

            VBox vbox = new VBox(label);
            vbox.prefWidthProperty().bind(this.widthProperty().subtract(30)); // Subtract some padding
            label.prefWidthProperty().bind(vbox.prefWidthProperty());
            setGraphic(vbox);
        }

        /**
         * Updates the cell's display based on the item added
         */
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                label.setText(item);
                setGraphic(label.getParent());
            }
        }
    }

    // Custom ListCell for emails with buttons
    public class EmailListCell extends ListCell<String> {
        private final VBox vbox;
        private final Label label;
        private final Button confirmButton;

        public EmailListCell() {
            label = new Label();
            label.setWrapText(true);
            label.setMaxWidth(Double.MAX_VALUE);

            confirmButton = new Button("Confirm");
            confirmButton.setOnAction(e -> handleConfirmButtonClick(getItem()));
            confirmButton.setVisible(false); // Initially hide the button
            confirmButton.setManaged(false); // Ensure it's not part of the layout's space

            vbox = new VBox(label, confirmButton);
            vbox.prefWidthProperty().bind(this.widthProperty().subtract(30));
            label.prefWidthProperty().bind(vbox.prefWidthProperty());
            setGraphic(vbox);
        }

        @Override

        /**
         * update the options based on user choice. If the item is chosen and confirmed
         * the confirm button is made visible. For any other item, the confirm button is hidden
         */
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                label.setText(item);
                if ("Click on the following button to confirm your account if not done in the next 24 hours your account will be deleted".equals(item)) {
                    confirmButton.setVisible(true);
                    confirmButton.setManaged(true);
                } else {
                    confirmButton.setVisible(false);
                    confirmButton.setManaged(false);
                }

                setGraphic(vbox);
            }
        }

        private void handleConfirmButtonClick(String item) {
            if (client != null) {
                try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
                    Gson gson = new Gson();
                    List<Client> clients = gson.fromJson(reader, new TypeToken<List<Client>>() {}.getType());
                    for (Client client : clients) {
                        if (client.getEmail().equals(InboxController.this.client.getEmail())) {
                            client.setConfirmationLink("null");
                            client.getNotifications().add("Email confirmed !");
                            try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                                gson.toJson(clients, writer);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            displayInbox();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (supplier != null) {
                try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                    Gson gson = new Gson();
                    List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>() {}.getType());
                    for (Supplier supplier : suppliers) {
                        if (supplier.getEmail().equals(InboxController.this.supplier.getEmail())) {
                            supplier.setConfirmationLink("null");
                            supplier.getNotifications().add("Email confirmed !");
                            try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                                gson.toJson(suppliers, writer);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            displayInbox();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Initializes the InboxController
     */
    @FXML
    public void initialize() {
        notificationSpace.setCellFactory(param -> new NotificationListCell());
        emailSpace.setCellFactory(param -> new EmailListCell());
    }


    /**
     * Sets the current user for the inbox view depending if it is an user or a supplier
     */
    public void setUserInbox(User user) {
        if (user instanceof Client) {
            try (FileReader reader = new FileReader("src/main/JsonFiles/client.json")) {
                Gson gson = new Gson();
                List<Client> clients = gson.fromJson(reader, new TypeToken<List<Client>>() {}.getType());
                for (Client client : clients) {
                    if (client.getEmail().equals(user.getEmail())) {
                        this.client = client;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (user instanceof Supplier) {
            try (FileReader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                Gson gson = new Gson();
                List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>() {}.getType());
                for (Supplier supplier : suppliers) {
                    if (supplier.getEmail().equals(user.getEmail())) {
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
     * Displays the inbox for the current user.
     */
    public void displayInbox() throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (client != null) {
            List<Client> clients = gson.fromJson(new FileReader("src/main/JsonFiles/client.json"), new TypeToken<List<Client>>() {}.getType());
            for (Client client : clients) {
                if (client.getEmail().equals(this.client.getEmail())) {
                    listItems = FXCollections.observableArrayList();
                    notificationSpace.setItems(listItems);
                    listItems.addAll(client.getNotifications());
                    emailSpace.setItems(FXCollections.observableArrayList(client.getEmailInbox()));
                    if (client.isToggleEmail()) {
                        ToggleEmailOff.setVisible(true);
                        ToggleEmailOff.setManaged(true);
                        ToggleEmailOn.setVisible(false);
                        ToggleEmailOn.setManaged(false);
                    } else {
                        ToggleEmailOn.setVisible(true);
                        ToggleEmailOn.setManaged(true);
                        ToggleEmailOff.setVisible(false);
                        ToggleEmailOff.setManaged(false);
                    }
                    break;
                }
            }
        } else if (supplier != null) {
            List<Supplier> suppliers = gson.fromJson(new FileReader("src/main/JsonFiles/supplier.json"), new TypeToken<List<Supplier>>() {}.getType());
            for (Supplier supplier : suppliers) {
                if (supplier.getEmail().equals(this.supplier.getEmail())) {
                    listItems = FXCollections.observableArrayList();
                    notificationSpace.setItems(listItems);
                    listItems.addAll(supplier.getNotifications());
                    emailSpace.setItems(FXCollections.observableArrayList(supplier.getEmailInbox()));
                    if (supplier.isToggleEmail()) {
                        ToggleEmailOff.setVisible(true);
                        ToggleEmailOff.setManaged(true);
                        ToggleEmailOn.setVisible(false);
                        ToggleEmailOn.setManaged(false);
                    } else {
                        ToggleEmailOn.setVisible(true);
                        ToggleEmailOn.setManaged(true);
                        ToggleEmailOff.setVisible(false);
                        ToggleEmailOff.setManaged(false);
                    }
                    break;
                }
            }
        }
    }

    /**
     *Handle to Go back option, returning to the home page
     */
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

    /**
     * Handles the action to enable email notifications for current user
     */
    public void handleToggleEmailOn() throws FileNotFoundException {
        if (client != null) {
            try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
                Gson gson = new Gson();
                List<Client> clients = gson.fromJson(reader, new TypeToken<List<Client>>() {}.getType());
                for (Client client : clients) {
                    if (client.getEmail().equals(this.client.getEmail())) {
                        client.setToggleEmail(true);
                        try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                            gson.toJson(clients, writer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (supplier != null) {
            try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                Gson gson = new Gson();
                List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>() {}.getType());
                for (Supplier supplier : suppliers) {
                    if (supplier.getEmail().equals(this.supplier.getEmail())) {
                        supplier.setToggleEmail(true);
                        try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                            gson.toJson(suppliers, writer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        displayInbox();
    }

    /**
     * Handles the action to close email notifications for current user
     */
    public void handleToggleEmailOff() throws FileNotFoundException {
        if (client != null) {
            try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
                Gson gson = new Gson();
                List<Client> clients = gson.fromJson(reader, new TypeToken<List<Client>>() {}.getType());
                for (Client client : clients) {
                    if (client.getEmail().equals(this.client.getEmail())) {
                        client.setToggleEmail(false);
                        try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                            gson.toJson(clients, writer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (supplier != null) {
            try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                Gson gson = new Gson();
                List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>() {}.getType());
                for (Supplier supplier : suppliers) {
                    if (supplier.getEmail().equals(this.supplier.getEmail())) {
                        supplier.setToggleEmail(false);
                        try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                            gson.toJson(suppliers, writer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        displayInbox();
    }
}