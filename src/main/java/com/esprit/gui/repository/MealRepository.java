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
    public List<Meal> getMealsByUser(int id) {
        List<Meal> meals = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM meal WHERE user_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Meal meal = new Meal();
                meal.setId(resultSet.getInt("id"));
                meal.setName(resultSet.getString("name"));
                meal.setDate(resultSet.getDate("date"));
                meal.setQuantity(resultSet.getInt("quantity"));
                meal.setCategory(resultSet.getString("category"));
                meal.setUserId(resultSet.getInt("user_id"));
                meal.setPlateId(resultSet.getInt("plate_id"));
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    @Override
    public List<Meal> getMealsByDate(String date) {
        List<Meal> meals = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM meal WHERE date = ?");
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Meal meal = new Meal();
                meal.setId(resultSet.getInt("id"));
                meal.setName(resultSet.getString("name"));
                meal.setDate(resultSet.getDate("date"));
                meal.setCategory(resultSet.getString("category"));
                meal.setQuantity(resultSet.getInt("quantity"));
                meal.setUserId(resultSet.getInt("user_id"));
                meal.setPlateId(resultSet.getInt("plate_id"));
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    @Override
    public List<Meal> getMealsByDateAndUser(Date date, int userId) {
        List<Meal> meals = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM meal WHERE date = ? AND user_id = ?");
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Meal meal = new Meal();
                meal.setId(resultSet.getInt("id"));
                meal.setName(resultSet.getString("name"));
                meal.setDate(resultSet.getDate("date"));
                meal.setQuantity(resultSet.getInt("quantity"));
                meal.setCategory(resultSet.getString("category"));
                meal.setUserId(resultSet.getInt("user_id"));
                meal.setPlateId(resultSet.getInt("plate_id"));
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }


    @Override
    public Meal save(Meal meal) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO meal (name, date, quantity, category, user_id, plate_id) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, meal.getName());
            preparedStatement.setDate(2, meal.getDate());
            preparedStatement.setInt(3, meal.getQuantity());
            preparedStatement.setString(4, meal.getCategory());
            preparedStatement.setInt(5, meal.getUserId());
            preparedStatement.setInt(6, meal.getPlateId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }

    @Override
    public void update(Meal meal) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE meal SET name = ?, date = ?, quantity = ?, user_id = ?, plate_id = ?, category = ? WHERE id = ?");
            preparedStatement.setString(1, meal.getName());
            preparedStatement.setDate(2, meal.getDate());
            preparedStatement.setInt(3, meal.getQuantity());
            preparedStatement.setInt(4, meal.getUserId());
            preparedStatement.setInt(5, meal.getPlateId());
            preparedStatement.setString(6, meal.getCategory());
            preparedStatement.setInt(7, meal.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM meal WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Meal findById(int id) {
        Meal meal = new Meal();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM meal WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                meal.setId(resultSet.getInt("id"));
                meal.setName(resultSet.getString("name"));
                meal.setDate(resultSet.getDate("date"));
                meal.setQuantity(resultSet.getInt("quantity"));
                meal.setCategory(resultSet.getString("category"));
                meal.setUserId(resultSet.getInt("user_id"));
                meal.setPlateId(resultSet.getInt("plate_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }

    @Override
    public List<Meal> list() {
        List<Meal> meals = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM meal");
            while (resultSet.next()) {
                Meal meal = new Meal();
                meal.setId(resultSet.getInt("id"));
                meal.setName(resultSet.getString("name"));
                meal.setDate(resultSet.getDate("date"));
                meal.setQuantity(resultSet.getInt("quantity"));
                meal.setCategory(resultSet.getString("category"));
                meal.setUserId(resultSet.getInt("user_id"));
                meal.setPlateId(resultSet.getInt("plate_id"));
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }
}
