package com.esprit.gym_gui.repository;

import com.esprit.gym_gui.interfaces.IGoal;
import com.esprit.gym_gui.models.Goal;
import com.esprit.gym_gui.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalRepository implements IGoal {

    private Connection connection;

    public GoalRepository() {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public Goal createGoal(int userId, String title, String description, LocalDate startDate, LocalDate targetDate, int targetValue, String unit) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO goal(userId, title, description, startDate, targetDate, targetValue, unit) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setDate(4, Date.valueOf(startDate));
            ps.setDate(5, Date.valueOf(targetDate));
            ps.setInt(6, targetValue);
            ps.setString(7, unit);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastAddedGoal();
    }

    @Override
    public Goal getGoalById(int goalId) {
        Goal goal = new Goal();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM goal WHERE goalId = ?");
            ps.setInt(1, goalId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                goal.setGoalId(rs.getInt("goalId"));
                goal.setUserId(rs.getInt("userId"));
                goal.setTitle(rs.getString("title"));
                goal.setDescription(rs.getString("description"));
                goal.setStartDate(rs.getDate("startDate").toLocalDate());
                goal.setTargetDate(rs.getDate("targetDate").toLocalDate());
                goal.setTargetValue(rs.getInt("targetValue"));
                goal.setUnit(rs.getString("unit"));
                goal.setAchieved(rs.getBoolean("achieved"));
                goal.setProgressNotes(rs.getString("progressNotes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goal;
    }

    @Override
    public List<Goal> getGoalsByUserId(int userId) {
        List<Goal> goals = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM goal WHERE userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Goal goal = new Goal();
                goal.setGoalId(rs.getInt("goalId"));
                goal.setUserId(rs.getInt("userId"));
                goal.setTitle(rs.getString("title"));
                goal.setDescription(rs.getString("description"));
                goal.setStartDate(rs.getDate("startDate").toLocalDate());
                goal.setTargetDate(rs.getDate("targetDate").toLocalDate());
                goal.setTargetValue(rs.getInt("targetValue"));
                goal.setUnit(rs.getString("unit"));
                goal.setAchieved(rs.getBoolean("achieved"));
                goal.setProgressNotes(rs.getString("progressNotes"));
                goals.add(goal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals;
    }

    private Goal lastAddedGoal() {
        // get last added goal from the database
        Goal goal = new Goal();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM goal ORDER BY goalId DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                goal.setGoalId(rs.getInt("goalId"));
                goal.setUserId(rs.getInt("userId"));
                goal.setTitle(rs.getString("title"));
                goal.setDescription(rs.getString("description"));
                goal.setStartDate(rs.getDate("startDate").toLocalDate());
                goal.setTargetDate(rs.getDate("targetDate").toLocalDate());
                goal.setTargetValue(rs.getInt("targetValue"));
                goal.setUnit(rs.getString("unit"));
                goal.setAchieved(rs.getBoolean("achieved"));
                goal.setProgressNotes(rs.getString("progressNotes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goal;
    }

    @Override
    public void updateGoal(int goalId, String title, String description, LocalDate startDate, LocalDate targetDate, int targetValue, String unit, boolean achieved, String progressNotes) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE goal SET title=?, description=?, startDate=?, targetDate=?, targetValue=?, unit=?, achieved=?, progressNotes=? WHERE goalId=?");
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setDate(3, Date.valueOf(startDate));
            ps.setDate(4, Date.valueOf(targetDate));
            ps.setInt(5, targetValue);
            ps.setString(6, unit);
            ps.setBoolean(7, achieved);
            ps.setString(8, progressNotes);
            ps.setInt(9, goalId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGoal(int goalId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM goal WHERE goalId = ?");
            ps.setInt(1, goalId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Goal> getAllGoals() {
        List<Goal> goals = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM goal");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Goal goal = new Goal();
                goal.setGoalId(rs.getInt("goalId"));
                goal.setUserId(rs.getInt("userId"));
                goal.setTitle(rs.getString("title"));
                goal.setDescription(rs.getString("description"));
                goal.setStartDate(rs.getDate("startDate").toLocalDate());
                goal.setTargetDate(rs.getDate("targetDate").toLocalDate());
                goal.setTargetValue(rs.getInt("targetValue"));
                goal.setUnit(rs.getString("unit"));
                goal.setAchieved(rs.getBoolean("achieved"));
                goal.setProgressNotes(rs.getString("progressNotes"));
                goals.add(goal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals;
    }

}
