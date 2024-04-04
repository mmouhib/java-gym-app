package com.esprit.gym_gui.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Goal {
    private int goalId;
    private int userId;  // To associate the goal with a specific user
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate targetDate;
    private int targetValue;  // The numerical target value associated with the goal (e.g., weight, distance, etc.)
    private String unit;  // The unit of measurement for the target value (e.g., kg, km, minutes)
    private boolean achieved;
    private String progressNotes;  // Any additional notes or updates regarding progress

    public Goal(){

    }

    public Goal(int goalId, int userId, String title, String description, LocalDate startDate, LocalDate targetDate, int targetValue, String unit) {
        this.goalId = goalId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.targetDate = targetDate;
        this.targetValue = targetValue;
        this.unit = unit;
        this.achieved = false;
        this.progressNotes = "";
    }

    public Goal(int userId, String title, String description, LocalDate startDate, LocalDate targetDate, int targetValue, String unit) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.targetDate = targetDate;
        this.targetValue = targetValue;
        this.unit = unit;
        this.achieved = false;
        this.progressNotes = "";
    }
}
