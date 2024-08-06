module main.Robotix {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    // Export packages for use by other modules
    exports Controller;
    exports main;
    exports Model;
    exports Model.TypeOfUsers;

    // Open packages for reflection during tests
    opens Controller to javafx.fxml, org.junit.jupiter.api;
    opens Model to javafx.fxml, org.junit.jupiter.api;
    opens main to javafx.fxml;
}