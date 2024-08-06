package Controller;

import Model.Robot;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class RobotControllerTest {

    private static Stage testStage;
    private RobotController robotController;
    private static CountDownLatch javafxLatch;

    @BeforeAll
    static void setUpClass() throws InterruptedException {
        if (!Platform.isFxApplicationThread()) {
            javafxLatch = new CountDownLatch(1);
            Platform.startup(() -> {
                javafxLatch.countDown(); // Notify that JavaFX has started
            });
            javafxLatch.await(); // Wait for JavaFX to initialize
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/RobotMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                robotController = loader.getController();
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
    void successGetRobotInfo() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                ArrayList<String> components = new ArrayList<>();
                components.add("test");
                Robot testRobot = new Robot("tests", "tests", components, "10", new float[]{0, 0, 0}, 10, 10, 10);
                String test = robotController.GetRobotInfoTest(testRobot);
                String location = String.format("%.1f, %.1f, %.1f", testRobot.getLocation()[0], testRobot.getLocation()[1], testRobot.getLocation()[2]);
                String cpuUsage = String.format("%.0f%%", testRobot.getCpuUsage());
                String expected = String.format("Robot Name: %s%n" +
                                "Robot Type: %s%n" +
                                "Robot Battery: %s%%%n" +
                                "Robot Speed: %.1fm/s%n" +
                                "Robot CpuUsage: %s%n" +
                                "Robot Memory: %.1fGB%n" +
                                "Robot Components: %s%n" +
                                "Robot Location: %s%n" +
                                "SerialNumber: %s%n",
                        testRobot.getName(),
                        testRobot.getType(),
                        testRobot.getBattery(),
                        testRobot.getSpeed(),
                        cpuUsage,
                        testRobot.getMemory(),
                        testRobot.getComponents(),
                        location,
                        testRobot.getSerialNumber());

                assertEquals(expected, test);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    public static class JavaFXTestApp extends Application {
        @Override
        public void start(Stage primaryStage) {
        }
    }
}