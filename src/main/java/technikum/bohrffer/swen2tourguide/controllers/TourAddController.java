package technikum.bohrffer.swen2tourguide.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import technikum.bohrffer.swen2tourguide.models.Tour;
import technikum.bohrffer.swen2tourguide.services.TourAddService;

import java.net.URL;
import java.util.ResourceBundle;

public class TourAddController implements Initializable {

    private static final Logger logger = LogManager.getLogger(TourAddController.class);
    private final TourAddService tourAddService = new TourAddService();

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
    private TextField fromLat;
    @FXML
    private TextField fromLng;
    @FXML
    private TextField toLat;
    @FXML
    private TextField toLng;
    @FXML
    public Button submitButton;

    private Stage stage;
    private final ListView<Tour> tourList;

    public TourAddController(ListView<Tour> tourList) {
        this.tourList = tourList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void handleSubmitButton() {
        if (!validateForm()) {
            logger.warn("The form is invalid!");
        } else {
            submit();
        }
        logger.info("handleSubmitButton clicked");
    }

    public void submit() {
        double dist = Double.parseDouble(distance.getText());
        double timeDouble = Double.parseDouble(time.getText());
        double fromLatDouble = Double.parseDouble(fromLat.getText());
        double fromLngDouble = Double.parseDouble(fromLng.getText());
        double toLatDouble = Double.parseDouble(toLat.getText());
        double toLngDouble = Double.parseDouble(toLng.getText());
        Tour tour = new Tour(name.getText(), description.getText(), from.getText(), to.getText(), transport.getText(),
                dist, timeDouble, fromLatDouble, fromLngDouble, toLatDouble, toLngDouble);
        logger.info("Tour created: " + tour);
        tourList.getItems().add(tour);
        stage.close();
        tourAddService.AddTour(tour);
    }

    private boolean validateForm() {
        boolean isValid = true;

        String tourName = name.getText();
        if (tourName == null || tourName.isEmpty()) {
            logger.warn("Invalid tour name!");
            isValid = false;
        }
        String desc = description.getText();
        String fromText = from.getText();
        String toText = to.getText();
        String transp = transport.getText();
        if (desc.isEmpty() || fromText.isEmpty() || toText.isEmpty() || transp.isEmpty()) {
            logger.warn("Please fill out all fields!");
            isValid = false;
        }
        String input = distance.getText();
        if (!input.matches("\\d+(\\.\\d+)?$")) {
            logger.warn("Distance must be a number!");
            isValid = false;
        }
        input = time.getText();
        if (!input.matches("\\d+(\\.\\d+)?$")) {
            logger.warn("Time must be a number!");
            isValid = false;
        }
        input = fromLat.getText();
        if (!input.matches("\\d+(\\.\\d+)?$")) {
            logger.warn("From Latitude must be a number!");
            isValid = false;
        }
        input = fromLng.getText();
        if (!input.matches("\\d+(\\.\\d+)?$")) {
            logger.warn("From Longitude must be a number!");
            isValid = false;
        }
        input = toLat.getText();
        if (!input.matches("\\d+(\\.\\d+)?$")) {
            logger.warn("To Latitude must be a number!");
            isValid = false;
        }
        input = toLng.getText();
        if (!input.matches("\\d+(\\.\\d+)?$")) {
            logger.warn("To Longitude must be a number!");
            isValid = false;
        }
        return isValid;
    }
}
