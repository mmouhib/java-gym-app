package com.esprit.gui.controllers;

import com.esprit.gui.models.Meal;
import com.esprit.gui.models.Plate;
import com.esprit.gui.models.tableviewmodels.MealsModel;
import com.esprit.gui.models.tableviewmodels.PlatesModel;
import com.esprit.gui.repository.MealRepository;
import com.esprit.gui.repository.PlatesRepository;
import com.esprit.gui.utils.AuthSessionUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MealsController implements Initializable {


    public Button home;
    public ComboBox<String> type_input;
    public CheckBox isToday;
    public DatePicker dateInput;
    public Text quantityValue;
    public Slider quantity_input;
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


    public TableView<MealsModel> mealsTableView;
    public TableColumn<MealsModel, String> name;
    public TableColumn<MealsModel, Integer> quantity;
    public TableColumn<MealsModel, String> date;
    public TableColumn<MealsModel, String> plate;
    public TableColumn<MealsModel, String> type;
    public TableColumn<MealsModel, Integer> id;
    public Button deleteButton;
    private ObservableList<MealsModel> mealsModels = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantity_input.valueProperty().addListener((observable, oldValue, newValue) -> {
            quantityValue.setText(Integer.toString(newValue.intValue()));
        });
        category.getItems().add("breakfast");
        category.getItems().add("lunch");
        category.getItems().add("dinner");
        category.getItems().add("snack");
        updatePlatesDropdown();
        initializeTableView();
    }

    private void initializeTableView() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        plate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        addToTableView();

    }

    private void addToTableView() {
        mealsModels.clear();
        List<Meal> meals = new MealRepository().getMealsByUser(Integer.parseInt(AuthSessionUtils.getCurrentUser()));
        for (Meal meal : meals) {
            String plateName = new PlatesRepository().findById(meal.getPlateId()).getName();
            mealsModels.add(new MealsModel(
                    meal.getId(),
                    meal.getName(),
                    meal.getQuantity(),
                    meal.getDate().toString(),
                    plateName,
                    meal.getCategory()
            ));
        }
        mealsTableView.setItems(mealsModels);
    }


    private void updatePlatesDropdown() {
        PlatesRepository platesRepository = new PlatesRepository();
        platesRepository.list().forEach(plate -> {
            type_input.getItems().add(plate.getId() + " - " + plate.getName());
        });
    }

    public void updateDateInput() {
        dateInput.setDisable(isToday.isSelected());
        dateInput.setValue(LocalDate.parse(new Date(Calendar.getInstance().getTimeInMillis()).toString()));
    }

    public void updateQuantityValue() {
    }

    private Date localDateToDate() {
        String inputDate = dateInput.getValue().toString();
        Date result = new Date(
                Integer.parseInt(inputDate.split("-")[0]) - 1900,
                Integer.parseInt(inputDate.split("-")[1]) - 1,
                Integer.parseInt(inputDate.split("-")[2])
        );
        return result;
    }

    public void onSave(ActionEvent actionEvent) {
        mealsModels.clear();
        MealRepository mealRepository = new MealRepository();
        mealRepository.save(
                new Meal(
                        name_input.getText(),
                        localDateToDate(),
                        Integer.parseInt(quantityValue.getText()),
                        category.getValue(),
                        Integer.parseInt(AuthSessionUtils.getCurrentUser()),
                        Integer.parseInt(type_input.getValue().split(" - ")[0])
                )
        );
        addToTableView();

    }


    public void onDelete(ActionEvent actionEvent) {
        mealsTableView.getSelectionModel().getSelectedItems().forEach(mealsModel -> {
            new MealRepository().delete(mealsModel.getId());
        });
        addToTableView();
    }

    public void clearForm(ActionEvent actionEvent) {
        name_input.setText("");
        quantity_input.setValue(0);
        type_input.setValue("");
        category.setValue("");
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
