package Controller;

import Model.Component;
import Model.TypeOfUsers.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
    private Label componentWelcome;
    @FXML
    private FlowPane supplierComponents;
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
    private TextArea componentTag;
    @FXML
    private Button closeButton;
    @FXML
    private Label messageLabel;
    public void setUserComponent(Supplier supplier) {
        this.supplier = supplier;
        componentWelcome.setText("Welcome " + supplier.getCompanyName());
        addComponentGrid.managedProperty().bind(addComponentGrid.visibleProperty());
        closeButton.managedProperty().bind(closeButton.visibleProperty());
    }
    public void displayComponents(Supplier supplier){
        supplierComponents.getChildren().clear();
        for (Component component: supplier.getStorage()){
            VBox componentBox = new VBox();
            componentBox.getChildren().add(new Label(component.getName()));
            VBox componentSpecs = new VBox();
            componentSpecs.getChildren().add(new Label("Tag: " + component.getTag()));
            componentSpecs.getChildren().add(new Label("Price: " + component.getPrice()));
            componentSpecs.getChildren().add(new Label("Width: " + component.getWidth()));
            componentSpecs.getChildren().add(new Label("Length: " + component.getLength()));
            componentBox.getChildren().add(componentSpecs);
            HBox componentButtons = new HBox();
            Button editButton = new Button("Edit");
            editButton.setOnAction(e -> {
                // Create a dialog for editing the component
                Dialog<Component> dialog = new Dialog<>();
                dialog.setTitle("Edit Component");

                // Set the button types
                ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

                // Create the labels and fields
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField nameField = new TextField(component.getName());
                ListView<String> tagListView = new ListView<>();
                tagListView.getItems().addAll(component.getTag());
                tagListView.setPrefHeight(100);

                TextField newTagField = new TextField();
                Button addTagButton = new Button("Add Tag");
                addTagButton.setOnAction(ev -> {
                    String newTag = newTagField.getText();
                    if (!newTag.isEmpty() && !tagListView.getItems().contains(newTag)) {
                        tagListView.getItems().add(newTag);
                        newTagField.clear();
                    }
                });
                TextField priceField = new TextField(String.valueOf(component.getPrice()));
                TextField widthField = new TextField(String.valueOf(component.getWidth()));
                TextField lengthField = new TextField(String.valueOf(component.getLength()));

                grid.add(new Label("Name:"), 0, 0);
                grid.add(nameField, 1, 0);
                grid.add(new Label("Tags:"), 0, 1);
                grid.add(tagListView, 1, 1);
                grid.add(newTagField, 1, 2);
                grid.add(addTagButton, 1, 3);
                grid.add(new Label("Price:"), 0, 4);
                grid.add(priceField, 1, 4);
                grid.add(new Label("Width:"), 0, 5);
                grid.add(widthField, 1, 5);
                grid.add(new Label("Length:"), 0, 6);
                grid.add(lengthField, 1, 6);

                dialog.getDialogPane().setContent(grid);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == saveButtonType) {
                        component.setName(nameField.getText());
                        component.setTags(new ArrayList<>(tagListView.getItems()));
                        component.setPrice(Float.parseFloat(priceField.getText()));
                        component.setWidth(Float.parseFloat(widthField.getText()));
                        component.setLength(Float.parseFloat(lengthField.getText()));
                        return component;
                    }
                    return null;
                });

                Optional<Component> result = dialog.showAndWait();

                result.ifPresent(updatedComponent -> {
                    // Update the component in the supplier's storage
                    int index = supplier.getStorage().indexOf(component);
                    if (index >= 0) {
                        supplier.getStorage().set(index, updatedComponent);

                        // Update the JSON file
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

            supplierComponents.getChildren().add(componentBox);

        }

    }
    public void displayAddComponent(){
        addComponentGrid.setVisible(true);
        closeButton.setVisible(true);
    }
    public void closeDisplayAddComponent(){
        addComponentGrid.setVisible(false);
        closeButton.setVisible(false);
    }
    public void addComponent(){
        try {
            String allTags =componentTag.getText().replace(" ", "");
            String[] tag = allTags.split(",");
            ArrayList<String> tags = new ArrayList<>(Arrays.asList(tag));
            Component component = new Component(componentName.getText(), tags, Float.parseFloat(componentPrice.getText()),
                    Float.parseFloat(componentWidth.getText()), Float.parseFloat(componentLength.getText()), supplier.getId(), UUID.randomUUID());
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
            messageLabel.setText("error input or missing input");
        }
    }
    public void handleGoBack() {
        try {
            Stage stage = (Stage) componentWelcome.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FxmlPages/HomepageMenu.fxml"));
            Scene homepageMenu = new Scene(fxmlLoader.load(), 1024, 768);
            homepageMenu.getStylesheets().remove(getClass().getResource("/CssFiles/Component.css").toExternalForm());
            homepageMenu.getStylesheets().add(getClass().getResource("/CssFiles/Homepage.css").toExternalForm());
            stage.setTitle("Homepage");
            HomepageController homepageController = fxmlLoader.getController();
            homepageController.setUserHomepage(supplier);
            homepageController.displayCorrectMenu();
            stage.setScene(homepageMenu);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
