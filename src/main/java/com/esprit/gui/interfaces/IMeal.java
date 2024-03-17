package com.esprit.gui.interfaces;

import com.esprit.gui.models.Meal;

import java.util.List;

public interface IMeal {
    List<Meal> findAll();
    Meal findById(int id);
    Meal save(Meal meal);
    void update(Meal meal);
    void delete(int id);
    List<Meal> getMealsByUser(int id);
    List<Meal> getMealsByDate(String date);
    List<Meal> getMealsByDates(String start, String end);
}
