package technikum.bohrffer.swen2tourguide.models;

import java.time.LocalDateTime;

public class TourLog {
    private LocalDateTime date;
    private String comment;
    private String difficulty;
    private double totalDistance;
    private double totalTime;
    private int rating;

    public TourLog(LocalDateTime date, String comment, String difficulty, double totalDistance, double totalTime, int rating) {
        this.date = date;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.rating = rating;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
