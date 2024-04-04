package com.esprit.gui.controllers;

import com.esprit.gui.utils.api.FoodMacros;
import com.esprit.gui.utils.api.PlateResponse;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    public Button findButton;
    public ImageView productImage;
    public Text calories;
    public Text protein;
    public Text carbs;
    public Text fat;
    public Text sugar;
    public Text sodium;
    public Text salt;
    public Text name;
    public AnchorPane dataAnchor;
    public TextField searchInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataAnchor.setVisible(false);

    }

    public void searchForProduct(ActionEvent actionEvent) {
        FoodMacros foodMacros = new FoodMacros(searchInput.getText());
        try {
            PlateResponse plateResponse = foodMacros.parseResponse();
            this.calories.setText(String.valueOf(plateResponse.getCalories()));
            this.protein.setText(String.valueOf(plateResponse.getProtein()));
            this.carbs.setText(String.valueOf(plateResponse.getCarbs()));
            this.fat.setText(String.valueOf(plateResponse.getFat()));
            this.sugar.setText(String.valueOf(plateResponse.getSugar()));
            this.sodium.setText(String.valueOf(plateResponse.getSodium()));
            this.salt.setText(String.valueOf(plateResponse.getSalt()));
            this.name.setText(plateResponse.getName());
            this.productImage.setImage(new Image(plateResponse.getImageUrl()));
            this.dataAnchor.setVisible(true);
        } catch (Exception ignored) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid barcode. ");
            alert.show();
            this.resetData();


        }
    }

    public void resetData() {
        this.calories.setText("x");
        this.protein.setText("x");
        this.carbs.setText("x");
        this.fat.setText("x");
        this.sugar.setText("x");
        this.sodium.setText("x");
        this.salt.setText("x");
        this.name.setText("x");
        this.dataAnchor.setVisible(false);
    }


}
