package com.esprit.gui.interfaces;

import java.util.List;

import com.esprit.gui.models.Meal;
import com.esprit.gui.models.User;

public interface IUser {
    List<User> findAll();
    User findById(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
    List<Meal> getUserMeals(int id);
}
