package Controller;

import Model.Activity;
import Model.Component;
import Model.TypeOfUsers.Client;
import Model.TypeOfUsers.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
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
    private Label thankYouMessage;
    @FXML
    private Client client;

    public void setUserShop(Client client) {
        try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
            Gson gson = new GsonBuilder().create();
            List<Client> clients = gson.fromJson(reader, new TypeToken<List<Client>>() {}.getType());

            for (Client client1 : clients) {
                if (client1.getId().equals(client.getId())) {
                    this.client = client1;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        shopWelcome.setText("Welcome to the Robotix shop !");
    }


    public void displayALlComponents() {
        productsFlowPane.getChildren().clear();
        try(Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
             Gson gson = new GsonBuilder().create();
             Supplier[] suppliers = gson.fromJson(reader, Supplier[].class);
             if (suppliers == null) {
                 return;
             }
             for (Supplier supplier : suppliers) {
                 if (supplier.getStorage() == null) {
                     continue;
                 }
                 for (Component component : supplier.getStorage()) {;
                     VBox componentVBox = new VBox();
                     Label componentName = new Label(component.getName());
                     componentName.setWrapText(true);
                     VBox componentInfo = new VBox();
                        Label componentPrice = new Label("Price: " + component.getPrice());
                        Label componentWidth = new Label("Width: " + component.getWidth());
                        Label componentLength = new Label("Length: " + component.getLength());
                        Label componentHeight = new Label("Height: " + component.getHeight());
                        Label componentSupplier = new Label("Supplier: " + supplier.getCompanyName());
                        componentInfo.getChildren().addAll(componentPrice, componentWidth, componentLength, componentHeight, componentSupplier);
                        componentInfo.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
                     Button buyNow = new Button("Buy now");
                     buyNow.setOnAction(e -> {
                         if (client.getStorage() == null) {
                             client.setStorage(new ArrayList<>());
                         }
                         List<Client> clients = new ArrayList<>();
                         client.getStorage().add(component);
                         try(Reader reader1 = new FileReader("src/main/JsonFiles/client.json")) {
                             Gson gson1 = new GsonBuilder().create();
                             Type type = new TypeToken<List<Client>>() {}.getType();
                             clients = gson1.fromJson(reader1, type);
                             for (Client client1 : clients) {
                                 if (client1.getId().equals(client.getId())) {
                                     client1.setStorage(client.getStorage());
                                 }
                             }
                         } catch (Exception e1) {
                             e1.printStackTrace();
                         }
                         List<Supplier> suppliers1 = new ArrayList<>();
                         for (Supplier supplier1 : suppliers) {
                             if (supplier1.getId().equals(component.getSupplierID())) {
                                 supplier1.getStorage().remove(component);
                             }
                         }
                         try(Reader reader1 = new FileReader("src/main/JsonFiles/supplier.json")) {
                             Gson gson1 = new GsonBuilder().create();
                             Type type = new TypeToken<List<Supplier>>() {}.getType();
                             suppliers1 = gson1.fromJson(reader1, type);
                             for (Supplier supplier1 : suppliers1) {
                                 if (supplier1.getId().equals(component.getSupplierID())) {
                                     supplier1.setStorage(supplier1.getStorage());
                                     supplier.getNotifications().add("Your component " + component.getName() + " has been bought by " + client.getUsername() +"!");
                                     if (supplier.isToggleEmail()){
                                         supplier.getEmailInbox().add("Your component " + component.getName() + " has been bought by " + client.getUsername() + "!");
                                     }
                                 }
                             }
                         } catch (Exception e1) {
                             e1.printStackTrace();
                         }
                         try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                             gson.toJson(clients, writer);
                         } catch (Exception e1) {
                             e1.printStackTrace();
                         }
                         try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                             gson.toJson(suppliers, writer);
                         } catch (Exception e1) {
                             e1.printStackTrace();
                         }
                         thankYouMessage.setText("Thank you for your purchase");
                         displayALlComponents();
                     });
                     componentVBox.getChildren().addAll(componentName,componentInfo, buyNow);
                     componentVBox.getStyleClass().add("componentVbox");
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
            homepageController.displayRobotixActivities();
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
            if (suppliers == null) {
                return;
            }
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

                            String[] types = filter9.getText().replace(" ", "").split(",");
                            for (String type : types) {
                                if (component.getType().contains(type)) {
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
                                if (supplier.getUsername().equals(supplierName)) {
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
                    if (supplier1.getId().equals(component.getSupplierID())) {
                        Label componentSupplier = new Label("Supplier: " + supplier1.getCompanyName());
                        componentInfo.getChildren().addAll(componentPrice, componentWidth, componentLength, componentHeight, componentSupplier);
                        break;
                    }
                }
                Button buyNow = new Button("Buy now");
                buyNow.setOnAction(e -> {
                    if (client.getStorage() == null) {
                        client.setStorage(new ArrayList<>());
                    }
                    List<Client> clients = new ArrayList<>();
                    client.getStorage().add(component);
                    try(Reader reader1 = new FileReader("src/main/JsonFiles/client.json")) {
                        Gson gson1 = new GsonBuilder().create();
                        Type type = new TypeToken<List<Client>>() {}.getType();
                        clients = gson1.fromJson(reader1, type);
                        for (Client client1 : clients) {
                            if (client1.getId().equals(client.getId())) {
                                client1.setStorage(client.getStorage());
                            }
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    List<Supplier> suppliers1 = new ArrayList<>();
                    for (Supplier supplier : suppliers) {
                        if (supplier.getId().equals(component.getSupplierID())) {
                            supplier.getStorage().remove(component);
                            supplier.getNotifications().add("Your component " + component.getName() + " has been bought by " + client.getUsername() + "!");
                            if (supplier.isToggleEmail()){
                                supplier.getEmailInbox().add("Your component " + component.getName() + " has been bought by " + client.getUsername() + "!");
                            }
                        }
                    }
                    try (Reader reader1 = new FileReader("src/main/JsonFiles/supplier.json")) {
                        Gson gson1 = new GsonBuilder().create();
                        Type type = new TypeToken<List<Supplier>>() {
                        }.getType();
                        suppliers1 = gson1.fromJson(reader1, type);
                        for (Supplier supplier : suppliers1) {
                            if (supplier.getId().equals(component.getSupplierID())) {
                                supplier.setStorage(supplier.getStorage());
                            }
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                        gson.toJson(clients, writer);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                        gson.toJson(suppliers, writer);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    thankYouMessage.setText("Thank you for your purchase");
                    applyFilters();
                });
                componentVBox.getChildren().addAll(componentName, componentInfo, buyNow);
                componentVBox.getStyleClass().add("componentVbox");
                productsFlowPane.getChildren().add(componentVBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // TEST FUNCTIONS

    public ArrayList<List<String>> displayAllComponentsTest() {
        ArrayList<List<String>> namesAndInfos = new ArrayList<List<String>>();
        try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {

            List<String> names = new ArrayList<String>();
            List<String> infos = new ArrayList<String>();

            displayALlComponents();

            for (Node childNode : productsFlowPane.getChildren()) {
                VBox child = (VBox) childNode;
                for (int i = 0; i < child.getChildren().size(); i++) {
                    if (i % 3 == 0) {
                        Label nameArea = (Label) child.getChildren().get(i);
                        String name = nameArea.getText();
                        names.add(name);
                    }
                    if (i % 3 == 1) {
                        VBox nodeChild = (VBox) child.getChildren().get(i);
                        for (Node infoNode : nodeChild.getChildren()) {
                            Label info = (Label) infoNode;
                            infos.add(info.getText());
                        }
                    }
                }
            }
            namesAndInfos.add(names);
            namesAndInfos.add(infos);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return namesAndInfos;
    }
}
//    public void displayALlComponents() {
//        productsFlowPane.getChildren().clear();
//        try(Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
//            Gson gson = new GsonBuilder().create();
//            Supplier[] suppliers = gson.fromJson(reader, Supplier[].class);
//            if (suppliers == null) {
//                return;
//            }
//            for (Supplier supplier : suppliers) {
//                if (supplier.getStorage() == null) {
//                    continue;
//                }
//                for (Component component : supplier.getStorage()) {;
//                    VBox componentVBox = new VBox();
//                    Label componentName = new Label(component.getName());
//                    VBox componentInfo = new VBox();
//                    Label componentPrice = new Label("Price: " + component.getPrice());
//                    Label componentWidth = new Label("Width: " + component.getWidth());
//                    Label componentLength = new Label("Length: " + component.getLength());
//                    Label componentHeight = new Label("Height: " + component.getHeight());
//                    Label componentSupplier = new Label("Supplier: " + supplier.getCompanyName());
//                    componentInfo.getChildren().addAll(componentPrice, componentWidth, componentLength, componentHeight, componentSupplier);
//                    Button buyNow = new Button("Buy now");
//                    buyNow.setOnAction(e -> {
//                        if (client.getStorage() == null) {
//                            client.setStorage(new ArrayList<>());
//                        }
//                        List<Client> clients = new ArrayList<>();
//                        client.getStorage().add(component);
//                        try(Reader reader1 = new FileReader("src/main/JsonFiles/client.json")) {
//                            Gson gson1 = new GsonBuilder().create();
//                            Type type = new TypeToken<List<Client>>() {}.getType();
//                            clients = gson1.fromJson(reader1, type);
//                            for (Client client1 : clients) {
//                                if (client1.getId().equals(client.getId())) {
//                                    client1.setStorage(client.getStorage());
//                                }
//                            }
//                        } catch (Exception e1) {
//                            e1.printStackTrace();
//                        }
//                        List<Supplier> suppliers1 = new ArrayList<>();
//                        for (Supplier supplier1 : suppliers) {
//                            if (supplier1.getId().equals(component.getSupplierID())) {
//                                supplier1.getStorage().remove(component);
//                            }
//                        }
//                        try(Reader reader1 = new FileReader("src/main/JsonFiles/supplier.json")) {
//                            Gson gson1 = new GsonBuilder().create();
//                            Type type = new TypeToken<List<Supplier>>() {}.getType();
//                            suppliers1 = gson1.fromJson(reader1, type);
//                            for (Supplier supplier1 : suppliers1) {
//                                if (supplier1.getId().equals(component.getSupplierID())) {
//                                    supplier1.setStorage(supplier1.getStorage());
//                                    supplier.getNotifications().add("Your component " + component.getName() + " has been bought by " + client.getUsername() +"!");
//                                }
//                            }
//                        } catch (Exception e1) {
//                            e1.printStackTrace();
//                        }
//                        try (Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
//                            gson.toJson(clients, writer);
//                        } catch (Exception e1) {
//                            e1.printStackTrace();
//                        }
//                        try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
//                            gson.toJson(suppliers, writer);
//                        } catch (Exception e1) {
//                            e1.printStackTrace();
//                        }
//                        thankYouMessage.setText("Thank you for your purchase");
//                        displayALlComponents();
//                    });
//                    componentVBox.getChildren().addAll(componentName, componentInfo, buyNow);
//                    productsFlowPane.getChildren().add(componentVBox);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

