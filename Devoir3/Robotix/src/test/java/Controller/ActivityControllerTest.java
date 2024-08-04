package Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityControllerTest {

        @Test
        void testActivityController() {
            ActivityController activityController = new ActivityController();
            assertNotNull(activityController);
        }


}