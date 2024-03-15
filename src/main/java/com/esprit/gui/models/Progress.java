package com.esprit.gui.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Progress {
    private int progressId;
    private int userId;  // To associate the progress with a specific user
    private String activityType;  // Type of activity for which progress is being tracked (e.g., exercise, diet)
    private String description;  // Description of the progress or activity
    private LocalDate date;  // Date of the progress update
    private int duration;  // Duration of the activity in minutes or hours
    private int intensity;  // Intensity level of the activity (e.g., low, medium, high)
    private String notes;  // Additional notes or comments about the progress

    public Progress(){

    }
    public Progress(int id, int userId, String activityType, String description, LocalDate date, int duration, int intensity, String notes) {
        this.progressId = id;
        this.userId = userId;
        this.activityType = activityType;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.intensity = intensity;
        this.notes = notes;
    }

    public Progress(int userId, String activityType, String description, LocalDate date, int duration, int intensity, String notes) {
        this.userId = userId;
        this.activityType = activityType;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.intensity = intensity;
        this.notes = notes;
    }
}
