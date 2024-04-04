package com.esprit.gui.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor

public class Plate {
    private int id;
    private String name;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private int sugar;
    private int userId;

    public Plate(String name, int calories, int protein, int carbs, int fat, int sugar, int userId) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.sugar = sugar;
        this.userId = userId;
    }

    public Plate(String name, int calories, int protein, int carbs, int fat, int sugar) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.sugar = sugar;
    }

}
