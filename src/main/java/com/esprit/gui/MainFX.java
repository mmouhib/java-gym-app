package com.esprit.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("/com/esprit/gui/food.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("/com/esprit/gui/home2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1100, 650);
        stage.setTitle("Sign in");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
