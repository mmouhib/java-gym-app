package com.esprit.gui.controllers;

import com.esprit.gui.models.User;
import com.esprit.gui.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField ageField;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TextField weightField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField caloriesField;

    @FXML
    private TextField proteinField;

    @FXML
    private TextField carbsField;

    @FXML
    private TextField fatField;

    @FXML
    private TextField sugarField;

    @FXML
    private TextField targetWeightField;

    @FXML
    private ComboBox<String>  roleComboBox;



    @FXML
    private Button clear;


    @FXML
    private Button signup;


    @FXML
    private void signUp(ActionEvent event) throws SQLException {
        // Perform signup logic here
        User u = new User();
        UserRepository ur = new UserRepository();
        u.setFirst_name(firstNameField.getText());
        u.setLast_name(lastNameField.getText());
        u.setPhone(phoneField.getText());
        u.setEmail(emailField.getText());
        u.setPassword(passwordField.getText());
        u.setAge(Integer.parseInt(ageField.getText()));
        u.setGender(genderComboBox.getValue());
        u.setWeight(Float.parseFloat(weightField.getText()));
        u.setHeight(Float.parseFloat(heightField.getText()));
        u.setCalories(Float.parseFloat(caloriesField.getText()));
        u.setProtein(Float.parseFloat(proteinField.getText()));
        u.setCarbs(Float.parseFloat(carbsField.getText()));
        u.setFat(Float.parseFloat(fatField.getText()));
        u.setSugar(Float.parseFloat(sugarField.getText()));
        u.setTargetWeight(Float.parseFloat(targetWeightField.getText()));
        u.setRole(roleComboBox.getValue());

        // test output
        //System.out.println(u);
        ur.save(u);

    }
    @FXML
    private void signIn(ActionEvent event) throws SQLException {
        // Perform signup logic here
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/sign-in.fxml"));
            Scene scene = new Scene(p,1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void clearFields(ActionEvent event) {
        firstNameField.clear();
        lastNameField.clear();
        phoneField.clear();
        emailField.clear();
        passwordField.clear();
        ageField.clear();
        genderComboBox.getSelectionModel().clearSelection();
        weightField.clear();
        heightField.clear();
        caloriesField.clear();
        proteinField.clear();
        carbsField.clear();
        fatField.clear();
        sugarField.clear();
        targetWeightField.clear();
        roleComboBox.getSelectionModel().clearSelection();
    }
}
