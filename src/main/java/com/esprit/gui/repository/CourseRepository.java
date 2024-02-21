package com.esprit.gui.repository;

import com.esprit.gui.interfaces.ICourse;
import com.esprit.gui.models.Course;
import com.esprit.gui.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements ICourse {

    private Connection connection;

    public CourseRepository() {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Course> findAll() {
        Connection connection = DatabaseConnection.getConnection();
        List<Course> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from course");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setType(rs.getString(3));
                course.setCoach(rs.getString(4));
                course.setUserId(rs.getInt(5));
                course.setNumPlaces(rs.getInt(6));
                course.setStartDate(rs.getDate(7));
                course.setEndDate(rs.getDate(8));
                lst.add(course);
            }
        } catch (SQLException e) {
            return null;
        }
        return lst;
    }

    @Override
    public Course findById(int id) {
        //return the course by its id from database
        Course course = new Course();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from course where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setType(rs.getString(3));
                course.setCoach(rs.getString(4));
                course.setUserId(rs.getInt(5));
                course.setNumPlaces(rs.getInt(6));
                course.setStartDate(rs.getDate(7));
                course.setEndDate(rs.getDate(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public Course save(Course course) {
        // save course to database
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO course(name, calories, protein, carbs, fat, sugar, userId, date) VALUES(?, ?, ?, ?, ?,?, ?, ?)");
            ps.setString(1, course.getName());
            ps.setString(2,course.getType());
            ps.setString(3,course.getCoach());
            ps.setInt(4, course.getUserId());
            ps.setInt(5,course.getNumPlaces());
            ps.setDate(6, (Date) course.getStartDate());
            ps.setDate(7, (Date) course.getEndDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lastAddedCourse();
    }


    private Course lastAddedCourse() {
        // get last added course from database
        Course course = new Course();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM course ORDER BY id DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setType(rs.getString(3));
                course.setCoach(rs.getString(4));
                course.setUserId(rs.getInt(5));
                course.setNumPlaces(rs.getInt(6));
                course.setStartDate(rs.getDate(7));
                course.setEndDate(rs.getDate(8));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public void update(Course course) {
        // update course in database
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE course SET name=?, calories=?, protein=?, carbs=?, fat=?, sugar=?, userId=?, date=? WHERE id=?");
            ps.setString(1, course.getName());
            ps.setString(2,course.getType());
            ps.setString(3,course.getCoach());
            ps.setInt(4, course.getUserId());
            ps.setInt(5,course.getNumPlaces());
            ps.setDate(6, (Date) course.getStartDate());
            ps.setDate(7, (Date) course.getEndDate());
            ps.setInt(8, course.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM course WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public List<Course> getCourseByUser(int id) {
        List<Course> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from course where userId=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setType(rs.getString(3));
                course.setCoach(rs.getString(4));
                course.setUserId(rs.getInt(5));
                course.setNumPlaces(rs.getInt(6));
                course.setStartDate(rs.getDate(7));
                course.setEndDate(rs.getDate(8));
                lst.add(course);
            }
        } catch (SQLException e) {
            return null;
        }
        return lst;
    }

    @Override
    public List<Course> getCoursesByDate(String date) {
        List<Course> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from course where date>=?");
            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setType(rs.getString(3));
                course.setCoach(rs.getString(4));
                course.setUserId(rs.getInt(5));
                course.setNumPlaces(rs.getInt(6));
                course.setStartDate(rs.getDate(7));
                course.setEndDate(rs.getDate(8));
                lst.add(course);
            }
        } catch (SQLException e) {
            return null;
        }
        return lst;
    }

    @Override
    public List<Course> getCoursesByDates(String start, String end) {
        // get courses between two dates
        List<Course> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from course where date>=? and date<=?");
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setType(rs.getString(3));
                course.setCoach(rs.getString(4));
                course.setUserId(rs.getInt(5));
                course.setNumPlaces(rs.getInt(6));
                course.setStartDate(rs.getDate(7));
                course.setEndDate(rs.getDate(8));
                lst.add(course);
            }
        } catch (SQLException e) {
            return null;
        }
        return lst;
    }
}
