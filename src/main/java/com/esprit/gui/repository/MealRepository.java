package com.esprit.gui.repository;

import com.esprit.gui.interfaces.IMeal;
import com.esprit.gui.models.Meal;
import com.esprit.gui.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealRepository implements IMeal {

    private Connection connection;

    public MealRepository() {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Meal> findAll() {
        Connection connection = DatabaseConnection.getConnection();
        List<Meal> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from meal");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt(1));
                meal.setName(rs.getString(2));
                meal.setCalories(rs.getInt(3));
                meal.setProtein(rs.getInt(4));
                meal.setCarbs(rs.getInt(5));
                meal.setFat(rs.getInt(6));
                meal.setSugar(rs.getInt(7));
                meal.setUserId(rs.getInt(8));
                meal.setDate(rs.getDate(9));
                lst.add(meal);
            }
        } catch (SQLException e) {
            return null;
        }
        return lst;
    }

    @Override
    public Meal findById(int id) {
        //return the meal by its id from database
        Meal meal = new Meal();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from meal where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                meal.setId(rs.getInt(1));
                meal.setName(rs.getString(2));
                meal.setCalories(rs.getInt(3));
                meal.setProtein(rs.getInt(4));
                meal.setCarbs(rs.getInt(5));
                meal.setFat(rs.getInt(6));
                meal.setSugar(rs.getInt(7));
                meal.setUserId(rs.getInt(8));
                meal.setDate(rs.getDate(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }

    @Override
    public Meal save(Meal meal) {
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
            ps.setDate(8, (Date) meal.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lastAddedMeal();
    }


    private Meal lastAddedMeal() {
        // get last added meal from database
        Meal meal = new Meal();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM meal ORDER BY id DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                meal.setId(rs.getInt(1));
                meal.setName(rs.getString(2));
                meal.setCalories(rs.getInt(3));
                meal.setProtein(rs.getInt(4));
                meal.setCarbs(rs.getInt(5));
                meal.setFat(rs.getInt(6));
                meal.setSugar(rs.getInt(7));
                meal.setUserId(rs.getInt(8));
                meal.setDate(rs.getDate(9));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return meal;
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
            ps.setDate(8, (Date) meal.getDate());
            ps.setInt(9, meal.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());

        }
    }

    @Override
    public List<Meal> getMealsByUser(int id) {
        List<Meal> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from meal where userId=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt(1));
                meal.setName(rs.getString(2));
                meal.setCalories(rs.getInt(3));
                meal.setProtein(rs.getInt(4));
                meal.setCarbs(rs.getInt(5));
                meal.setFat(rs.getInt(6));
                meal.setSugar(rs.getInt(7));
                meal.setUserId(rs.getInt(8));
                meal.setDate(rs.getDate(9));
                lst.add(meal);
            }
        } catch (SQLException e) {
            return null;
        }
        return lst;
    }

    @Override
    public List<Meal> getMealsByDate(String date) {
        List<Meal> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from meal where date>=?");
            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt(1));
                meal.setName(rs.getString(2));
                meal.setCalories(rs.getInt(3));
                meal.setProtein(rs.getInt(4));
                meal.setCarbs(rs.getInt(5));
                meal.setFat(rs.getInt(6));
                meal.setSugar(rs.getInt(7));
                meal.setUserId(rs.getInt(8));
                meal.setDate(rs.getDate(9));
                lst.add(meal);
            }
        } catch (SQLException e) {
            return null;
        }
        return lst;
    }

    @Override
    public List<Meal> getMealsByDates(String start, String end) {
        // get meals between two dates
        List<Meal> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from meal where date>=? and date<=?");
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt(1));
                meal.setName(rs.getString(2));
                meal.setCalories(rs.getInt(3));
                meal.setProtein(rs.getInt(4));
                meal.setCarbs(rs.getInt(5));
                meal.setFat(rs.getInt(6));
                meal.setSugar(rs.getInt(7));
                meal.setUserId(rs.getInt(8));
                meal.setDate(rs.getDate(9));
                lst.add(meal);
            }
        } catch (SQLException e) {
            return null;
        }
        return lst;
    }
}
