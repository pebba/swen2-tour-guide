package technikum.bohrffer.swen2tourguide.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import technikum.bohrffer.swen2tourguide.models.Tour;

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
    private ImageView image;
    private ListView<Tour> tourList;
    private Stage stage;
    private Tour tour;
    private Boolean elementSelected;

    /*
    public TourModifyController(String name, String description, String from, String to, String transport, Double distance, Double time){

    } */
    public TourModifyController(ListView<Tour> tourList){ //ListView<Tour> tourList, Stage stage, Tour tour
        this.tourList = tourList;
        /*
        this.tourList = tourList;
        this.stage = stage;
        this.tour = tour;
        */
    }

    public void handle(){
        tourList.setOnMouseClicked(event -> {
            Tour selectedItem = tourList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                elementSelected = true;
                Tour selectedObject = selectedItem;
                System.out.println("Der Benutzer hat auf das Element geklickt: " + selectedObject.getName());

                openWindow(selectedObject);
            }
        });

    }

    public void openWindow(Tour tour){
        this.tour = tour;
        name = new TextField();
        distance = new TextField();
        description = new TextField();
        from = new TextField();
        to = new TextField();
        transport = new TextField();
        time = new TextField();
        image = new ImageView(tour.getRouteImage());

        name.setEditable(true);
        distance.setEditable(true);

        description.setText(tour.getDescription());
        from.setText(tour.getFrom());
        to.setText(tour.getTo());
        transport.setText(tour.getTransportType());
        time.setText(String.valueOf(tour.getEstimatedTime()));
        name.setText(tour.getName());
        distance.setText(String.valueOf(tour.getDistance()));

        Label nameLabel = new Label("Name:");
        Label distanceLabel = new Label("Distanz:");
        Label toLabel = new Label("Nach:");
        Label fromLabel = new Label("Von:");
        Label transportLabel = new Label("Transportart:");
        Label timeLabel = new Label("Dauer:");
        Label descriptionLabel = new Label("Beschreibung:");
        Label imageLabel = new Label("Karte");


        Stage detailsStage = new Stage();
        VBox detailsRoot = new VBox();
        detailsRoot.getChildren().addAll(nameLabel, name, descriptionLabel, description,
                fromLabel, from, toLabel, to, transportLabel, transport, distanceLabel,distance,
                timeLabel, time, imageLabel, image);

        Button submitButton = new Button("Submit");
        detailsRoot.getChildren().add(submitButton);
        submitButton.setOnAction(event -> {
            if (!validateForm()){
                System.out.println("Das Formular ist ungültig!");
            }
            else submit();
            System.out.println("handleSubmitButton");
        });

        //detailsRoot.getChildren().add(new Button("Schließen"));
        Scene detailsScene = new Scene(detailsRoot, 500, 400);
        detailsStage.setScene(detailsScene);
        detailsStage.setTitle(tour.getName() + " Details");
        detailsStage.show();
        this.stage = detailsStage;
        System.out.println(tour.getName());

    }

    public void handleSubmitButton(){
        if (!validateForm()){
            System.out.println("Das Formular ist ungültig!");
        }
        else submit();
        System.out.println("handleSubmitButton");


    }

    public void submit(){
        //Tour Werte modifizieren?
        Double dist = Double.parseDouble(distance.getText());
        Double timeDouble = Double.parseDouble(time.getText());
        String image = "https://via.placeholder.com/150";
        //tourList.
        tour.setName(name.getText());
        tour.setDescription(description.getText());
        tour.setTo(to.getText());
        tour.setFrom(from.getText());
        tour.setTransportType(transport.getText());
        tour.setDistance(dist);
        tour.setEstimatedTime(timeDouble);
        stage.close();

        /*
        Tour tour = new Tour(name.getText(), description.getText(), from.getText(), to.getText(), transport.getText(),
                dist, timeDouble, image);
        System.out.println(tour.getDistance());
        tourList.getItems().add(tour);
        stage.close();

         */

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
