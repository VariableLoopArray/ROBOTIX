package Controller;

import Model.TypeOfUsers.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAccountControllerTest {

    private CreateAccountController createAccountController;
    private Stage testStage;

    @BeforeEach
    void setUp() throws InterruptedException {
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
        if (!latch.await(5, TimeUnit.SECONDS)) {
            fail("Setup timed out");
        }
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            if (testStage != null) {
                testStage.close();
                testStage = null; // Release the reference
            }
            latch.countDown();
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            fail("Teardown timed out");
        }
    }

    void setUpTestStage() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/AccountCreationMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                createAccountController = loader.getController();
                testStage.show(); // Assurez-vous que la scène est affichée
            } catch (Exception e) {
                e.printStackTrace();
                fail("Setup failed: " + e.getMessage());
            }
        });
    }

    @Test
    void successEmailHandleCreateClientAccount() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                boolean result = createAccountController.handleClientCreateAccountTest(
                        "Hello", "Hello", "Hello", "HelloHello", "Hello@", "Hello", "111-111-1111");
                ArrayList<Client> clients = null;
                try(Reader reader = new java.io.FileReader("src/main/JsonFiles/client.json")) {
                    Gson gson = new GsonBuilder().create();
                    clients = new ArrayList<>(List.of(gson.fromJson(reader, Client[].class)));
                } catch (IOException e) {
                    e.printStackTrace();
                    fail("Failed to read client.json: " + e.getMessage());
                }
                clients.removeLast();
                try(Writer writer = new FileWriter("src/main/JsonFiles/client.json")) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    gson.toJson(clients, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                    fail("Failed to write to client.json: " + e.getMessage());
                }
                assertTrue(result);
            } finally {
                latch.countDown();
            }
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            fail("Test timed out");
        }
    }

    @Test
    void successfulClearFields() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                int result = createAccountController.clearFieldsTest();
                assertEquals(0, result);
            } finally {
                latch.countDown();
            }
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            fail("Test timed out");
        }
    }

    @Test
    void failHandleCreateClientAccount() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                boolean result = createAccountController.handleClientCreateAccountTest(
                        "Hello", "Hello", "Hello", "HelloHello", "Hello@", "Hello", "hhh-hhh-hhhh");
                assertFalse(result);
            } finally {
                latch.countDown();
            }
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            fail("Test timed out");
        }
    }
}