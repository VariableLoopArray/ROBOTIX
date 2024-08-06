package Controller;

import Model.Activity;
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
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityControllerTest {

    private ActivityController activityController;
    private Stage testStage;
    private CountDownLatch latch;

    @BeforeEach
    void setUp() throws Exception {
        latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                setUpTestStage();
            } catch (Exception e) {
                e.printStackTrace();
                fail("Setup failed: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        CountDownLatch teardownLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            if (testStage != null) {
                testStage.close();
                testStage = null; // Release the reference
            }
            teardownLatch.countDown();
        });
        teardownLatch.await();
    }

    void setUpTestStage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/ActivityMenu.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 768);
        testStage = new Stage();
        testStage.setScene(scene);
        activityController = loader.getController();
        testStage.show(); // Ensure the stage is shown for proper initialization
    }

    private Client createTestClient() {
        return new Client("hello", "hello", "hello22", "hellohello22",
                "hello@", "hello22", "111-111-1111",
                new ArrayList<>(), new ArrayList<>(), false, "2024-11-11");
    }

    private Activity createTestActivity(String name, ArrayList<Task> tasks) {
        return new Activity(name, name, LocalDate.parse("2024-12-12"),
                LocalDate.parse("2024-12-12"), "100", new ArrayList<>(), UUID.randomUUID(), UUID.randomUUID(),
                tasks, "hello", "hello");
    }

    @Test
    void successfulRemoveButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                Client clientTest = createTestClient();

                Task task1 = new Task("notHello1", new ArrayList<>(Arrays.asList("notHello1")));
                Task task2 = new Task("notHello2", new ArrayList<>(Arrays.asList("notHello2")));

                Activity newActivity1 = createTestActivity("hello1", new ArrayList<>(Arrays.asList(task1)));
                Activity newActivity2 = createTestActivity("hello2", new ArrayList<>(Arrays.asList(task2)));

                Client updatedClient = activityController.removeButtonTest(clientTest, newActivity1, newActivity2);

                assertEquals(1, updatedClient.getMyActivities().size());
                assertFalse(updatedClient.getMyActivities().stream()
                        .anyMatch(activity -> activity.getName().equals(newActivity2.getName())));
            } catch (Exception e) {
                e.printStackTrace();
                fail("Test failed: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    @Test
    void successfulCreateActivity() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                ArrayList<String> interests = new ArrayList<>(Arrays.asList("Sports and Fitness", "Arts and Crafts"));
                ArrayList<String> instructions = new ArrayList<>(Arrays.asList("Instruction1", "Instruction2"));
                ArrayList<Task> taskList = new ArrayList<>(Arrays.asList(
                        new Task("Task1", instructions),
                        new Task("Task2", instructions),
                        new Task("Task3", instructions)));

                int result = activityController.addActivityTest("Activity1", "2021-12-12", "2021-12-13", "10", interests, taskList, "Description Prompt");

                assertEquals(1, result);
            } catch (Exception e) {
                e.printStackTrace();
                fail("Test failed: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }

    @Test
    void successfulButtonModifyTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                assertEquals("hello", activityController.buttonModifyTest());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Test failed: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }
}