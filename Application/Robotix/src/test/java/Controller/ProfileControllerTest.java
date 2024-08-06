package Controller;

import Model.Robot;
import Model.TypeOfUsers.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
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

    private static Stage testStage;
    private ProfileController profileController;
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/ProfileMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                profileController = loader.getController();
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
    void successfulHandleSaveChanges() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            ArrayList<Client> clients = new ArrayList<>();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Client clientTest = new Client("clientTest1", "clientTest1", "clientTest1",
                    "clientTest1", "clientTest1", "clientTest1", "123-123-1234",
                    new ArrayList<>(), new ArrayList<>(), false, "2024-11-11");

            Client newClient = profileController.handleSaveChangesTest(clientTest, "changesTest1", "changesTest1@",
                    "changesTest1", "321-321-4321",
                    clientTest.getPassword(), "changesTest1");

            File clientFile = new File("src/main/JsonFiles/client.json");
            try {
                // Read existing clients
                try (Reader reader = new FileReader(clientFile)) {
                    clients = gson.fromJson(reader, new TypeToken<ArrayList<Client>>() {}.getType());
                }
                clients.add(clientTest);
                // Write updated clients
                try (Writer writer = new FileWriter(clientFile)) {
                    gson.toJson(clients, writer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            assertEquals("changesTest1", newClient.getUsername());
            assertEquals("changesTest1", newClient.getPassword());
            assertEquals("changesTest1@", newClient.getEmail());
            assertEquals("changesTest1", newClient.getCompanyName());
            assertEquals("321-321-4321", newClient.getPhoneNumber());

            clients.remove(clients.size() - 1);
            try (Writer writer = new FileWriter(clientFile)) {
                gson.toJson(clients, writer);
            } catch (IOException e) {
                e.printStackTrace();
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
                e.printStackTrace();
            }

            ArrayList<Object> userJsoninfo = new ArrayList<>();
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

            ArrayList<Object> result = profileController.displayProfileTest();

            assertEquals(userJsoninfo, result);
            latch.countDown();
        });
        latch.await();
    }

    public static class JavaFXTestApp extends Application {
        @Override
        public void start(Stage primaryStage) {
        }
    }
}