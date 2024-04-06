package com.esprit.gui.controllers;

import com.esprit.gui.models.Meal;
import com.esprit.gui.repository.MealRepository;
import com.esprit.gui.repository.PlatesRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;
import java.util.ResourceBundle;

public class MealsController implements Initializable {


    public Button home;
    public ComboBox<String> type;
    public CheckBox isToday;
    public DatePicker dateInput;
    public Text quantityValue;
    public Slider quantity;
    public Button resetButton;
    public Button saveButton;
    public TextField name_input;
    public Button products;
    public Button logout;
    public Button profile;
    public Button plates;
    public Button tracking;
    public Button nutrition;
    public ComboBox<String> category;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantity.valueProperty().addListener((observable, oldValue, newValue) -> {
            quantityValue.setText(Integer.toString(newValue.intValue()));
        });
        category.getItems().add("breakfast");
        category.getItems().add("lunch");
        category.getItems().add("dinner");
        category.getItems().add("snack");
        updatePlatesDropdown();
    }


    private void updatePlatesDropdown() {
        PlatesRepository platesRepository = new PlatesRepository();
        platesRepository.list().forEach(plate -> {
            type.getItems().add(plate.getId() + " - " + plate.getName());
        });
    }

    public void updateDateInput(DragEvent actionEvent) {
        dateInput.setDisable(isToday.isSelected());
        dateInput.setValue(LocalDate.parse(new Date(Calendar.getInstance().getTimeInMillis()).toString()));
    }

    public void updateQuantityValue() {
    }

    public void onSave(ActionEvent actionEvent) {
        MealRepository mealRepository = new MealRepository();
        mealRepository.save(
                new Meal(
                        name_input.getText(),
                        Date.valueOf(dateInput.getValue()),
                        Integer.parseInt(quantityValue.getText()),
                        // Integer.parseInt(AuthSessionUtils.getCurrentUser()),
                        category.getValue(),
                        4,
                        Integer.parseInt(type.getValue().split(" - ")[0])
                )
        );

    }

    public void clearForm(ActionEvent actionEvent) {
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

    public void goToNutrition(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/food.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToTracking(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/nutrition.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToMeals(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/meals.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToProfile(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/edit-info.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logOut(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/sign-in.fxml"));
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
