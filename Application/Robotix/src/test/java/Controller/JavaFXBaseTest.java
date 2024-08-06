package Controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.CountDownLatch;

public abstract class JavaFXBaseTest {

    protected Stage testStage;
    private static CountDownLatch javafxLatch = new CountDownLatch(1);

    @BeforeAll
    static void startJavaFX() throws InterruptedException {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> javafxLatch.countDown()); // Notify that JavaFX has started
            javafxLatch.await(); // Wait for JavaFX to initialize
        }
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        javafxLatch.await(); // Ensure JavaFX is started before setting up
        Platform.runLater(() -> {
            try {
                setUpTestStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    protected abstract void setUpTestStage() throws Exception;

    @AfterEach
    void tearDown() {
        Platform.runLater(() -> {
            if (testStage != null) {
                testStage.close();
            }
        });
    }
}