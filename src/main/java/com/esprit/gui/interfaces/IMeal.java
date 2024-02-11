package com.esprit.gui.interfaces;

import com.esprit.gui.models.Meal;

import java.util.List;

public interface IMeal extends ICrud<Meal>{
    List<Meal> getMealsByUser(int id);
    List<Meal> getMealsByDate(String date);
    List<Meal> getMealsByDates(String start, String end);
}
