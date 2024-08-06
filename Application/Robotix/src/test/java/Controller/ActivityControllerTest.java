package Controller;

import Model.Activity;
import Model.Task;
import Model.TypeOfUsers.Client;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ActivityControllerTest extends JavaFXBaseTest {

    private ActivityController activityController;

    @Override
    protected void setUpTestStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/ActivityMenu.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 768);
        testStage = new Stage();
        testStage.setScene(scene);
        activityController = loader.getController();
        testStage.show(); // Ensure the stage is shown for proper initialization
    }

    @Test
    void successfulRemoveButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Client clientTest = new Client("hello", "hello", "hello22", "hellohello22",
                    "hello@", "hello22", "111-111-1111",
                    new ArrayList<>(), new ArrayList<>(), false, "2024-11-11");

            ArrayList<String> instruction1 = new ArrayList<>(Arrays.asList("notHello1"));
            ArrayList<String> instruction2 = new ArrayList<>(Arrays.asList("notHello2"));

            Task task1 = new Task("notHello1", instruction1);
            Task task2 = new Task("notHello2", instruction2);

            ArrayList<Task> tasks1 = new ArrayList<>(Arrays.asList(task1));
            ArrayList<Task> tasks2 = new ArrayList<>(Arrays.asList(task2));

            Activity newActivity1 = new Activity("hello1", "hello1", LocalDate.parse("2024-12-12"),
                    LocalDate.parse("2024-12-12"), "100", new ArrayList<>(), UUID.randomUUID(), UUID.randomUUID(),
                    tasks1, "hello", "hello");

            Activity newActivity2 = new Activity("hello2", "hello2", LocalDate.parse("2024-12-12"),
                    LocalDate.parse("2024-12-12"), "100", new ArrayList<>(), UUID.randomUUID(), UUID.randomUUID(),
                    tasks2, "hello", "hello");

            Client newClient = activityController.removeButtonTest(clientTest, newActivity1, newActivity2);

            assertEquals(1, newClient.getMyActivities().size());
            assertFalse(newClient.getMyActivities().stream()
                    .anyMatch(activity -> activity.getName().equals(newActivity2.getName())));

            latch.countDown();
        });
        latch.await();
    }

    @Test
    void successfulCreateActivity() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            ArrayList<String> interests = new ArrayList<>(Arrays.asList("Sports and Fitness", "Arts and Crafts"));
            ArrayList<String> instructions = new ArrayList<>(Arrays.asList("Instruction1", "Instruction2"));
            ArrayList<Task> taskList = new ArrayList<>(Arrays.asList(new Task("Task1", instructions), new Task("Task2", instructions), new Task("Task3", instructions)));

            int result = activityController.addActivityTest("Activity1", "2021-12-12", "2021-12-13", "10", interests, taskList, "Description Prompt");

            assertEquals(1, result);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void successfulButtonModifyTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertEquals("hello", activityController.buttonModifyTest());
            latch.countDown();
        });
        latch.await();
    }
}