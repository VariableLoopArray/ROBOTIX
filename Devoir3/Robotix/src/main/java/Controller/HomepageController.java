package Controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;



public class HomepageController {
    @FXML
    private Label messageLabel1;
    @FXML
    private Label messageLabel2;

    public HomepageController() {
    }
    public void displayMessage(String message, boolean isError) {
        if (!isError) {
            messageLabel1.setText(message);
        } else {
            messageLabel2.setText(message);
        }
    }

}
