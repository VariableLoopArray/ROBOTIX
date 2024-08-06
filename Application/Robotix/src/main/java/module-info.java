module main.Robotix {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;

    opens Model;
    opens Controller to javafx.fxml, junit.jupiter.api;
    opens main to javafx.fxml;
    opens Model.TypeOfUsers;


    exports Controller;
    exports main;
}