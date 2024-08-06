module main.Robotix {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    // Export packages
    exports Controller;
    exports main;
    exports Model;
    exports Model.TypeOfUsers;

    // Open packages for reflection
    opens Controller to javafx.fxml;
    opens Model to javafx.fxml;
    opens main to javafx.fxml;
}