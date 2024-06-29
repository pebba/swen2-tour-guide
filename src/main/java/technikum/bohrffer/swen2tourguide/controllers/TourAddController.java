package technikum.bohrffer.swen2tourguide.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import technikum.bohrffer.swen2tourguide.models.Tour;
import technikum.bohrffer.swen2tourguide.repositories.TestDatabase;
import technikum.bohrffer.swen2tourguide.services.TourAddService;

import java.net.URL;
import java.util.ResourceBundle;

public class TourAddController implements Initializable {

   private TourAddService tourAddService = new TourAddService();

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
            System.out.println("The form is invalid!");
        } else {
            submit();
        }
        System.out.println("handleSubmitButton");
    }

    public void submit() {
        double dist = Double.parseDouble(distance.getText());
        double timeDouble = Double.parseDouble(time.getText());
        String image = "https://via.placeholder.com/150";
        Tour tour = new Tour(name.getText(), description.getText(), from.getText(), to.getText(), transport.getText(),
                dist, timeDouble, image);
        System.out.println(tour.getDistance());
        tourList.getItems().add(tour);
        stage.close();
        tourAddService.AddTour(tour);
        /*
        TestDatabase testDatabase = new TestDatabase();
        TestDatabase.TestCon();
        try {
            TestDatabase.getConnection();
        }catch (Exception e){
            System.out.println(e);
        }
        testDatabase.TestSelect();
        */

    }

    private boolean validateForm() {
        boolean isValid = true;

        String tourName = name.getText();
        if (tourName == null || tourName.isEmpty()) {
            System.out.println("Invalid tour name!");
            isValid = false;
        }
        String desc = description.getText();
        String fromText = from.getText();
        String toText = to.getText();
        String transp = transport.getText();
        if (desc.isEmpty() || fromText.isEmpty() || toText.isEmpty() || transp.isEmpty()) {
            System.out.println("Please fill out all fields!");
            isValid = false;
        }
        String input = distance.getText();
        if (!input.matches("\\d+(\\.\\d+)?$")) {
            System.out.println("Distance must be a number!");
            isValid = false;
        }
        input = time.getText();
        if (!input.matches("\\d+(\\.\\d+)?$")) {
            System.out.println("Time must be a number!");
            isValid = false;
        }
        return isValid;
    }
}
