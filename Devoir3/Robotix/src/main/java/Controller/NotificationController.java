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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class NotificationController {
    @FXML
    private Client client;
    @FXML
    private Supplier supplier;
    @FXML
    private Button goBack;
    @FXML
    private ListView<String> notificationSpace;
    private ObservableList<String> listItems;

    public void setUserNotification(User user) {
        if (user instanceof Client) {
            try(FileReader reader = new FileReader("src/main/JsonFiles/client.json")) {
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
            try(FileReader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
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

    public void displayNotifications() throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (client != null) {
            List<Client> clients = gson.fromJson(new FileReader("src/main/JsonFiles/client.json"), new TypeToken<List<Client>>() {}.getType());
            for (Client client : clients) {
                if (client.getEmail().equals(this.client.getEmail())) {
                    listItems = FXCollections.observableArrayList();
                    notificationSpace.setItems(listItems);

                    listItems.addAll(client.getNotifications());
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
                    break;
                }
            }
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
