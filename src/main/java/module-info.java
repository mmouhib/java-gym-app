module com.esprit.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires lombok;
    requires mysql.connector.j;
    requires junit;

    opens com.esprit.gym_gui to javafx.fxml;
    exports com.esprit.gym_gui;
}