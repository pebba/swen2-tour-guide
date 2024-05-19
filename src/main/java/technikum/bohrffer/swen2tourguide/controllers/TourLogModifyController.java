package technikum.bohrffer.swen2tourguide.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import technikum.bohrffer.swen2tourguide.models.TourLog;

import javafx.scene.control.TableView;

public class TourLogModifyController {

    @FXML
    private TextField comment;
    @FXML
    private TextField difficulty;
    @FXML
    private TextField totalDistance;
    @FXML
    private TextField totalTime;
    @FXML
    private TextField rating;
    @FXML
    public Button submitButton;

    private final TourLog tourLog;
    private Stage stage;

    private final TableView<TourLog> tourLogsTable;

    public TourLogModifyController(TourLog tourLog, TableView<TourLog> tourLogsTable) {
        this.tourLog = tourLog;
        this.tourLogsTable = tourLogsTable;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        comment.setText(tourLog.getComment());
        difficulty.setText(tourLog.getDifficulty());
        totalDistance.setText(String.valueOf(tourLog.getTotalDistance()));
        totalTime.setText(String.valueOf(tourLog.getTotalTime()));
        rating.setText(String.valueOf(tourLog.getRating()));
    }

    @FXML
    public void handleSubmitButton() {
        if (!validateForm()) {
            System.out.println("The form is invalid!");
        } else {
            submit();
        }
    }

    private void submit() {
        tourLog.setComment(comment.getText());
        tourLog.setDifficulty(difficulty.getText());
        tourLog.setTotalDistance(Double.parseDouble(totalDistance.getText()));
        tourLog.setTotalTime(Double.parseDouble(totalTime.getText()));
        tourLog.setRating(Integer.parseInt(rating.getText()));

        tourLogsTable.refresh();
        stage.close();
    }

    private boolean validateForm() {
        boolean isValid = true;

        if (comment.getText().isEmpty() || difficulty.getText().isEmpty() || totalDistance.getText().isEmpty() || totalTime.getText().isEmpty() || rating.getText().isEmpty()) {
            System.out.println("All fields must be filled out.");
            isValid = false;
        }

        if (!totalDistance.getText().matches("\\d+(\\.\\d+)?")) {
            System.out.println("Distance must be a number.");
            isValid = false;
        }

        if (!totalTime.getText().matches("\\d+(\\.\\d+)?")) {
            System.out.println("Time must be a number.");
            isValid = false;
        }

        if (!rating.getText().matches("\\d+")) {
            System.out.println("Rating must be an integer.");
            isValid = false;
        }

        return isValid;
    }
}
