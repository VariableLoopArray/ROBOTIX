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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ComponentControllerTest {

    private Stage testStage;
    private ComponentController componentController;
    private static CountDownLatch javafxLatch;

    @BeforeAll
    static void setUpClass() throws InterruptedException {
        javafxLatch = new CountDownLatch(1);
        Platform.startup(() -> javafxLatch.countDown());
        javafxLatch.await(); // Ensure JavaFX is properly initialized
    }

    @BeforeEach
    void setUp() throws IOException {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/MyComponentsMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                componentController = loader.getController();
                testStage.show(); // Ensure the stage is shown
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
    void successfulDisplayComponent() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Supplier supplierTest = new Supplier("supplierTest", "supplierTest", "supplierTest",
                    "supplierTest", "supplierTest", "supplierTest", "supplierTest",
                    10, false, "2024-11-11");

            Component componentTest1 = new Component("componentTest1",
                    new ArrayList<>(List.of("componentDescriptionTest1")), 1.0f, 1.0f, 1.0f, 1.0f, UUID.randomUUID(), UUID.randomUUID());

            Component componentTestToADD = new Component("componentTestToAdd1",
                    new ArrayList<>(List.of("componentToAddDescriptionTest1")), 2.0f, 2.0f, 2.0f, 2.0f, UUID.randomUUID(), UUID.randomUUID());

            try {
                ArrayList<String> components = new ArrayList<>();
                ArrayList<Supplier> suppliers;

                try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                    suppliers = gson.fromJson(reader, new TypeToken<ArrayList<Supplier>>() {}.getType());
                }

                suppliers.add(supplierTest);

                try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                    gson.toJson(suppliers, writer);
                }

                for (Component component : supplierTest.getStorage()) {
                    components.add("Type: " + component.getType());
                    components.add("Price: " + component.getPrice() + " $");
                    components.add("Width: " + component.getWidth() + " cm");
                    components.add("Length: " + component.getLength() + " cm");
                    components.add("Height: " + component.getHeight() + " cm");
                }

                ArrayList<String> result = componentController.displayComponentsTest(supplierTest);

                assertEquals(components.size(), result.size());
                for (int i = 0; i < components.size(); i++) {
                    assertEquals(components.get(i), result.get(i));
                }

                suppliers.remove(suppliers.size() - 1);

                try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                    gson.toJson(suppliers, writer);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            latch.countDown();
        });
        latch.await();
    }

    @Test
    void successfulAddComponent() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Supplier supplierTest = new Supplier("supplierTest", "supplierTest", "supplierTest",
                    "supplierTest", "supplierTest", "supplierTest", "supplierTest",
                    10, false, "2024-11-11");

            Component componentTest1 = new Component("componentTest1",
                    new ArrayList<>(List.of("componentDescriptionTest1")), 1.0f, 1.0f, 1.0f, 1.0f, UUID.randomUUID(), UUID.randomUUID());

            Component componentTestToADD = new Component("componentTestToAdd1",
                    new ArrayList<>(List.of("componentToAddDescriptionTest1")), 2.0f, 2.0f, 2.0f, 2.0f, UUID.randomUUID(), UUID.randomUUID());

            List<Component> listOfComponentAtTheEnd = new ArrayList<>();

            listOfComponentAtTheEnd.add(componentTest1);
            listOfComponentAtTheEnd.add(componentTestToADD);

            supplierTest.getStorage().add(componentTest1);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try {
                ArrayList<Supplier> suppliersList;

                try (Reader reader = new FileReader("src/main/JsonFiles/supplier.json")) {
                    suppliersList = gson.fromJson(reader, new TypeToken<ArrayList<Supplier>>() {}.getType());
                }

                suppliersList.add(supplierTest);

                try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                    gson.toJson(suppliersList, writer);
                }

                componentController.addComponentTest(supplierTest, componentTestToADD);

                Supplier supplierTestAfter = suppliersList.get(suppliersList.size() - 1);

                assertEquals(supplierTest.getStorage().size(), supplierTestAfter.getStorage().size());
                for (int i = 0; i < supplierTestAfter.getStorage().size(); i++) {
                    assertEquals(supplierTestAfter.getStorage().get(i), supplierTest.getStorage().get(i));
                }

                suppliersList.remove(suppliersList.size() - 1);

                try (Writer writer = new FileWriter("src/main/JsonFiles/supplier.json")) {
                    gson.toJson(suppliersList, writer);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            latch.countDown();
        });
        latch.await();
    }
}