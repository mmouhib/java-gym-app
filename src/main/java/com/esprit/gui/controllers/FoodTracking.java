package com.esprit.gui.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FoodTracking implements Initializable {
    public ProgressBar caloriesBar;
    public ProgressBar proteinBar;
    public ProgressBar fatBar;
    public ProgressBar carbsBar;
    public ProgressBar sugarBar;
    public DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setValue(LocalDate.now());
        updateProgress();
    }


    public void updateProgress() {

        LocalDate pickerDate = datePicker.getValue();


        Date date = new Date(
                pickerDate.getYear(),
                pickerDate.getMonthValue(),
                pickerDate.getDayOfMonth()
        );
//        System.out.println(date.getYear());
//        System.out.println(date.getMonth());
//        System.out.println(date.getDate());

        caloriesBar.setProgress(0.5);
        proteinBar.setProgress(0.5);
        fatBar.setProgress(0.5);
        carbsBar.setProgress(0.5);
        sugarBar.setProgress(0.5);

        caloriesBar.setStyle("-fx-accent: red;");
        fatBar.setStyle("-fx-accent: green;");
        sugarBar.setStyle("-fx-accent: orange;");
    }
}
