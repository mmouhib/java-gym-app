package com.esprit.gui.controllers;

import com.esprit.gui.models.User;
import com.esprit.gui.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class EditInfo {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField pwdField;

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
    private String OldPassword;
    private String email;

    @FXML
    void home(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/home.fxml"));
            Scene scene = new Scene(p,1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private TextField nameField;
    @FXML
    private void initialize() throws SQLException {



        setFileds(5); // Call the method to set fields by default
    }
    void setFileds(int id)throws SQLException {


        User u = new User();
        UserRepository ur = new UserRepository();
        u = ur.findById(id);
        System.out.println(u);

        // Save hashed password into variable
        OldPassword = u.getPassword();
        email = u.getEmail();
        firstNameField.setText(u.getFirst_name());
        lastNameField.setText(u.getLast_name());
        phoneField.setText(u.getPhone());
        pwdField.setText("");

        passwordField.setText("");
        ageField.setText(String.valueOf(u.getAge()));
        genderComboBox.setValue(u.getGender());
        weightField.setText(String.valueOf(u.getWeight()));
        heightField.setText(String.valueOf(u.getHeight()));
        caloriesField.setText(String.valueOf(u.getCalories()));
        proteinField.setText(String.valueOf(u.getProtein()));
        carbsField.setText(String.valueOf(u.getCarbs()));
        fatField.setText(String.valueOf(u.getFat()));
        sugarField.setText(String.valueOf(u.getSugar()));
        targetWeightField.setText(String.valueOf(u.getTargetWeight()));
        roleComboBox.setValue(u.getRole());


    }

    @FXML
    private void edit(ActionEvent event) throws SQLException {


        // Perform signup logic here
        User u = new User();
        UserRepository ur = new UserRepository();
        u.setFirst_name(firstNameField.getText());
        u.setLast_name(lastNameField.getText());
        u.setPhone(phoneField.getText());
        u.setEmail(email);
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

        // check current password validity
        String hashedPasswordFromDB = OldPassword;
        boolean validPassword = BCrypt.checkpw(pwdField.getText(), hashedPasswordFromDB);

        if(email.isEmpty() ||
                OldPassword.isEmpty() ||
                passwordField.getText().isEmpty() ||
                u.getFirst_name().isEmpty() ||
                u.getLast_name().isEmpty() ||
                u.getPhone().isEmpty() ||
                String.valueOf(u.getAge()).isEmpty() ||
                u.getGender().isEmpty() ||
                String.valueOf(u.getWeight()).isEmpty() ||
                String.valueOf(u.getHeight()).isEmpty() ||
                String.valueOf(u.getProtein()).isEmpty() ||
                String.valueOf(u.getCarbs()).isEmpty() ||
                String.valueOf(u.getFat()).isEmpty() ||
                String.valueOf(u.getSugar()).isEmpty() ||
                String.valueOf(u.getTargetWeight()).isEmpty() ||
                u.getRole().isEmpty()

        ){
            Stage errorStage = new Stage();

            Label errorMessage = new Label("filed empty!.\ud83d\ude28 ");
            errorMessage.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;"); // Setting style for the error message

            VBox root = new VBox(10);
            root.getChildren().add(errorMessage);
            root.setPadding(new Insets(20));
            root.setStyle("-fx-background-color: #b80b21;"); // Inline CSS styling for the background color

            Scene scene = new Scene(root, 400, 100);
            errorStage.setScene(scene);
            errorStage.setTitle("Error");
            errorStage.show();
            // Center error scene
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            errorStage.setX((primScreenBounds.getWidth() - errorStage.getWidth()) / 2);
            errorStage.setY((primScreenBounds.getHeight() - errorStage.getHeight()) / 2);
        }else {

            if(validPassword) {
                //ur.save(u);
                System.out.println("edited!");

                try {
                    Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/home.fxml"));
                    Scene scene = new Scene(p,1100, 650);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }else {
                Stage errorStage = new Stage();

                Label errorMessage = new Label("Incorrect password! Please try again.\ud83d\ude28 ");
                errorMessage.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;"); // Setting style for the error message

                VBox root = new VBox(10);
                root.getChildren().add(errorMessage);
                root.setPadding(new Insets(20));
                root.setStyle("-fx-background-color: #b80b21;"); // Inline CSS styling for the background color

                Scene scene = new Scene(root, 400, 100);
                errorStage.setScene(scene);
                errorStage.setTitle("Error");
                errorStage.show();
                // Center error scene
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                errorStage.setX((primScreenBounds.getWidth() - errorStage.getWidth()) / 2);
                errorStage.setY((primScreenBounds.getHeight() - errorStage.getHeight()) / 2);
            }

        }
    }


    @FXML
    void clearFields(ActionEvent event) {
        firstNameField.clear();
        lastNameField.clear();
        phoneField.clear();
        pwdField.clear();
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
