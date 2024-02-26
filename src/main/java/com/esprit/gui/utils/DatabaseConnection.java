package com.esprit.gui.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection con;
    private static String URL = "jdbc:mysql://localhost:3306/";
    private static String USER = "root";
    private static String DB_NAME = "gym";
    private static String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("could not connect to the database.");
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
