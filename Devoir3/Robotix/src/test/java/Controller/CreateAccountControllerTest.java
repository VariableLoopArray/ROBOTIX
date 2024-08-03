package Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAccountControllerTest extends Application {

    Stage testStage;
    CreateAccountController createAccountController = new CreateAccountController();

    @BeforeEach
    void setUp() throws Exception {
        // Initialize JavaFX toolkit
        Platform.startup(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/AccountCreationMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);

                // Get controller instance
                createAccountController = loader.getController();
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
    void successfulEmailHandleCreateClientAccount() {
        Platform.runLater(() -> {


            boolean result = createAccountController.handleClientCreateAccountTest("hello","hello",
                    "hello","hellohello","hello@","hello","111-111-1111");

            // Perform assertions
            assertTrue(result);
        });
    }

    @Test
    void successfulClearFields(){
        Platform.runLater(() -> {
            int result = createAccountController.clearFieldsTest();
            assertEquals(0, result);
        });
    }
    @Override
    public void start(Stage stage) throws Exception {
        // This method is required but not used in this test setup
    }
}