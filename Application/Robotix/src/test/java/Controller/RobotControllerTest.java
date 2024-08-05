package Controller;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

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
                latch.countDown();
                assertEquals("Robot Name: "+ testRobot.getName()+"\n" +
                        "Robot Type: "+ testRobot.getType()+"\n" +
                        "Robot Battery: "+testRobot.getBattery()+"%\n" +
                        "Robot Speed: "+testRobot.getSpeed()+"m/s\n" +
                        "Robot Memory: "+testRobot.getMemory()+"GB\n" +
                        "Robot Components: "+testRobot.getComponents()+"\n" +
                        "Robot Position: "+testRobot.getLocation() +"\n" +
                        "SerialNumber: "+ testRobot.getSerialNumber(), test);

            });
            latch.await();
        }
}