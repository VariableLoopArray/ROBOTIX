package Controller;

import Model.TypeOfUsers.Client;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private LoginController loginController;


    @Test
    void testIsClientValid() {
        loginController = new LoginController();
        // Test valid client
        Client validClient = loginController.isClientValid("1@", "11111111");
        assertEquals("1@", validClient.getEmail());
        Client validClient2 = loginController.isClientValid("1@", "1234");
        assertNull(validClient2, "Expected null ");
        Client validClient3 = loginController.isClientValid("1@", "11111111");
        assertNotEquals("lol@", validClient3.getEmail());
    }
}