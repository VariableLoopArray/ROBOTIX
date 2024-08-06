module test {
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires javafx.controls;
    requires javafx.fxml;

    // Allow access to main module packages for testing
    opens Controller to org.junit.jupiter.api, javafx.fxml;
    opens Model to org.junit.jupiter.api, javafx.fxml;
    opens main to org.junit.jupiter.api;
}