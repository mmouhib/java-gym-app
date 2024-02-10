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
    private int weight;
    private int height;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private int sugar;
    private int targetWeight;


    public User(int id, String name, int age, int weight, int height, int calories, int protein, int carbs, int fat, int sugar, int targetWeight) {
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

    public User(String name, int age, int weight, int height, int calories, int protein, int carbs, int fat, int sugar, int targetWeight) {
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
