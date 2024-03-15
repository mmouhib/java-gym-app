package com.esprit.gui.repository;

import com.esprit.gui.interfaces.IProgress;
import com.esprit.gui.models.Progress;
import com.esprit.gui.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgressRepository implements IProgress {

    private Connection connection;

    public ProgressRepository() {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public Progress createProgress(int userId, String activityType, String description, LocalDate date, int duration, int intensity, String notes) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO progress(userId, activityType, description, date, duration, intensity, notes) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, userId);
            ps.setString(2, activityType);
            ps.setString(3, description);
            ps.setDate(4, Date.valueOf(date));
            ps.setInt(5, duration);
            ps.setInt(6, intensity);
            ps.setString(7, notes);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }

        return lastAddedProgress();
    }

    @Override
    public Progress getProgressById(int progressId) {
        Progress progress = new Progress();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM progress WHERE progressId = ?");
            ps.setInt(1, progressId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                progress.setProgressId(rs.getInt("progressId"));
                progress.setUserId(rs.getInt("userId"));
                progress.setActivityType(rs.getString("activityType"));
                progress.setDescription(rs.getString("description"));
                progress.setDate(rs.getDate("date").toLocalDate());
                progress.setDuration(rs.getInt("duration"));
                progress.setIntensity(rs.getInt("intensity"));
                progress.setNotes(rs.getString("notes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
        return progress;
    }

    @Override
    public List<Progress> getProgressByUserId(int userId) {
        List<Progress> progressList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM progress WHERE userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Progress progress = new Progress();
                progress.setProgressId(rs.getInt("progressId"));
                progress.setUserId(rs.getInt("userId"));
                progress.setActivityType(rs.getString("activityType"));
                progress.setDescription(rs.getString("description"));
                progress.setDate(rs.getDate("date").toLocalDate());
                progress.setDuration(rs.getInt("duration"));
                progress.setIntensity(rs.getInt("intensity"));
                progress.setNotes(rs.getString("notes"));
                progressList.add(progress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
        return progressList;
    }

    @Override
    public void updateProgress(int progressId, int userId, String activityType, String description, LocalDate date, int duration, int intensity, String notes) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE progress SET userId=?, activityType=?, description=?, date=?, duration=?, intensity=?, notes=? WHERE progressId=?");
            ps.setInt(1, userId);
            ps.setString(2, activityType);
            ps.setString(3, description);
            ps.setDate(4, Date.valueOf(date));
            ps.setInt(5, duration);
            ps.setInt(6, intensity);
            ps.setString(7, notes);
            ps.setInt(8, progressId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
    }

    @Override
    public void deleteProgress(int progressId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM progress WHERE progressId = ?");
            ps.setInt(1, progressId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
    }

    @Override
    public List<Progress> getAllProgress() {
        List<Progress> progressList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM progress");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Progress progress = new Progress();
                progress.setProgressId(rs.getInt("progressId"));
                progress.setUserId(rs.getInt("userId"));
                progress.setActivityType(rs.getString("activityType"));
                progress.setDescription(rs.getString("description"));
                progress.setDate(rs.getDate("date").toLocalDate());
                progress.setDuration(rs.getInt("duration"));
                progress.setIntensity(rs.getInt("intensity"));
                progress.setNotes(rs.getString("notes"));
                progressList.add(progress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
        return progressList;
    }

    private Progress lastAddedProgress() {
        Progress progress = new Progress();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM progress ORDER BY progressId DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                progress.setProgressId(rs.getInt("progressId"));
                progress.setUserId(rs.getInt("userId"));
                progress.setActivityType(rs.getString("activityType"));
                progress.setDescription(rs.getString("description"));
                progress.setDate(rs.getDate("date").toLocalDate());
                progress.setDuration(rs.getInt("duration"));
                progress.setIntensity(rs.getInt("intensity"));
                progress.setNotes(rs.getString("notes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
        return progress;
    }
}
