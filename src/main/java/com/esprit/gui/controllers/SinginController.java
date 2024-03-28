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

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.System.exit;

public class SinginController {
    @FXML
    private Button closebtn;

    @FXML
    private Hyperlink forgetpassord;

    @FXML
    private Button login;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField password;
    @FXML
    private Button signup;

    private final UserRepository ps = new UserRepository();

    @FXML
    void closeApp(ActionEvent event) {
        System.out.println("bye!");
        exit(0);

    }

    @FXML
    void close(ActionEvent event) {
        System.out.println("close");

    }

    @FXML
    void restorePassword(ActionEvent event) {

    }

    @FXML
    void sigin(ActionEvent event) throws SQLException {
        User u = new User();
        boolean success;
        u.setEmail(mail.getText());
        u.setPassword(password.getText());
        success= ps.signIn(u);
        if(success){
            // interface home
            System.out.println("ture");
            try {
                Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/home.fxml"));
                Scene scene = new Scene(p,1100, 650);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.centerOnScreen();
                appStage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            // error msg
/*
            Stage errorStage = new Stage();
            Label errorMessage = new Label("Incorrect email or password! Please try again.");
            VBox root = new VBox(10);
            root.getChildren().add(errorMessage);
            root.setPadding(new Insets(20));

            Scene scene = new Scene(root, 350, 200);

            errorStage.setScene(scene);
            errorStage.setTitle("Error");
            errorStage.show();


            */
            Stage errorStage = new Stage();

            Label errorMessage = new Label("Incorrect email or password! Please try again.\ud83d\ude28 ");
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
    @FXML
    void signup(ActionEvent event) {
        System.out.println("signup");

        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/signup.fxml"));
            Scene scene = new Scene(p,1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.centerOnScreen();
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
