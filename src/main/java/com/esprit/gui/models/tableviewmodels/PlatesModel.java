package com.esprit.gui.models.tableviewmodels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class PlatesModel {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty calories;
    private SimpleIntegerProperty protein;
    private SimpleIntegerProperty carbs;
    private SimpleIntegerProperty fat;
    private SimpleIntegerProperty sugar;

    public PlatesModel() {
    }

    public PlatesModel(int id, String name, int calories, int protein, int carbs, int fat, int sugar) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.calories = new SimpleIntegerProperty(calories);
        this.protein = new SimpleIntegerProperty(protein);
        this.carbs = new SimpleIntegerProperty(carbs);
        this.fat = new SimpleIntegerProperty(fat);
        this.sugar = new SimpleIntegerProperty(sugar);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getCalories() {
        return calories.get();
    }

    public SimpleIntegerProperty caloriesProperty() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories.set(calories);
    }

    public int getProtein() {
        return protein.get();
    }

    public SimpleIntegerProperty proteinProperty() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein.set(protein);
    }

    public int getCarbs() {
        return carbs.get();
    }

    public SimpleIntegerProperty carbsProperty() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs.set(carbs);
    }

    public int getFat() {
        return fat.get();
    }

    public SimpleIntegerProperty fatProperty() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat.set(fat);
    }

    public int getSugar() {
        return sugar.get();
    }

    public SimpleIntegerProperty sugarProperty() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar.set(sugar);
    }
}

