package com.esprit.gui.repository;

import com.esprit.gui.interfaces.IMeal;
import com.esprit.gui.models.Meal;
import com.esprit.gui.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealRepository implements IMeal {

    Connection connection;

    public MealRepository() {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Meal> findAll() {
        List<Meal> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from meal");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt(1));
                // todo: add the rest of the fields
                lst.add(meal);
            }
        } catch (SQLException e) {
            return null;
        }
        return lst;
    }

    @Override
    public Meal findById(int id) {
        return null;
    }

    @Override
    public void save(Meal meal) {

    }

    @Override
    public void update(Meal meal) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Meal> getMealsByUser(int id) {
        return null;
    }

    @Override
    public List<Meal> getMealsByDate(String date) {
        return null;
    }

    @Override
    public List<Meal> getMealsByDates(String start, String end) {
        return null;
    }
}
