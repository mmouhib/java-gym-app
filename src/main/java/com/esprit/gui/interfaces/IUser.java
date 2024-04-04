package com.esprit.gui.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.esprit.gui.models.Meal;
import com.esprit.gui.models.User;

public interface IUser {
    List<User> findAll() throws SQLException;
    User findById(int id) throws SQLException;
    void save(User user) throws SQLException;
    void update(User user) throws SQLException;;
    void delete(int id) throws SQLException;
    boolean signIn(User user) throws SQLException;;

    List<Meal> getUserMeals(int id) throws SQLException;
}
