package com.esprit.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FoodController {

    @FXML
    void initialize() {
    }


    @FXML
    void logout(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/sign-in.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goToPlates(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/plates.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void goToMeals(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/meal.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void goToTracking(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/food-tracking.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void goToHome(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/home.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
