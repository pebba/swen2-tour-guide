package technikum.bohrffer.swen2tourguide.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import technikum.bohrffer.swen2tourguide.models.Tour;
import technikum.bohrffer.swen2tourguide.models.TourList;

import java.net.URL;
import java.util.ResourceBundle;

public class TourAddController implements Initializable {
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
    private Button submitButton;
    private Stage stage;
    private Tour tour;

    private ListView<Tour> tourList;

    public TourAddController(ListView<Tour> tourList){
        this.tourList = tourList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        submitButton.setOnAction(event -> {
            System.out.println("Add Tour submit");
            System.out.println(name.getCharacters());
            System.out.println(distance.getCharacters());
            if (!validateForm()){
                System.out.println("Das Formular ist ungültig!");

            }
            else handleSubmitButton();
        });

         */

    }
    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void handleSubmitButton(){
        if (!validateForm()){
            System.out.println("Das Formular ist ungültig!");
        }
        else submit();
        System.out.println("handleSubmitButton");


    }

    public void submit(){
        Double dist = Double.parseDouble(distance.getText());
        Double timeDouble = Double.parseDouble(time.getText());
        String image = "https://via.placeholder.com/150";
        tour = new Tour(name.getText(), description.getText(), from.getText(), to.getText(), transport.getText(),
                dist, timeDouble, image);
        System.out.println(tour.getDistance());
        tourList.getItems().add(tour);
        stage.close();

    }

    public Tour getTour(){
        return tour;
    }

    private boolean validateForm(){
        boolean isValid = true;

        String tourName = name.getText();
        if (tourName == null || tourName.isEmpty()) {
            System.out.println("Ungültiger Benutzername!");
            isValid = false;
        }
        String desc = description.getText();
        String fromText = from.getText();
        String toText = to.getText();
        String transp = transport.getText();
        if (desc.isEmpty()  || fromText.isEmpty() || toText.isEmpty() || transp.isEmpty()){
            System.out.println("Bitte alle Felder befüllen!");
            isValid = false;
        }
        String input = distance.getText();
        if(!input.matches("\\d+(\\.\\d+)?$")){
            System.out.println("Distanz muss eine Zahl sein!");
            isValid = false;
        }
        input = time.getText();
        if(!input.matches("(\\d+(\\.\\d+)?$)")){
            System.out.println("Zeit muss eine Zahl sein!");
            isValid = false;
        }
        return isValid;
    }
}
