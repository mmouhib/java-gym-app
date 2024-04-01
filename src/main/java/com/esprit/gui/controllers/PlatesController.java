package com.esprit.gui.controllers;

import com.esprit.gui.models.Plate;
import com.esprit.gui.models.PlatesModel;
import com.esprit.gui.repository.PlatesRepository;
import com.esprit.gui.utils.AuthSessionUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PlatesController {
    @FXML
    public TextField id_input;
    @FXML
    public TextField name_input;
    @FXML
    public TextField calories_input;
    @FXML
    public TextField protein_input;
    @FXML
    public TextField fat_input;
    @FXML
    public TextField carbs_input;
    @FXML
    public TextField sugar_input;
    @FXML
    public Button saveButton;
    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button resetButton;


    // table view
    @FXML
    public TableView<PlatesModel> platesTableView;
    @FXML
    public TableColumn<PlatesModel, Integer> id;
    @FXML
    public TableColumn<PlatesModel, String> name;
    @FXML
    public TableColumn<PlatesModel, Integer> calories;
    @FXML
    public TableColumn<PlatesModel, Integer> protein;
    @FXML
    public TableColumn<PlatesModel, Integer> carbs;
    @FXML
    public TableColumn<PlatesModel, Integer> fat;
    @FXML
    public TableColumn<PlatesModel, Integer> sugar;
    @FXML
    public TextField search;

    private ObservableList<PlatesModel> platesModels = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        id_input.setDisable(true);
        platesTableView.setId("id");
        initializeTableView();

        search.textProperty().addListener((observable, oldVal, newVal) -> {
            search(newVal);
        });
    }

    private void initializeTableView() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        protein.setCellValueFactory(new PropertyValueFactory<>("protein"));
        carbs.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        fat.setCellValueFactory(new PropertyValueFactory<>("fat"));
        sugar.setCellValueFactory(new PropertyValueFactory<>("sugar"));
        addToTableView();

    }


    @FXML
    public void clearForm() {
        id_input.clear();
        name_input.clear();
        calories_input.clear();
        protein_input.clear();
        fat_input.clear();
        carbs_input.clear();
        sugar_input.clear();
    }

    boolean isFormValid() {
        return !name_input.getText().isEmpty() &&
                !calories_input.getText().isEmpty() &&
                !protein_input.getText().isEmpty() &&
                !fat_input.getText().isEmpty() &&
                !carbs_input.getText().isEmpty() &&
                !sugar_input.getText().isEmpty();
    }

    private void addToTableView() {
        search.setText("");

        platesModels.clear();
        List<Plate> plates = new PlatesRepository().list();

        for (Plate plate : plates) {
            platesModels.add(new PlatesModel(
                    plate.getId(),
                    plate.getName(),
                    plate.getCalories(),
                    plate.getProtein(),
                    plate.getCarbs(),
                    plate.getFat(),
                    plate.getSugar()
            ));
        }

        platesTableView.setItems(platesModels);
    }

    @FXML
    public void onSave() {
        try {
            if (!isFormValid()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please check the form data. ");
                alert.show();
                return;
            }
            PlatesRepository platesRepository = new PlatesRepository();

            Plate plate = new Plate(
                    name_input.getText(),
                    Integer.parseInt(calories_input.getText()),
                    Integer.parseInt(protein_input.getText()),
                    Integer.parseInt(carbs_input.getText()),
                    Integer.parseInt(fat_input.getText()),
                    Integer.parseInt(sugar_input.getText()),
                    Integer.parseInt(AuthSessionUtils.getCurrentUser())
            );

            if (id_input.getText().isEmpty()) {
                platesRepository.save(plate);
            } else {
                plate.setId(Integer.parseInt(id_input.getText()));
                platesRepository.update(plate);
            }

            addToTableView();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Problem adding data");
            alert.setHeaderText(null);
            alert.setContentText("There was a problem! please try again");
            alert.showAndWait();
        }
    }

    @FXML
    void deletePlate() {
        PlatesModel selectedItem = platesTableView.getSelectionModel().getSelectedItem();
        PlatesRepository platesRepository = new PlatesRepository();
        platesRepository.delete(selectedItem.getId());
        addToTableView();
    }

    public void editPlate(ActionEvent actionEvent) {
        PlatesRepository platesRepository = new PlatesRepository();
        PlatesModel selectedItem = platesTableView.getSelectionModel().getSelectedItem();
        Plate plate = platesRepository.findById(selectedItem.getId());

        id_input.setText(String.valueOf(plate.getId()));
        name_input.setText(plate.getName());
        calories_input.setText(String.valueOf(plate.getCalories()));
        protein_input.setText(String.valueOf(plate.getProtein()));
        carbs_input.setText(String.valueOf(plate.getCarbs()));
        fat_input.setText(String.valueOf(plate.getFat()));
        sugar_input.setText(String.valueOf(plate.getSugar()));
    }

    public void search(String searchTerm) {
        platesModels.clear();
        List<Plate> plates = new PlatesRepository().list();

        for (Plate plate : plates) {
            if (String.valueOf(plate.getId()).contains(searchTerm) ||
                    plate.getName().contains(searchTerm) ||
                    String.valueOf(plate.getCalories()).contains(searchTerm) ||
                    String.valueOf(plate.getProtein()).contains(searchTerm) ||
                    String.valueOf(plate.getCarbs()).contains(searchTerm) ||
                    String.valueOf(plate.getFat()).contains(searchTerm) ||
                    String.valueOf(plate.getSugar()).contains(searchTerm)) {
                platesModels.add(new PlatesModel(
                        plate.getId(),
                        plate.getName(),
                        plate.getCalories(),
                        plate.getProtein(),
                        plate.getCarbs(),
                        plate.getFat(),
                        plate.getSugar()
                ));
            }
        }

        platesTableView.setItems(platesModels);
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
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/food.fxml"));
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
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition.fxml"));
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
