package technikum.bohrffer.swen2tourguide.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import technikum.bohrffer.swen2tourguide.models.Tour;
import javafx.scene.control.ListView;

public class TourModifyController {

    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField from;
    @FXML
    private TextField to;
    @FXML
    private TextField transport;
    @FXML
    private TextField distance;
    @FXML
    private TextField time;
    @FXML
    public Button submitButton;

    private Stage stage;
    private final Tour tour;
    private final ListView<Tour> tourList;

    public TourModifyController(Tour tour, ListView<Tour> tourList) {
        this.tour = tour;
        this.tourList = tourList;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        name.setText(tour.getName());
        description.setText(tour.getDescription());
        from.setText(tour.getFrom());
        to.setText(tour.getTo());
        transport.setText(tour.getTransportType());
        distance.setText(String.valueOf(tour.getDistance()));
        time.setText(String.valueOf(tour.getEstimatedTime()));
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
        tour.setName(name.getText());
        tour.setDescription(description.getText());
        tour.setFrom(from.getText());
        tour.setTo(to.getText());
        tour.setTransportType(transport.getText());
        tour.setDistance(Double.parseDouble(distance.getText()));
        tour.setEstimatedTime(Double.parseDouble(time.getText()));
        stage.close();
        tourList.refresh();
    }

    private boolean validateForm() {
        boolean isValid = true;

        if (name.getText().isEmpty() || description.getText().isEmpty() || from.getText().isEmpty() ||
                to.getText().isEmpty() || transport.getText().isEmpty() || distance.getText().isEmpty() ||
                time.getText().isEmpty()) {
            System.out.println("All fields must be filled out.");
            isValid = false;
        }

        if (!distance.getText().matches("\\d+(\\.\\d+)?")) {
            System.out.println("Distance must be a number.");
            isValid = false;
        }

        if (!time.getText().matches("\\d+(\\.\\d+)?")) {
            System.out.println("Time must be a number.");
            isValid = false;
        }

        return isValid;
    }
}
