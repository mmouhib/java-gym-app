package com.esprit.gui;

import com.esprit.gui.models.Meal;

public class Dummy
{
    public static void main(String[] args) {

        Meal meal = new Meal();
        System.out.println(meal.createTableQuery());
    }
}
