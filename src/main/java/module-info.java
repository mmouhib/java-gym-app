module com.esprit.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires lombok;
    requires mysql.connector.j;
    requires jbcrypt;

    opens com.esprit.gui to javafx.fxml;
    exports com.esprit.gui;
    exports com.esprit.gui.controllers;
    opens com.esprit.gui.controllers to javafx.fxml;
}