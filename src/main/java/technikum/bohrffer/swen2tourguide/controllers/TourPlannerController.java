package technikum.bohrffer.swen2tourguide.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import technikum.bohrffer.swen2tourguide.TourApp;
import technikum.bohrffer.swen2tourguide.models.Tour;
import technikum.bohrffer.swen2tourguide.models.TourLog;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class TourPlannerController implements Initializable {

    @FXML
    private ListView<Tour> tourList;

    @FXML
    private ImageView mapView;

    @FXML
    private TableView<TourLog> tourLogsTable;

    @FXML
    private TableColumn<TourLog, LocalDateTime> dateColumn;

    @FXML
    private TableColumn<TourLog, Double> durationColumn;

    @FXML
    private TableColumn<TourLog, Double> distanceColumn;

    @FXML
    private TableColumn<TourLog, String> commentColumn;

    @FXML
    private TableColumn<TourLog, String> difficultyColumn;

    @FXML
    private TableColumn<TourLog, Integer> ratingColumn;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Detail> detailsTableView;

    @FXML
    private TableColumn<Detail, String> detailKeyColumn;

    @FXML
    private TableColumn<Detail, String> detailValueColumn;

    private final ObservableList<Detail> detailsData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("totalDistance"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        detailKeyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        detailValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        detailsTableView.setItems(detailsData);

        // sample data
        tourList.getItems().addAll(
                new Tour("Wienerwald", "Beautiful forest tour", "Vienna", "Wienerwald", "Hiking", 12.0, 3.0, "https://via.placeholder.com/150"),
                new Tour("Dopplerhütte", "Challenging mountain bike route", "Vienna", "Dopplerhütte", "Biking", 15.0, 2.5, "https://via.placeholder.com/150")
        );

        // sample logs
        tourList.getItems().get(0).addTourLog(new TourLog(LocalDateTime.now(), "Nice tour", "Easy", 12.0, 3.0, 5));
        tourList.getItems().get(1).addTourLog(new TourLog(LocalDateTime.now(), "Great ride", "Hard", 15.0, 2.5, 4));

        tourList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadTourDetails(newValue);
                loadTourLogs(newValue);
            }
        });

        if (!tourList.getItems().isEmpty()) {
            tourList.getSelectionModel().select(0);
        }
    }

    private void loadTourDetails(Tour tour) {
        detailsData.clear();
        detailsData.add(new Detail("Name", tour.getName()));
        detailsData.add(new Detail("Description", tour.getDescription()));
        detailsData.add(new Detail("From", tour.getFrom()));
        detailsData.add(new Detail("To", tour.getTo()));
        detailsData.add(new Detail("Transport", tour.getTransportType()));
        detailsData.add(new Detail("Distance", String.valueOf(tour.getDistance())));
        detailsData.add(new Detail("Time", String.valueOf(tour.getEstimatedTime())));

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

    private void loadTourLogs(Tour tour) {
        tourLogsTable.getItems().clear();
        tourLogsTable.getItems().addAll(tour.getTourLogs());
    }

    @FXML
    private void handleAddTour() {
        System.out.println("Add Tour");

        try {
            FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("tour-add.fxml"));
            loader.setControllerFactory(controllerClass -> new TourAddController(tourList));
            Parent root = loader.load();
            TourAddController tourAddController = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Add Tour");
            stage.setScene(scene);
            stage.show();

            tourAddController.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleModifyTour() {
        Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            try {
                FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("tour-modify.fxml"));
                loader.setControllerFactory(controllerClass -> new TourModifyController(selectedTour, tourList));
                Parent root = loader.load();
                TourModifyController tourModifyController = loader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Modify Tour");
                stage.setScene(scene);
                stage.show();

                tourModifyController.setStage(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleRemoveTour() {
        Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            tourList.getItems().remove(selectedTour);
            clearTourDetailsAndLogs();
        }
    }

    private void clearTourDetailsAndLogs() {
        detailsData.clear();
        mapView.setImage(null);
        tourLogsTable.getItems().clear();
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().toLowerCase();
        ObservableList<Tour> filteredTours = FXCollections.observableArrayList();
        for (Tour tour : tourList.getItems()) {
            if (tour.getName().toLowerCase().contains(query)) {
                filteredTours.add(tour);
            }
        }
        tourList.setItems(filteredTours);
    }

    @FXML
    private void handleAddTourLog() {
        System.out.println("Add Tour Log");

        Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
        if (selectedTour == null) {
            System.out.println("No tour selected.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("tour-log-add.fxml"));
            loader.setControllerFactory(controllerClass -> new TourLogAddController(selectedTour, tourLogsTable));
            Parent root = loader.load();
            TourLogAddController tourLogAddController = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Add Tour Log");
            stage.setScene(scene);
            stage.show();

            tourLogAddController.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemoveTourLog() {
        TourLog selectedLog = tourLogsTable.getSelectionModel().getSelectedItem();
        if (selectedLog != null) {
            Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
            if (selectedTour != null) {
                selectedTour.getTourLogs().remove(selectedLog);
                tourLogsTable.getItems().remove(selectedLog);
            }
        }
    }

    @FXML
    private void handleModifyTourLog() {
        TourLog selectedLog = tourLogsTable.getSelectionModel().getSelectedItem();
        if (selectedLog == null) {
            System.out.println("No tour log selected.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("tour-log-modify.fxml"));
            loader.setControllerFactory(controllerClass -> new TourLogModifyController(selectedLog, tourLogsTable));
            Parent root = loader.load();
            TourLogModifyController tourLogModifyController = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Modify Tour Log");
            stage.setScene(scene);
            stage.show();

            tourLogModifyController.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Detail {
        private final SimpleStringProperty key;
        private final SimpleStringProperty value;

        public Detail(String key, String value) {
            this.key = new SimpleStringProperty(key);
            this.value = new SimpleStringProperty(value);
        }

        public String getKey() {
            return key.get();
        }

        public void setKey(String key) {
            this.key.set(key);
        }

        public String getValue() {
            return value.get();
        }

        public void setValue(String value) {
            this.value.set(value);
        }
    }
}
