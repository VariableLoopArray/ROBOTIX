package Controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAccountControllerTest extends JavaFXBaseTest {

    private CreateAccountController createAccountController;

    @Override
    protected void setUpTestStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/AccountCreationMenu.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 768);
        testStage = new Stage();
        testStage.setScene(scene);
        createAccountController = loader.getController();
        testStage.show(); // Ensure the stage is shown for proper initialization
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