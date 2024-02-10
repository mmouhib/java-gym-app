package com.esprit.gui.repository;

import com.esprit.gui.interfaces.IUser;
import com.esprit.gui.models.Meal;
import com.esprit.gui.models.User;
import com.esprit.gui.utils.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class UserRepository implements IUser {

    Connection connection;

    public UserRepository() {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Meal> getUserMeals(int id) {
        return null;
    }
}
