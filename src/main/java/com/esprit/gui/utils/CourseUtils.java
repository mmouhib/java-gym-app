package com.esprit.gui.utils;

import com.esprit.gui.models.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseUtils {

    public void insertCourse(Course course) {
        String sql = "INSERT INTO course(id, name, type, coach) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, course.getId());
            pstmt.setString(2, course.getName());
            pstmt.setString(3, course.getType());
            pstmt.setString(4, course.getCoach());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        return DatabaseConnection.getConnection();
    }
}
