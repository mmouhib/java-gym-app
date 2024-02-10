package com.esprit.gui.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class Meal {

    private int id;
    private String name;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private int sugar;
    private int userId;
    private java.util.Date date;

    public Meal() {
    }

    public Meal(int id, String name, int calories, int protein, int carbs, int fat, int sugar, int userId, Date date) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.sugar = sugar;
        this.userId = userId;
        this.date = date;
    }

    public Meal(String name, int calories, int protein, int carbs, int fat, int sugar, int userId, Date date) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.sugar = sugar;
        this.userId = userId;
        this.date = date;
    }

}
