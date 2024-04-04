package com.esprit.gym_gui.interfaces;

import com.esprit.gym_gui.models.Goal;

import java.time.LocalDate;
import java.util.List;

public interface IGoal {
    Goal createGoal(int userId, String title, String description, LocalDate startDate, LocalDate targetDate, int targetValue, String unit);
    Goal getGoalById(int goalId);
    List<Goal> getGoalsByUserId(int userId);
    void updateGoal(int goalId, String title, String description, LocalDate startDate, LocalDate targetDate, int targetValue, String unit, boolean achieved, String progressNotes);
    void deleteGoal(int goalId);
    List<Goal> getAllGoals();
}
