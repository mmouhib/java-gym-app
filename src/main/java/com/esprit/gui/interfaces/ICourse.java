package com.esprit.gui.interfaces;

import com.esprit.gui.models.Course;

import java.util.List;

public interface ICourse {
    List<Course> findAll();
    Course findById(int id);
    Course save(Course course);
    void update(Course course);
    void delete(int id);
//    List<Course> getCourseByUser(int id);
    List<Course> getCoursesByDate(String date);
    List<Course> getCoursesByDates(String start, String end);
}
