package com.esprit.gym_gui.repository;

import com.esprit.gym_gui.interfaces.IUser;
import com.esprit.gym_gui.models.Meal;
import com.esprit.gym_gui.models.User;
import com.esprit.gym_gui.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUser {

    Connection connection;

    public UserRepository() {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<User> findAll()  throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<User> users = new ArrayList<>();

            PreparedStatement ps = connection.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setWeight(rs.getFloat("weight"));
                user.setHeight(rs.getFloat("height"));
                user.setCalories(rs.getFloat("calories"));
                user.setProtein(rs.getFloat("protein"));
                user.setCarbs(rs.getFloat("carbs"));
                user.setFat(rs.getFloat("fat"));
                user.setSugar(rs.getFloat("sugar"));
                user.setTargetWeight(rs.getFloat("targetWeight"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }

        return users;
    }

    @Override
    public User findById(int id) throws SQLException {
        String query = "SELECT * FROM user WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setFirst_name(resultSet.getString("first_name"));
                    user.setLast_name(resultSet.getString("last_name"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAge(resultSet.getInt("age"));
                    user.setGender(resultSet.getString("gender"));
                    user.setWeight(resultSet.getFloat("weight"));
                    user.setHeight(resultSet.getFloat("height"));
                    user.setCalories(resultSet.getFloat("calories"));
                    user.setProtein(resultSet.getFloat("protein"));
                    user.setCarbs(resultSet.getFloat("carbs"));
                    user.setFat(resultSet.getFloat("fat"));
                    user.setSugar(resultSet.getFloat("sugar"));
                    user.setTargetWeight(resultSet.getFloat("targetWeight"));
                    user.setRole(resultSet.getString("role"));

                    return user;
                }
            }
        }

        return null; // User not found
    }

    @Override
    public void save(User user)  throws SQLException{
        String query = "INSERT INTO user (first_name, last_name, phone, email, age, gender, weight, height, calories, protein, carbs, fat, sugar, targetWeight, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFirst_name());
            statement.setString(2, user.getLast_name());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getGender());
            statement.setFloat(7, user.getWeight());
            statement.setFloat(8, user.getHeight());
            statement.setFloat(9, user.getCalories());
            statement.setFloat(10, user.getProtein());
            statement.setFloat(11, user.getCarbs());
            statement.setFloat(12, user.getFat());
            statement.setFloat(13, user.getSugar());
            statement.setFloat(14, user.getTargetWeight());
            statement.setString(15, user.getRole());

            statement.executeUpdate();
        }
    }

    @Override
    public void update(User user)  throws SQLException {
        String query = "UPDATE user SET first_name=?, last_name=?, phone=?, email=?, age=?, gender=?, weight=?, height=?, calories=?, protein=?, carbs=?, fat=?, sugar=?, targetWeight=?, role=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFirst_name());
            statement.setString(2, user.getLast_name());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getGender());
            statement.setFloat(7, user.getWeight());
            statement.setFloat(8, user.getHeight());
            statement.setFloat(9, user.getCalories());
            statement.setFloat(10, user.getProtein());
            statement.setFloat(11, user.getCarbs());
            statement.setFloat(12, user.getFat());
            statement.setFloat(13, user.getSugar());
            statement.setFloat(14, user.getTargetWeight());
            statement.setString(15, user.getRole());
            statement.setInt(16, user.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            statement.executeUpdate();
            System.out.println("user deleted");
        }
    }

    @Override
    public List<Meal> getUserMeals(int id) throws SQLException {
        return null;
    }
}
