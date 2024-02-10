package com.esprit.gui;

import com.esprit.gui.repository.MealRepository;
import com.esprit.gui.utils.DatabaseConnection;

import java.sql.Connection;

public class Dummy {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        MealRepository mealRepository = new MealRepository();
        System.out.println(mealRepository.findAll());
    }
}
