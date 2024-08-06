package TestController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import Controller.RobotController;
import Model.Robot;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RobotControllerTest {

    Stage testStage;
    RobotController robotController;

    @BeforeEach
    void setUp() throws Exception {
        Platform.startup(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/RobotMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                robotController = loader.getController();
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
            ArrayList<String> components = new ArrayList<String>();
            components.add("test");
            Robot testRobot = new Robot("tests", "tests", components , "10", new float[]{0,0,0}, 10, 10, 10);
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
            latch.countDown();

            assertEquals(expected, test);

        });
        latch.await();
    }
}