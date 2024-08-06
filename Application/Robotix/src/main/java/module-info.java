module main.Robotix {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    requires java.base;
    //requires org.junit.jupiter.api;

    opens Model;
    opens Controller to javafx.fxml;
    opens main to javafx.fxml;
    opens Model.TypeOfUsers;

    exports Controller;
    exports main;
}