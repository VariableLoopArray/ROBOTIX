package Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAccountControllerTest {

    private static CountDownLatch javafxLatch = new CountDownLatch(1);
    private Stage testStage;
    private CreateAccountController createAccountController;

    // Starts the JavaFX runtime
    @BeforeAll
    static void startJavaFX() throws InterruptedException {
        Platform.startup(() -> {
            javafxLatch.countDown(); // JavaFX has started
        });
        javafxLatch.await(); // Wait until JavaFX is started
    }

    @BeforeEach
    void setUp() throws Exception {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/AccountCreationMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                createAccountController = loader.getController();
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
    void successEmailHandleCreateClientAccount() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                boolean result = createAccountController.handleClientCreateAccountTest(
                        "Hello", "Hello", "Hello", "HelloHello", "Hello@", "Hello", "111-111-1111");
                assertTrue(result);
            } finally {
                latch.countDown();
            }
        });
        latch.await();
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
        latch.await();
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
        latch.await();
    }
}