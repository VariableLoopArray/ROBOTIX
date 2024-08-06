package Controller;

import Model.Component;
import Model.TypeOfUsers.Supplier;
import Model.TypeOfUsers.Client;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class ComponentController {
    @FXML
    Supplier supplier;
    @FXML
    Client client;
    @FXML
    private Label componentWelcome;
    @FXML
    private FlowPane userComponents;
    @FXML
    private GridPane addComponentGrid;
    @FXML
    private TextField componentName;
    @FXML
    private TextField componentPrice;
    @FXML
    private TextField componentWidth;
    @FXML
    private TextField componentLength;
    @FXML
    private TextField componentHeight;
    @FXML
    private TextArea componentType;
    @FXML
    private Button closeButton;
    @FXML
    private ScrollPane mainScrollClient;
    @FXML
    private ScrollPane mainScroll;
    @FXML
    private FlowPane clientComponents;
    @FXML
    private Label messageLabel;



    /**
     * Sets the user and displays their components based on their type.
     * If the user is a supplier, the method loads the supplier data from the JSON file,
     * sets the supplier, updates the welcome message, and displays the supplier's components.
     * If the user is a Client, the method loads the client data and updates the welcome message,
     */
    public void setUserComponent(User user) {
        if (user instanceof Supplier) {
            try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>() {
                }.getType());
                for (Supplier supplier : suppliers) {
                    if (supplier.getId().equals(user.getId())) {
                        this.supplier = supplier;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            componentWelcome.setText("Welcome to your inventory !");
            displayComponents(supplier);
        } else {
            try(Reader reader = new FileReader("src/main/JsonFiles/client.json")){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                List<Client> clients = gson.fromJson(reader, new TypeToken<List<Client>>(){}.getType());
                for (Client client: clients){
                    if (client.getId().equals(user.getId())){
                        this.client = client;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.client = (Client) user;
            componentWelcome.setText("Welcome to your inventory !");
            displayComponents(client);
        }

    }

    /**
     * Display user components of the client
     */
    public void displayComponents(Client client){
        clientComponents.getChildren().clear();
        mainScroll.setVisible(false);
        mainScroll.managedProperty().bind(mainScroll.visibleProperty());
        clientComponents.setVisible(true);
        clientComponents.managedProperty().bind(clientComponents.visibleProperty());
        if (client.getStorage() == null){
            return;
        }
        for (Component component: client.getStorage()) {
            VBox componentBox = new VBox();
            componentBox.getChildren().add(new Label(component.getName()));
            VBox componentSpecs = new VBox();
            componentSpecs.getChildren().add(new Label("type: " + component.getType()));
            componentSpecs.getChildren().add(new Label("Price: " + component.getPrice() + " $"));
            componentSpecs.getChildren().add(new Label("Width: " + component.getWidth() + " cm"));
            componentSpecs.getChildren().add(new Label("Length: " + component.getLength() + " cm"));
            componentSpecs.getChildren().add(new Label("Height: " + component.getHeight() + " cm"));
            componentBox.getChildren().add(componentSpecs);
            componentBox.getStyleClass().add("componentBox");
            componentSpecs.getStyleClass().add("componentSpecs");
            clientComponents.getChildren().add(componentBox);
        }
    }

    /**
     * Display user components of the supplier
     */
    public void displayComponents(Supplier supplier){
        userComponents.getChildren().clear();
        mainScrollClient.setVisible(false);
        mainScrollClient.managedProperty().bind(mainScrollClient.visibleProperty());
        addComponentGrid.managedProperty().bind(addComponentGrid.visibleProperty());
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        if (supplier.getStorage() == null){
            return;
        }
        for (Component component: supplier.getStorage()){
            VBox componentBox = new VBox();
            componentBox.getChildren().add(new Label(component.getName()));
            VBox componentSpecs = new VBox();
            componentSpecs.getChildren().add(new Label("Type: " + component.getType()));
            componentSpecs.getChildren().add(new Label("Price: " + component.getPrice() + " $"));
            componentSpecs.getChildren().add(new Label("Width: " + component.getWidth() + " cm"));
            componentSpecs.getChildren().add(new Label("Length: " + component.getLength()+ " cm"));
            componentSpecs.getChildren().add(new Label("Height: " + component.getHeight()+ " cm"));
            componentBox.getChildren().add(componentSpecs);
            HBox componentButtons = new HBox();
            Button editButton = new Button("Edit");
            editButton.setOnAction(e -> {
                Dialog<Component> dialog = new Dialog<>();
                dialog.setTitle("Edit Component");

                ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField nameField = new TextField(component.getName());
                ListView<String> typeListView = new ListView<>();
                typeListView.getItems().addAll(component.getType());
                typeListView.setPrefHeight(100);

                TextField newTypeField = new TextField();
                Button addTypeButton = new Button("Add Type");
                addTypeButton.setOnAction(ev -> {
                    String newType = newTypeField.getText();
                    if (!newType.isEmpty() && !typeListView.getItems().contains(newType)) {
                        typeListView.getItems().add(newType);
                        newTypeField.clear();
                    }
                });
                TextField priceField = new TextField(String.valueOf(component.getPrice()));
                TextField widthField = new TextField(String.valueOf(component.getWidth()));
                TextField lengthField = new TextField(String.valueOf(component.getLength()));
                TextField heightField = new TextField(String.valueOf(component.getHeight()));



                grid.add(new Label("Name:"), 0, 0);
                grid.add(nameField, 1, 0);
                grid.add(new Label("Tags:"), 0, 1);
                grid.add(typeListView, 1, 1);
                grid.add(newTypeField, 1, 2);
                grid.add(addTypeButton, 1, 3);
                grid.add(new Label("Price:"), 0, 4);
                grid.add(priceField, 1, 4);
                grid.add(new Label("Width:"), 0, 5);
                grid.add(widthField, 1, 5);
                grid.add(new Label("Length:"), 0, 6);
                grid.add(lengthField, 1, 6);
                grid.add(new Label("Height:"), 0, 7);
                grid.add(heightField, 1, 7);

                dialog.getDialogPane().setContent(grid);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == saveButtonType) {
                        component.setName(nameField.getText());
                        component.setTypes(new ArrayList<>(typeListView.getItems()));
                        component.setPrice(Float.parseFloat(priceField.getText()));
                        component.setWidth(Float.parseFloat(widthField.getText()));
                        component.setLength(Float.parseFloat(lengthField.getText()));
                        component.setHeight(Float.parseFloat(heightField.getText()));
                        return component;
                    }
                    return null;
                });

                Optional<Component> result = dialog.showAndWait();

                result.ifPresent(updatedComponent -> {
                    int index = supplier.getStorage().indexOf(component);
                    if (index >= 0) {
                        supplier.getStorage().set(index, updatedComponent);

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                            List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>() {
                            }.getType());
                            if (suppliers == null) {
                                suppliers = new ArrayList<>();
                            }
                            for (Supplier supplier1 : suppliers) {
                                if (supplier1.getId().equals(supplier.getId())) {
                                    supplier1.setStorage(supplier.getStorage());
                                }
                            }
                            try (FileWriter writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                                gson.toJson(suppliers, writer);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        // Refresh the display
                        displayComponents(supplier);
                    }
                });
            });
            Pane space = new Pane();
            space.setPrefWidth(20);
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> {
                supplier.getStorage().remove(component);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                    List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>(){}.getType());
                    if (suppliers == null){
                        suppliers = new ArrayList<>();
                    }
                    for (Supplier supplier1: suppliers){
                        if (supplier1.getId().equals(this.supplier.getId())){
                            supplier1.setStorage(this.supplier.getStorage());
                        }
                    }
                    try (FileWriter writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                        gson.toJson(suppliers, writer);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                displayComponents(supplier);
            });
            componentButtons.getChildren().add(editButton);
            componentButtons.getChildren().add(space);
            componentButtons.getChildren().add(deleteButton);
            componentBox.getChildren().add(componentButtons);
            componentBox.getStyleClass().add("componentBox");
            componentSpecs.getStyleClass().add("componentSpecs");
            componentButtons.getStyleClass().add("componentButtons");

            userComponents.getChildren().add(componentBox);

        }

    }

    /**
     * Displays the component addition interface by making it visible.
     */
    public void displayAddComponent(){
        addComponentGrid.setVisible(true);
        closeButton.setVisible(true);
    }

    /**
     * Closer the component addition interface by making it not visible.
     */
    public void closeDisplayAddComponent(){
        addComponentGrid.setVisible(false);
        closeButton.setVisible(false);
    }


    /**
     * Adds a new component to the supplier's storage and updates the supplier's data
     * If there is an error during the process, an error message is displayed.
     */
    public void addComponent(){
        try {
            String allTypes = componentType.getText().replace(" ", "");
            String[] type = allTypes.split(",");
            ArrayList<String> types = new ArrayList<>(Arrays.asList(type));
            Component component = new Component(componentName.getText(), types, Float.parseFloat(componentPrice.getText()),
                    Float.parseFloat(componentWidth.getText()), Float.parseFloat(componentLength.getText()),
                    Float.parseFloat(componentHeight.getText()), supplier.getId(), UUID.randomUUID());
            if (supplier.getStorage() == null){
                supplier.setStorage(new ArrayList<>());
            }
            supplier.getStorage().add(component);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                List<Supplier> suppliers = gson.fromJson(reader, new TypeToken<List<Supplier>>(){}.getType());
                if (suppliers == null){
                    suppliers = new ArrayList<>();
                }
                for (Supplier supplier: suppliers){
                    if (supplier.getId().equals(this.supplier.getId())){
                        supplier.setStorage(this.supplier.getStorage());
                    }
                }
                try (FileWriter writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                    gson.toJson(suppliers, writer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            displayComponents(supplier);
            closeDisplayAddComponent();
        }
        catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("error input or missing input");
        }
    }

    /**
     * handle the go back button, return to the previous interface
     */
    public void handleGoBack() {
        try {
            Stage stage = (Stage) componentWelcome.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
            homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Component.css").toExternalForm());
            homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            stage.setTitle("Homepage");
            HomepageController homepageController = fxmlLoader.getController();
            if (supplier != null) {
                homepageController.setUserHomepage(supplier);
                homepageController.displayCorrectMenu();
            } else {
                homepageController.setUserHomepage(client);
                homepageController.displayRobotixActivities();
            }
            homepageController.displayCorrectMenu();
            stage.setScene(homepageMenu);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // TEST FUNCTIONS

    public void addComponentTest(Supplier supplierTest, Component componentTest1){

        supplier = supplierTest;

        componentName.setText(componentTest1.getName());
        componentPrice.setText(String.valueOf(componentTest1.getPrice()));
        componentWidth.setText(String.valueOf(componentTest1.getWidth()));
        componentLength.setText(String.valueOf(componentTest1.getLength()));
        componentHeight.setText(String.valueOf(componentTest1.getHeight()));

        String types = "";
        for (int i = 0; i < componentTest1.getType().size() - 1; i ++){
            types += componentTest1.getType().get(i) + ", ";
        }
        types += componentTest1.getType().getLast();

        componentType.setText(types);

        addComponent();


    }

    public ArrayList<String> displayComponentsTest(Supplier supplierTest){
        supplier = supplierTest;
        displayComponents(supplier);

        ArrayList<String> components = new ArrayList<String>();

        for (Node childNode : userComponents.getChildren()){
            FlowPane child = (FlowPane) childNode;
            for (int i = 0; i < child.getChildren().size(); i++ ){
                Label info = (Label) child.getChildren().get(i);
                components.add(info.getText());
            }
        }



        return components;
    }
}

