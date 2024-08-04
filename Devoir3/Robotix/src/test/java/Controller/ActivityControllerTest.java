package Controller;

import Controller.ActivityController;
import Model.Activity;
import Model.Task;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ActivityControllerTest {
    Stage testStage;
    ActivityController activityController;

    @BeforeEach
    void setUp(){
        Platform.startup(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/ActivityMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                activityController = loader.getController();
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

    // Dawson Test
    @Test
    void successfulCreateActivity() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            ArrayList<String> interests = new ArrayList<>(Arrays.asList("Sports and Fitness","Arts and Crafts"));
            ArrayList<String> instructions = new ArrayList<>(Arrays.asList("Instruction1","Instruction2"));
            ArrayList<Task> taskList = new ArrayList<>(Arrays.asList(new Task("Task1", instructions),new Task("Task2", instructions),new Task("Task2", instructions)));
            int result = activityController.addActivityTest("Activity1", "2021-12-12", "2021-12-13", "10", interests, taskList
                     ,"Description Prompt");
            latch.countDown();
            assertEquals(1, result);
        });
        latch.await();
    }

    @Test
    void successfulButtonModifyTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertEquals("hello",activityController.buttonModifyTest());
            latch.countDown();
        });
        latch.await();
    }

}