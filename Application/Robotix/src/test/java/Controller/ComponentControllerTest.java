package Controller;

import Model.Component;
import Model.TypeOfUsers.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentControllerTest {

    private ComponentController componentController;
    private Stage testStage;
    private Gson gson;
    private File supplierFile;

    @BeforeEach
    void setUp() throws Exception {
        gson = new GsonBuilder().setPrettyPrinting().create();
        supplierFile = new File("src/main/JsonFiles/supplier.json");

        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                setUpTestStage();
            } catch (Exception e) {
                e.printStackTrace();
                fail("Setup failed: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                if (testStage != null) {
                    testStage.close();
                    testStage = null;
                }
                resetSupplierFile(); // Reset the supplier file to its initial state
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    void setUpTestStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/MyComponentsMenu.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 768);
        testStage = new Stage();
        testStage.setScene(scene);
        componentController = loader.getController();
        testStage.show();
    }

    private void resetSupplierFile() throws IOException {
        // Logic to reset supplier.json to its initial state, if necessary
        // This could involve copying from a backup file or resetting contents
    }

    @Test
    void successfulDisplayComponent() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                Supplier supplierTest = createTestSupplier();
                Component componentTest = createTestComponent();

                List<String> expectedComponents = createExpectedComponentList(supplierTest);

                // Add supplier and component to file
                updateSupplierFile(supplierTest);

                List<String> result = componentController.displayComponentsTest(supplierTest);
                assertEquals(expectedComponents, result);

            } catch (IOException e) {
                e.printStackTrace();
                fail("Test failed: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    @Test
    void successfulAddComponent() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                Supplier supplierTest = createTestSupplier();
                Component componentToAdd = createTestComponent();

                // Add supplier to file
                updateSupplierFile(supplierTest);

                componentController.addComponentTest(supplierTest, componentToAdd);

                // Verify the component was added
                Supplier updatedSupplier = getLastSupplierFromFile();
                assertTrue(updatedSupplier.getStorage().contains(componentToAdd));

            } catch (IOException e) {
                e.printStackTrace();
                fail("Test failed: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    private Supplier createTestSupplier() {
        return new Supplier("supplierTest", "supplierTest", "supplierTest",
                "supplierTest", "supplierTest", "supplierTest", "supplierTest",
                10, false, "2024-11-11");
    }

    private Component createTestComponent() {
        return new Component("componentTest",
                new ArrayList<>(List.of("componentDescriptionTest")), 1.0f, 1.0f, 1.0f, 1.0f, UUID.randomUUID(), UUID.randomUUID());
    }

    private List<String> createExpectedComponentList(Supplier supplier) {
        List<String> components = new ArrayList<>();
        for (Component component : supplier.getStorage()) {
            components.add("Type: " + component.getType());
            components.add("Price: " + component.getPrice() + " $");
            components.add("Width: " + component.getWidth() + " cm");
            components.add("Length: " + component.getLength() + " cm");
            components.add("Height: " + component.getHeight() + " cm");
        }
        return components;
    }

    private void updateSupplierFile(Supplier supplier) throws IOException {
        List<Supplier> suppliers;
        try (Reader reader = new FileReader(supplierFile)) {
            suppliers = gson.fromJson(reader, new TypeToken<ArrayList<Supplier>>() {}.getType());
        }
        suppliers.add(supplier);
        try (Writer writer = new FileWriter(supplierFile)) {
            gson.toJson(suppliers, writer);
        }
    }

    private Supplier getLastSupplierFromFile() throws IOException {
        List<Supplier> suppliers;
        try (Reader reader = new FileReader(supplierFile)) {
            suppliers = gson.fromJson(reader, new TypeToken<ArrayList<Supplier>>() {}.getType());
        }
        return suppliers.get(suppliers.size() - 1);
    }
}