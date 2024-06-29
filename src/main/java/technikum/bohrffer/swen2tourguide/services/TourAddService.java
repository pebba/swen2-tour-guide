package technikum.bohrffer.swen2tourguide.services;

import technikum.bohrffer.swen2tourguide.models.Tour;
import technikum.bohrffer.swen2tourguide.repositories.TourAddRepository;

public class TourAddService {
    private TourAddRepository tourAddRepository = new TourAddRepository();

    public void AddTour(Tour tour){
        tourAddRepository.insertTour(tour);
    }
}
