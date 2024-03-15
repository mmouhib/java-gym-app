package com.esprit.gui.interfaces;

import com.esprit.gui.models.Progress;
import java.time.LocalDate;
import java.util.List;

public interface IProgress {
    Progress createProgress(int userId, String activityType, String description, LocalDate date, int duration, int intensity, String notes);
    Progress getProgressById(int progressId);
    List<Progress> getProgressByUserId(int userId);
    void updateProgress(int progressId, int userId, String activityType, String description, LocalDate date, int duration, int intensity, String notes);
    void deleteProgress(int progressId);
    List<Progress> getAllProgress();
}

