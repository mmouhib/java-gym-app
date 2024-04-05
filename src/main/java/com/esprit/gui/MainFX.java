package com.esprit.gui;

import com.esprit.gui.utils.AuthSessionUtils;
import com.esprit.gui.utils.api.FoodMacros;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FoodMacros foodMacros = new FoodMacros("6191427600769");
        try {
            System.out.println(foodMacros.parseResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }


        FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("/com/esprit/gui/signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 650);
        stage.setTitle("Sign in");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void stop() {
        AuthSessionUtils.LogOut();
    }

    public static void main(String[] args) {
        launch();
    }


}
