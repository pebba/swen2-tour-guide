package technikum.bohrffer.swen2tourguide.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import technikum.bohrffer.swen2tourguide.models.Tour;
import technikum.bohrffer.swen2tourguide.models.TourLog;

import javafx.scene.control.TableView;
import java.time.LocalDateTime;

public class TourLogAddController {

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

    private final Tour tour;
    private Stage stage;

    private final TableView<TourLog> tourLogsTable;

    public TourLogAddController(Tour tour, TableView<TourLog> tourLogsTable) {
        this.tour = tour;
        this.tourLogsTable = tourLogsTable;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
        double distance = Double.parseDouble(totalDistance.getText());
        double time = Double.parseDouble(totalTime.getText());
        int logRating = Integer.parseInt(rating.getText());

        TourLog tourLog = new TourLog(LocalDateTime.now(), comment.getText(), difficulty.getText(), distance, time, logRating);
        tour.addTourLog(tourLog);
        tourLogsTable.getItems().add(tourLog);
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
