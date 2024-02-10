package com.esprit.gui.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private int id;
    private String name;
    private int age;
    private float weight;
    private float height;
    private float calories;
    private float protein;
    private float carbs;
    private float fat;
    private float sugar;
    private float targetWeight;


    public User(int id, String name, int age, float weight, float height, float calories, float protein, float carbs, float fat, float sugar, float targetWeight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.sugar = sugar;
        this.targetWeight = targetWeight;
    }

    public User(String name, int age, float weight, float height, float calories, float protein, float carbs, float fat, float sugar, float targetWeight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.sugar = sugar;
        this.targetWeight = targetWeight;
    }

    public User() {
    }



}
