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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ShopControllerTest {

    private static Stage testStage;
    private ShopController shopController;
    private static CountDownLatch javafxLatch;

    @BeforeAll
    static void setUpClass() throws InterruptedException {
        javafxLatch = new CountDownLatch(1);
        Platform.startup(() -> {
            javafxLatch.countDown(); // Notify that JavaFX has started
        });
        javafxLatch.await(); // Wait for JavaFX to initialize
    }

    @BeforeEach
    void setUp() throws IOException {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/ShopMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                shopController = loader.getController();
                testStage.show(); // Ensure the stage is shown for proper initialization
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @AfterEach
    void tearDown() {
        Platform.runLater(() -> {
            if (testStage != null) {
                testStage.close();
            }
        });
    }

    @Test
    void successfulDisplayAllComponents() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                Gson gson = new GsonBuilder().create();
                Supplier[] suppliersArrays = gson.fromJson(reader, Supplier[].class);
                List<Supplier> suppliers = new ArrayList<>(List.of(suppliersArrays));
                List<String> componentNames = new ArrayList<>();
                List<String> componentsInfo = new ArrayList<>();

                for (Supplier supplier : suppliers) {
                    for (Component component : supplier.getStorage()) {
                        componentNames.add(component.getName());
                        componentsInfo.add("Price: " + component.getPrice());
                        componentsInfo.add("Width: " + component.getWidth());
                        componentsInfo.add("Length: " + component.getLength());
                        componentsInfo.add("Height: " + component.getHeight());
                        componentsInfo.add("Supplier: " + supplier.getCompanyName());
                    }
                }

                ArrayList<List<String>> result = shopController.displayAllComponentsTest();
                assertEquals(componentNames, result.get(0));
                assertEquals(componentsInfo, result.get(1));
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        latch.await();
    }

    public static class JavaFXTestApp extends Application {
        @Override
        public void start(Stage primaryStage) {
        }
    }
}