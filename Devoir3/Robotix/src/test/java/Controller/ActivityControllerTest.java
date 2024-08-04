package Controller;

import Controller.ActivityController;
import Model.Activity;
import Model.Robot;
import Model.Task;
import Model.TypeOfUsers.Client;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

    @Test
    void successfulRemoveButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {

            Client clientTest = new Client("hello", "hello", "hello22", "hellohello22",
                    "hello@","hello22","111-111-1111",
                    new ArrayList<Robot>(), new ArrayList<String>());



            ArrayList<String> instruction1 = new ArrayList<>();
            instruction1.add("notHello1");
            ArrayList<String> instruction2 = new ArrayList<>();
            instruction1.add("notHello2");

            Task task1 = new Task("notHello1", instruction1);
            ArrayList<Task> tasks = new ArrayList<Task>();
            Task task2 = new Task("notHello2", instruction2);
            ArrayList<Task> tasks2 = new ArrayList<Task>();

            tasks.add(task1);
            tasks2.add(task2);


            Activity newActivity1 = new Activity("hello1", "hello1", LocalDate.parse("2024-12-12"),
                    LocalDate.parse("2024-12-12"), "100", new ArrayList<String>(), UUID.randomUUID(), UUID.randomUUID(),
                    tasks, "hello", "hello");

            Activity newActivity2 = new Activity("hello2", "hello2", LocalDate.parse("2024-12-12"),
                    LocalDate.parse("2024-12-12"), "100", new ArrayList<String>(), UUID.randomUUID(), UUID.randomUUID(),
                    tasks2, "hello", "hello");



            Client newClient = activityController.removeButtonTest(clientTest, newActivity1, newActivity2);

            assertEquals(1, newClient.getMyActivities().size());

            boolean result = true;
            for (Activity activity : newClient.getMyActivities()){
                if (activity.getName().equals(newActivity2.getName())){
                    result = false;
                }
            }
            assertTrue(result);



            latch.countDown();
        });
        latch.await();
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