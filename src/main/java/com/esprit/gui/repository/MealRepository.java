package com.esprit.gui.repository;

import com.esprit.gui.interfaces.IMeal;
import com.esprit.gui.models.Meal;
import com.esprit.gui.utils.DatabaseConnection;

import java.sql.*;
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
        // save meal to database
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO meal(name, calories, protein, carbs, fat, sugar, userId, date) VALUES(?, ?, ?, ?, ?,?, ?, ?)");
            ps.setString(1, meal.getName());
            ps.setInt(2, meal.getCalories());
            ps.setInt(3, meal.getProtein());
            ps.setInt(4, meal.getCarbs());
            ps.setInt(5, meal.getFat());
            ps.setInt(6, meal.getSugar());
            ps.setInt(7, meal.getUserId());
            ps.setDate(8, meal.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Meal meal) {
        // update meal in database
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE meal SET name=?, calories=?, protein=?, carbs=?, fat=?, sugar=?, userId=?, date=? WHERE id=?");
            ps.setString(1, meal.getName());
            ps.setInt(2, meal.getCalories());
            ps.setInt(3, meal.getProtein());
            ps.setInt(4, meal.getCarbs());
            ps.setInt(5, meal.getFat());
            ps.setInt(6, meal.getSugar());
            ps.setInt(7, meal.getUserId());
            ps.setDate(8, meal.getDate());
            ps.setInt(9, meal.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM meal WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
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
