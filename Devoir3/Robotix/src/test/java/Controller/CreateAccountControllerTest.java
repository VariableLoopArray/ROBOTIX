package Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAccountControllerTest {

    Stage testStage;
    CreateAccountController createAccountController;

    @BeforeEach
    void setUp() throws Exception {
        Platform.startup(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/AccountCreationMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                // Get controller instance from FXMLLoader
                createAccountController = loader.getController();
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
    void FailEmailHandleCreateClientAccount() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            boolean result = createAccountController.handleClientCreateAccountTest(
                    "hello", "hello", "lol", "hellohello", "hello@",
                    "hello", "111-111-1111");

            System.out.println("result is " + result);

            latch.countDown();
            assertEquals(true, result);
        });
        latch.await();
    }

    @Test
    void successfulClearFields() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            int result = createAccountController.clearFieldsTest();
            latch.countDown();
            assertEquals(0, result);
        });
        latch.await();
    }
}