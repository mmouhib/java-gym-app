package com.esprit.gui.controllers;

import com.esprit.gui.models.Plate;
import com.esprit.gui.repository.PlatesRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class PlatesController {
    public TextField id;
    public TextField name;
    public TextField calories;
    public TextField protein;
    public TextField fat;
    public TextField carbs;
    public TextField sugar;
    public TableView platesTableView;
    public Button saveButton;
    public Button editButton;
    public Button deleteButton;
    public Button resetButton;

    @FXML
    void initialize() {
        id.setDisable(true);

        List<Plate> plates = new PlatesRepository().list();
        System.out.println(plates);

        //platesTableView.getItems().addAll(plates);
        //platesTableView.setEditable(true);
        platesTableView.setId("id");

        TableColumn<Plate, String> idColumn = new TableColumn<>("id");
        TableColumn<Plate, String> nameColumn = new TableColumn<>("name");
        TableColumn<Plate, String> caloriesColumn = new TableColumn<>("Calories");
        TableColumn<Plate, String> proteinColumn = new TableColumn<>("protein");
        TableColumn<Plate, String> carbsColumn = new TableColumn<>("Carbs");
        TableColumn<Plate, String> fatColumn = new TableColumn<>("Fat");
        TableColumn<Plate, String> sugarColumn = new TableColumn<>("Sugar");



    }

    // TODO: Add the following methods:
    // - savePlate
    // - editPlate
    // - deletePlate
    // - resetFields
    // - fillTable
    // - fillForm
    // - clearForm
    // - validateForm
    // - validateDate

    @FXML
    public void onSave() {
        if (!isFormValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please check the form data. ");
            alert.show();
            return;
        }
        PlatesRepository platesRepository = new PlatesRepository();
        Plate plate = new Plate(
                name.getText(),
                Integer.parseInt(calories.getText()),
                Integer.parseInt(protein.getText()),
                Integer.parseInt(carbs.getText()),
                Integer.parseInt(fat.getText()),
                Integer.parseInt(sugar.getText()),
                1
        );
        platesRepository.save(plate);
    }



    @FXML
    public void clearForm() {
        id.clear();
        name.clear();
        calories.clear();
        protein.clear();
        fat.clear();
        carbs.clear();
        sugar.clear();
    }

    boolean isFormValid() {
        return !name.getText().isEmpty() &&
                !calories.getText().isEmpty() &&
                !protein.getText().isEmpty() &&
                !fat.getText().isEmpty() &&
                !carbs.getText().isEmpty() &&
                !sugar.getText().isEmpty();
    }

}
