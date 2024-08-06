package Controller;

import Model.TypeOfUsers.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ProfileControllerTest {

    private ProfileController profileController;
    private Stage testStage;

    void setUpTestStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/ProfileMenu.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 768);
        testStage = new Stage();
        testStage.setScene(scene);
        profileController = loader.getController();
        testStage.show(); // Ensure the stage is shown for proper initialization
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                setUpTestStage();
            } catch (Exception e) {
                e.printStackTrace();
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
                    testStage = null; // Release the reference
                }
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    @Test
    void successfulHandleSaveChanges() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File clientFile = new File("src/main/JsonFiles/client.json");

            Client clientTest = new Client("clientTest1", "clientTest1", "clientTest1",
                    "clientTest1", "clientTest1", "clientTest1", "123-123-1234",
                    new ArrayList<>(), new ArrayList<>(), false, "2024-11-11");

            Client newClient = profileController.handleSaveChangesTest(clientTest, "changesTest1", "changesTest1@",
                    "changesTest1", "321-321-4321",
                    clientTest.getPassword(), "changesTest1");

            try {
                // Read existing clients
                List<Client> clients;
                try (Reader reader = new FileReader(clientFile)) {
                    clients = gson.fromJson(reader, new TypeToken<ArrayList<Client>>() {}.getType());
                } catch (IOException e) {
                    clients = new ArrayList<>();
                }
                clients.add(newClient);

                // Write updated clients
                try (Writer writer = new FileWriter(clientFile)) {
                    gson.toJson(clients, writer);
                }
            } catch (IOException e) {
                fail("IOException occurred: " + e.getMessage());
            }

            assertEquals("changesTest1", newClient.getUsername());
            assertEquals("changesTest1", newClient.getPassword());
            assertEquals("changesTest1@", newClient.getEmail());
            assertEquals("changesTest1", newClient.getCompanyName());
            assertEquals("321-321-4321", newClient.getPhoneNumber());

            // Clean up by removing the client added during the test
            try {
                List<Client> clients;
                try (Reader reader = new FileReader(clientFile)) {
                    clients = gson.fromJson(reader, new TypeToken<ArrayList<Client>>() {}.getType());
                }
                clients.remove(clients.size() - 1);
                try (Writer writer = new FileWriter(clientFile)) {
                    gson.toJson(clients, writer);
                }
            } catch (IOException e) {
                fail("IOException occurred during cleanup: " + e.getMessage());
            }

            latch.countDown();
        });
        latch.await();
    }

    @Test
    void successfulDisplayProfileTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Client client = null;
            try (Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
                Gson gson = new GsonBuilder().create();
                List<Client> clients = gson.fromJson(reader, new TypeToken<ArrayList<Client>>() {}.getType());
                client = clients.get(0);
            } catch (IOException e) {
                fail("IOException occurred while reading client file: " + e.getMessage());
            }

            ArrayList<Object> userJsoninfo = new ArrayList<>();
            if (client != null) {
                userJsoninfo.add(client.getFirstName() + " " + client.getLastName());
                userJsoninfo.add(client.getUsername());
                userJsoninfo.add(client.getEmail());
                userJsoninfo.add(client.getPhoneNumber());
                userJsoninfo.add(client.getCompanyName());

                ArrayList<String> userActivity = new ArrayList<>();
                client.getMyActivities().forEach(activity -> userActivity.add(" - " + activity.getName() + "\n"));
                ArrayList<String> userInterests = new ArrayList<>();
                client.getMyInterests().forEach(interest -> userInterests.add(" - " + interest + "\n"));
                ArrayList<String> userFleet = new ArrayList<>();
                client.getFleet().forEach(robot -> userFleet.add(" - " + robot.getName() + "\n"));

                userJsoninfo.add(userActivity);
                userJsoninfo.add(userInterests);
                userJsoninfo.add(userFleet);
            }

            ArrayList<Object> result = profileController.displayProfileTest();

            assertEquals(userJsoninfo, result);
            latch.countDown();
        });
        latch.await();
    }
}