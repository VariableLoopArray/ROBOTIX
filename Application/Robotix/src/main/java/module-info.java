module main.Robotix {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens Model;
    opens Controller to javafx.fxml, org.junit.jupiter.api;
    opens main to javafx.fxml;
    opens Model.TypeOfUsers;

    exports Controller;
    exports main;
}