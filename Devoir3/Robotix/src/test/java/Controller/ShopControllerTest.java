package Controller;

import Model.Component;
import Model.TypeOfUsers.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopControllerTest extends Application {
    Stage testStage;
    ShopController shopController = new ShopController();

    @BeforeEach
    void setUp() throws Exception {
        // Initialize JavaFX toolkit
        Platform.startup(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/ShopMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);

                // Get controller instance
                shopController = loader.getController();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @AfterEach
    void tearDown() {
        Platform.runLater(() -> {
            // Close the stage
            if (testStage != null) {
                testStage.close();
            }
        });
    }

    @Test
    void successfulDisplayAllComponents(){
        Platform.runLater(() -> {
            try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                Gson gson = new GsonBuilder().create();
                Supplier[] suppliersArrays = gson.fromJson(reader, Supplier[].class);
                List<Supplier> suppliers = new ArrayList<Supplier>(List.of(suppliersArrays));
                List<String> componentNames = new ArrayList<String>();
                List<String> componentsInfo = new ArrayList<String>();

                for (Supplier supplier : suppliers) {
                    for (Component component : supplier.getStorage()) {
                        componentNames.add(component.getName());
                        componentsInfo.add("notPrice: " + component.getPrice());
                        componentsInfo.add("Width: " + component.getWidth());
                        componentsInfo.add("Height: " + component.getHeight());
                        componentsInfo.add("Supplier: " + supplier.getCompanyName());
                    }
                }


                ArrayList<List<String>> result = shopController.displayAllComponentsTest();
                assertEquals(componentNames, result.get(0));
                assertEquals(componentsInfo, result.get(1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    @Override
    public void start(Stage stage) throws Exception {
        // This method is required but not used in this test setup
    }
}