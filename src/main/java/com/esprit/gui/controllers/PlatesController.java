package com.esprit.gui.controllers;

import com.esprit.gui.models.Plate;
import com.esprit.gui.models.PlatesModel;
import com.esprit.gui.repository.PlatesRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private ObservableList<PlatesModel> platesModels = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        id_input.setDisable(true);
        platesTableView.setId("id");
        initializeTableView();
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
                    1
            );
            platesRepository.save(plate);
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

    }
}
