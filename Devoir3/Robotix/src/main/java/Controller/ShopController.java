package Controller;

import Model.Component;
import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
    private FlowPane productsFlowPane;
    @FXML
    private Client client;
    public void setUserShop(Client client) {
        this.client = client;
        shopWelcome.setText("Welcome to the shop " + client.getUsername());
    }
    public void displayALlComponents() {

        try(Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
             Gson gson = new GsonBuilder().create();
             Supplier[] suppliers = gson.fromJson(reader, Supplier[].class);
             for (Supplier supplier : suppliers) {
                 for (Component component : supplier.getStorage()) {;
                     VBox componentVBox = new VBox();
                     Label componentName = new Label(component.getName());
                     VBox componentInfo = new VBox();
                        Label componentPrice = new Label("Price: " + component.getPrice());
                        Label componentWidth = new Label("Width: " + component.getWidth());
                        Label componentLength = new Label("Length: " + component.getLength());
                        Label componentHeight = new Label("Height: " + component.getHeight());
                        Label componentSupplier = new Label("Supplier: " + supplier.getCompanyName());
                        componentInfo.getChildren().addAll(componentPrice, componentWidth, componentLength, componentHeight, componentSupplier);
                     Button addToCart = new Button("Add to cart");
                     componentVBox.getChildren().addAll(componentName, componentInfo, addToCart);
                     productsFlowPane.getChildren().add(componentVBox);
                 }
             }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        productsFlowPane.getChildren().clear();
        try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
            Gson gson = new GsonBuilder().create();
            Supplier[] suppliers = gson.fromJson(reader, Supplier[].class);
            ArrayList<Component> toBePrinted = new ArrayList<>();
            boolean noFiltersSelected = !filter1.isSelected()
                    && !filter2.isSelected()
                    && !filter3.isSelected()
                    && !filter4.isSelected()
                    && !filter5.isSelected()
                    && !filter6.isSelected()
                    && !filter7.isSelected()
                    && !filter8.isSelected()
                    && filter9.getText().isEmpty()
                    && filter10.getText().isEmpty();

            for (Supplier supplier : suppliers) {
                for (Component component : supplier.getStorage()) {
                    float size = component.getWidth() * component.getLength() * component.getHeight();
                    boolean addComponent = noFiltersSelected;

                    if (filter1.isSelected() && component.getPrice() < 10) {
                        addComponent = true;
                    } else if (filter2.isSelected() && component.getPrice() >= 10 && component.getPrice() < 50) {
                        addComponent = true;
                    } else if (filter3.isSelected() && component.getPrice() >= 50) {
                        addComponent = true;
                    } else if (filter4.isSelected()) {
                        addComponent = true;
                    }
                    if (filter5.isSelected() && size < 50) {
                        addComponent = true;
                    } else if (filter6.isSelected() && size >= 50 && size < 100) {
                        addComponent = true;
                    } else if (filter7.isSelected() && size >= 100) {
                        addComponent = true;
                    } else if (filter8.isSelected()) {
                        addComponent = true;
                    }

                    if (!filter9.getText().isEmpty()) {
                        try {
                            String[] tags = filter9.getText().split(",");
                            for (String tag : tags) {
                                if (component.getTag().contains(tag)) {
                                    addComponent = true;
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (!filter10.getText().isEmpty()) {
                        try {
                            String[] suppliersNames = filter10.getText().replace(" ", "").split(",");
                            for (String supplierName : suppliersNames) {
                                if (supplier.getUsername().contains(supplierName)) {
                                    addComponent = true;
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (addComponent) {
                        toBePrinted.add(component);
                    }
                }
            }

            for (Component component : toBePrinted) {
                VBox componentVBox = new VBox();
                Label componentName = new Label(component.getName());
                VBox componentInfo = new VBox();
                Label componentPrice = new Label("Price: " + component.getPrice());
                Label componentWidth = new Label("Width: " + component.getWidth());
                Label componentLength = new Label("Length: " + component.getLength());
                Label componentHeight = new Label("Height: " + component.getHeight());
                for (Supplier supplier1 : suppliers) {
                    if (supplier1.getId() == component.getSupplierID()) {
                        Label componentSupplier = new Label("Supplier: " + supplier1.getCompanyName());
                        componentInfo.getChildren().addAll(componentPrice, componentWidth, componentLength, componentHeight, componentSupplier);
                    }
                }
                Button addToCart = new Button("Add to cart");
                componentVBox.getChildren().addAll(componentName, componentInfo, addToCart);
                productsFlowPane.getChildren().add(componentVBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
