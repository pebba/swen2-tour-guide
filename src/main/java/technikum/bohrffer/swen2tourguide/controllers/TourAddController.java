package technikum.bohrffer.swen2tourguide.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import technikum.bohrffer.swen2tourguide.models.Tour;
import technikum.bohrffer.swen2tourguide.services.TourAddService;
import technikum.bohrffer.swen2tourguide.services.TourService;

import java.net.URL;
import java.util.ResourceBundle;

public class TourAddController implements Initializable {

    private static final Logger logger = LogManager.getLogger(TourAddController.class);
    private final TourAddService tourAddService = new TourAddService();
    private final TourService tourService = new TourService();

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
    @FXML
    private WebView mapView;

    private Stage stage;
    private final ListView<Tour> tourList;

    public TourAddController(ListView<Tour> tourList) {
        this.tourList = tourList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = mapView.getEngine();
        URL mapUrl = getClass().getResource("/leaflet_map.html");
        assert mapUrl != null;
        webEngine.load(mapUrl.toString());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void handleSubmitButton() {
        if (!validateForm()) {
            logger.warn("The form is invalid!");
        } else {
            fetchCoordinatesAndSubmit();
        }
        logger.info("handleSubmitButton clicked");
    }

    private void fetchCoordinatesAndSubmit() {
        tourService.fetchCoordinates(from.getText(), (fromCoords) -> {
            tourService.fetchCoordinates(to.getText(), (toCoords) -> {
                Platform.runLater(() -> {
                    double dist = Double.parseDouble(distance.getText());
                    double timeDouble = Double.parseDouble(time.getText());
                    Tour tour = new Tour(
                            name.getText(),
                            description.getText(),
                            from.getText(),
                            to.getText(),
                            transport.getText(),
                            dist,
                            timeDouble,
                            fromCoords[0],
                            fromCoords[1],
                            toCoords[0],
                            toCoords[1]
                    );
                    logger.info("Tour created: " + tour);
                    tourList.getItems().add(tour);
                    stage.close();
                    tourAddService.AddTour(tour);
                    displayRouteOnMap(fromCoords, toCoords);
                });
            });
        });
    }

    private void displayRouteOnMap(double[] fromCoords, double[] toCoords) {
        String script = String.format("plotRoute(%f, %f, %f, %f);", fromCoords[0], fromCoords[1], toCoords[0], toCoords[1]);
        Platform.runLater(() -> mapView.getEngine().executeScript(script));
    }

    private boolean validateForm() {
        boolean isValid = true;

        if (name.getText().isEmpty() || description.getText().isEmpty() || from.getText().isEmpty() ||
                to.getText().isEmpty() || transport.getText().isEmpty() || distance.getText().isEmpty() ||
                time.getText().isEmpty()) {
            logger.warn("All fields must be filled out.");
            isValid = false;
        }

        if (!distance.getText().matches("\\d+(\\.\\d+)?")) {
            logger.warn("Distance must be a number.");
            isValid = false;
        }

        if (!time.getText().matches("\\d+(\\.\\d+)?")) {
            logger.warn("Time must be a number.");
            isValid = false;
        }

        return isValid;
    }
}
