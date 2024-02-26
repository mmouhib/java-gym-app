package com.esprit.gui.controllers;

import com.esprit.gui.models.Plate;
import com.esprit.gui.repository.PlatesRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    void initialize() {
        id.setDisable(true);
        System.out.println("init");
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
}
