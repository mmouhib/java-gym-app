package com.esprit.gym_gui;

import com.esprit.gym_gui.repository.MealRepository;
import com.esprit.gym_gui.utils.DatabaseConnection;

import java.sql.Connection;

public class Dummy {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        MealRepository mealRepository = new MealRepository();
        System.out.println(mealRepository.findAll());
    }
}
