package Controller;

import Model.Activity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ActivityControllerTest {

        @Test
        void testActivityController() {
//            Activity newActivity = new Activity(activityName.getText(), null, startDate, endDate,
//                    activityPoints.getText(), interests, client.getId(), UUID.randomUUID(), taskList, activityDescription.getText(),status);
            List<String> interests = new ArrayList<>(Arrays.asList("Sports and Fitness","Arts and Crafts"));
            addActivityTest("Activity1", "2021-12-12", "2021-12-13", "10", interests,
                    new ArrayList<>() ,"Description Prompt" , "Upcoming");
        }


}