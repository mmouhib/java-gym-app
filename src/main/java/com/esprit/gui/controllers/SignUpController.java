package com.esprit.gui.controllers;

import com.esprit.gui.models.User;
import com.esprit.gui.repository.UserRepository;
import com.esprit.gui.utils.api.EmailValidator;
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

import java.io.FileWriter;
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
    private void signUp(ActionEvent event) throws SQLException, IOException {
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

        //test Email Verification API
        EmailValidator ev = new EmailValidator();
        boolean validEmail = ev.verify(emailField.getText());

        if(validEmail) {
            ur.save(u);
            String email = u.getEmail();
            u = ur.findByEmail(email);
            saveData(String.valueOf(u.getId()), "set-id.txt");
            try {
                Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/home.fxml"));
                Scene scene = new Scene(p, 1100, 650);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();

            } catch (IOException exp) {
                throw new RuntimeException(exp);
            }
        }else {
            Stage errorStage = new Stage();

            Label errorMessage = new Label("Invalid email\ud83d\ude28 ");
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
    void saveData(String data, String path) throws IOException{
        //Files.writeString(fileName, data);
        FileWriter writer = new FileWriter(path);
        writer.write(data);
        writer.close();
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
