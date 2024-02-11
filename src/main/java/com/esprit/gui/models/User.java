package com.esprit.gui.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private int age;
    private String gender;
    private float weight;
    private float height;
    private float calories;
    private float protein;
    private float carbs;
    private float fat;
    private float sugar;
    private float targetWeight;
    private String role;

    public User(int id, String first_name, String last_name, String phone, String email, int age, String gender, float weight, float height, float calories, float protein, float carbs, float fat, float sugar, float targetWeight, String role) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.sugar = sugar;
        this.targetWeight = targetWeight;
        this.role = role;
    }

    public User(String first_name, String last_name, int age, float weight, float height, float calories, float protein, float carbs, float fat, float sugar, float targetWeight) {
        this.first_name = first_name;
        this.last_name = last_name;
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

    public User(String first_name, String last_name, String phone, String email, int age, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }
}
