package Controller;

import Model.TypeOfUsers.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ProfileControllerTest {
    Stage testStage;
    ProfileController profileController = new ProfileController();

    @BeforeEach
    void setUp(){

        Platform.startup(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlPages/ProfileMenu.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                testStage = new Stage();
                testStage.setScene(scene);
                profileController = loader.getController();
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
    void successfulDisplayProfileTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Client client = null;
            try(Reader reader = new FileReader("src/main/JsonFiles/client.json")) {
                Gson gson = new GsonBuilder().create();
                List<Client> clients = gson.fromJson(reader, new TypeToken<ArrayList<Client>>(){}.getType());
                client = clients.get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList<Object> userJsoninfo = new ArrayList<>();
            userJsoninfo.add(client.getFirstName()+" "+client.getLastName());
            userJsoninfo.add(client.getUsername());
            userJsoninfo.add(client.getEmail());
            userJsoninfo.add(client.getPhoneNumber());
            userJsoninfo.add(client.getCompanyName());
            ArrayList<String> userActivity = new ArrayList<>();
            for (int i = 0; i < client.getMyActivities().size(); i++) {
                userActivity.add(" - " + client.getMyActivities().get(i).getName() + "\n");
            }
            ArrayList<String> userInterests = new ArrayList<>();
            for (int i = 0; i < client.getMyInterests().size(); i++) {
                userInterests.add(" - " + client.getMyInterests().get(i) + "\n");
            }
            ArrayList<String> userFleet = new ArrayList<>();
            for (int i = 0; i < client.getFleet().size(); i++) {
                userFleet.add(" - " + client.getFleet().get(i).getName() + "\n");
            }
            userJsoninfo.add(userActivity);
            userJsoninfo.add(userInterests);
            userJsoninfo.add(userFleet);

            ArrayList<Object> result = profileController.displayProfileTest();

            latch.countDown();
            assertEquals(userJsoninfo, result);

        });
        latch.await();
    }
}