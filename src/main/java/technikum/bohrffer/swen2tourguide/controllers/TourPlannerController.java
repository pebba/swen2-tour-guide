package technikum.bohrffer.swen2tourguide.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import technikum.bohrffer.swen2tourguide.models.Tour;
import technikum.bohrffer.swen2tourguide.services.TourService;

public class TourPlannerController {

    @FXML
    private ListView<Tour> tourList;

    @FXML
    private ImageView mapView;

    @FXML
    private TableView<?> tourLogsTable;

    @FXML
    private TextField searchField;

    @FXML
    private void initialize() {
        TourService tourService = new TourService();

        // sample data (geht noch nicht)
        tourList.getItems().addAll(
                new Tour("Wienerwald", "Beautiful forest tour", "Vienna", "Wienerwald", "Hiking", 12.0, 3.0, "https://via.placeholder.com/150"),
                new Tour("Dopplerhütte", "Challenging mountain bike route", "Vienna", "Dopplerhütte", "Biking", 15.0, 2.5, "https://via.placeholder.com/150")
        );

        tourList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadTourDetails(newValue);
            }
        });

        // zeige details von erster tour
        if (!tourList.getItems().isEmpty()) {
            tourList.getSelectionModel().select(0);
        }
    }

    private void loadTourDetails(Tour tour) {
        // zeige route image
        if (tour.getRouteImage() != null) {
            try {
                mapView.setImage(new Image(tour.getRouteImage()));
            } catch (Exception e) {
                System.out.println("Could not load image: " + e.getMessage());
                mapView.setImage(null);
            }
        } else {
            mapView.setImage(null);
        }
    }

    @FXML
    private void handleAddTour() {
        // logik fehlt
    }

    @FXML
    private void handleRemoveTour() {
        Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            tourList.getItems().remove(selectedTour);
        }
    }

    @FXML
    private void handleEditTour() {
        // logik fehlt
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText();
        // logik fehlt
    }
}
