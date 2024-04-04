package com.esprit.gym_gui.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection con;
    private static String URL = "jdbc:mysql://localhost:3306/";
    private static String USER = "root";
    private static String DB_NAME = "gym";

    private static String PASSWORD = "";
    private Connection connection;


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL+DB_NAME, USER, PASSWORD);
            System.out.println("coonected to database");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
