package com.esprit.gui;

import com.esprit.gui.models.User;
import com.esprit.gui.repository.UserRepository;
import com.esprit.gui.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class dummyUser {
    public static void main(String[] args) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        // List all users
        UserRepository u = new UserRepository();
        List<User> users = u.findAll();

        // Print user details
        for (User user : users) {
            System.out.println(user);
        }

        // Add user
        User newUser = new User();
        newUser.setFirst_name("test");
        newUser.setLast_name("user");
        newUser.setPhone("1234567890");
        newUser.setEmail("test@example.tn");
        newUser.setPassword("0000");
        newUser.setAge(25);
        newUser.setGender("Male");
        newUser.setWeight(70.0f);
        newUser.setHeight(170.0f);
        newUser.setCalories(2000.0f);
        newUser.setProtein(150.0f);
        newUser.setCarbs(250.0f);
        newUser.setFat(80.0f);
        newUser.setSugar(70.0f);
        newUser.setTargetWeight(160.0f);
        newUser.setRole("user");
        u.save(newUser);
        System.out.println("User created successfully!");
        // Test the create method
        users = u.findAll();
        for (User user : users) {
            System.out.println(user);
        }

        // test sign in method (booth credentials are corrects)
        User s = newUser;
        s.setEmail("test@example.tn");
        s.setPassword("0000");

        System.out.println(u.signIn(s));

        // test sign in method (email is incorrect)
        s.setEmail("balabla@blabla.tn");
        System.out.println(u.signIn(s));

        // test sign in method (password is incorrect)
        s.setEmail("test@example.tn");
        s.setPassword("0");
        System.out.println(u.signIn(newUser));

        // test sign in method (booth credentials are incorrect)
        s.setEmail("balabla2@blabla.tn");
        System.out.println(u.signIn(newUser));

        // Update user number 4
        newUser.setFirst_name("test-modify");
        newUser.setId(4);
        u.update(newUser);
        System.out.println("User updated successfully!");
        //check
        users = u.findAll();
        for (User user : users) {
            System.out.println(user);
        }

      // delete user number 4
        u.delete(4);
        System.out.println("User deleted successfully!");

        //check
        users = u.findAll();
        for (User user : users) {
            System.out.println(user);
        }


        System.out.println("User find by id");
        //check
        System.out.println(u.findById(2));


    }

}
