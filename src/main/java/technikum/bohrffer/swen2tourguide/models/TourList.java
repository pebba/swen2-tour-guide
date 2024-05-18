package technikum.bohrffer.swen2tourguide.models;

import javafx.scene.control.ListView;

public class TourList {

    private ListView<Tour> tourList;

    public ListView<Tour> getTourList(){
        return tourList;
    }

    public void addTour(Tour tour){
        tourList.getItems().add(tour);
    }
}
