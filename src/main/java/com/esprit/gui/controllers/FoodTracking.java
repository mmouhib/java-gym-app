package com.esprit.gui.controllers;

import com.esprit.gui.models.Meal;
import com.esprit.gui.models.Plate;
import com.esprit.gui.models.User;
import com.esprit.gui.models.controllerutis.Macros;
import com.esprit.gui.repository.MealRepository;
import com.esprit.gui.repository.PlatesRepository;
import com.esprit.gui.repository.UserRepository;
import com.esprit.gui.utils.AuthSessionUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class FoodTracking implements Initializable {
    public ProgressBar caloriesBar;
    public ProgressBar proteinBar;
    public ProgressBar fatBar;
    public ProgressBar carbsBar;
    public ProgressBar sugarBar;
    public DatePicker datePicker;
    public Button searchButton;
    public ComboBox<String> timeComboBox;
    public Text caloriesValue;
    public Text fatValue;
    public Text proteinValue;
    public Text carbsValue;
    public Text sugarValue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setValue(LocalDate.now());
        updateProgress();
        timeComboBox.getItems().add("all");
        timeComboBox.getItems().add("breakfast");
        timeComboBox.getItems().add("lunch");
        timeComboBox.getItems().add("dinner");
        timeComboBox.getItems().add("snack");
    }

    private Date localDateToDate() {
        String inputDate = datePicker.getValue().toString();
        return new Date(
                Integer.parseInt(inputDate.split("-")[0]) - 1900,
                Integer.parseInt(inputDate.split("-")[1]) - 1,
                Integer.parseInt(inputDate.split("-")[2])
        );
    }

    private Macros countMacros() {
        Date date = localDateToDate();
        List<Meal> meals = new ArrayList<>();
        Macros macros = new Macros();
        meals = new MealRepository().getMealsByDateAndUser(date, 4);
        int calories = 0, protein = 0, fat = 0, carbs = 0, sugar = 0;

        for (Meal meal : meals) {
            if (timeComboBox.getValue().equals("all")) {
                Plate plate = new PlatesRepository().findById(meal.getPlateId());
                calories += plate.getCalories() * meal.getQuantity();
                fat += plate.getFat() * meal.getQuantity();
                protein += plate.getProtein() * meal.getQuantity();
                carbs += plate.getCarbs() * meal.getQuantity();
                sugar += plate.getSugar() * meal.getQuantity();
            } else {
                if (meal.getCategory().equals(timeComboBox.getValue())) {
                    Plate plate = new PlatesRepository().findById(meal.getPlateId());
                    calories += plate.getCalories() * meal.getQuantity();
                    fat += plate.getFat() * meal.getQuantity();
                    protein += plate.getProtein() * meal.getQuantity();
                    carbs += plate.getCarbs() * meal.getQuantity();
                    sugar += plate.getSugar() * meal.getQuantity();
                }
            }
        }
        macros = new Macros(calories, protein, fat, carbs, sugar);
        return macros;
    }

    public void updateProgress() {
        caloriesBar.setStyle("-fx-accent: red;");
        fatBar.setStyle("-fx-accent: #2c2ca5;");
        sugarBar.setStyle("-fx-accent: orange;");
    }

    public void search(ActionEvent actionEvent) {
        Macros macros = countMacros();
        User user = new User();

        try {
            user = new UserRepository().findById(Integer.parseInt(AuthSessionUtils.getCurrentUser()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Double calories = (double) (macros.getCalories() / user.getCalories());
        Double protein = (double) (macros.getProtein() / user.getProtein());
        Double fat = (double) (macros.getFat() / user.getFat());
        Double carbs = (double) (macros.getCarbs() / user.getCarbs());
        Double sugar = (double) (macros.getSugar() / user.getSugar());

        if (calories > 1) {
            caloriesBar.setStyle("-fx-accent: red;");
        } else if (calories > 0.75) {
            caloriesBar.setStyle("-fx-accent: orange;");
        } else {
            caloriesBar.setStyle("-fx-accent: green;");
        }

        if (protein > 1) {
            proteinBar.setStyle("-fx-accent: red;");
        } else if (protein > 0.75) {
            proteinBar.setStyle("-fx-accent: orange;");
        } else {
            proteinBar.setStyle("-fx-accent: green;");
        }

        if (fat > 1) {
            fatBar.setStyle("-fx-accent: red;");
        } else if (fat > 0.75) {
            fatBar.setStyle("-fx-accent: orange;");
        } else {
            fatBar.setStyle("-fx-accent: green;");
        }

        if (carbs > 1) {
            carbsBar.setStyle("-fx-accent: red;");
        } else if (carbs > 0.75) {
            carbsBar.setStyle("-fx-accent: orange;");
        } else {
            carbsBar.setStyle("-fx-accent: green;");
        }

        if (sugar > 1) {
            sugarBar.setStyle("-fx-accent: red;");
        } else if (sugar > 0.75) {
            sugarBar.setStyle("-fx-accent: orange;");
        } else {
            sugarBar.setStyle("-fx-accent: green;");
        }


        caloriesBar.setProgress(calories);
        proteinBar.setProgress(protein);
        fatBar.setProgress(fat);
        carbsBar.setProgress(carbs);
        sugarBar.setProgress(sugar);

        caloriesValue.setText("Calories: " + macros.getCalories() + " / " + (int) user.getCalories());
        fatValue.setText("Fat: " + macros.getFat() + " / " + (int) user.getFat());
        proteinValue.setText("Protein: " + macros.getProtein() + " / " + (int) user.getProtein());
        carbsValue.setText("Carbs: " + macros.getCarbs() + " / " + (int) user.getCarbs());
        sugarValue.setText("Sugar: " + macros.getSugar() + " / " + (int) user.getSugar());
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
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/food-tracking.fxml"));
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
            appStage.show();
            AuthSessionUtils.LogOut();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToProducts(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/product.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            AuthSessionUtils.LogOut();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
