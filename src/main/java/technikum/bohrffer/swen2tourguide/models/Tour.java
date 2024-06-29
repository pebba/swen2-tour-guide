package technikum.bohrffer.swen2tourguide.models;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tours")
public class Tour {

    @Id
    private String name;
    private String description;
    @Column(name = "departure")
    private String from;
    @Column(name = "destination")
    private String to;
    @Column(name = "transport")
    private String transportType;
    private double distance;
    @Column(name = "time")
    private double estimatedTime;
    @Transient
    private String routeImage;
    @Transient
    private final List<TourLog> tourLogs;

    public Tour(String name, String description, String from, String to, String transportType, double distance, double estimatedTime, String routeImage) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.routeImage = routeImage;
        this.tourLogs = new ArrayList<>();
    }


    public List<TourLog> getTourLogs() {
        return tourLogs;
    }

    public void addTourLog(TourLog log) {
        this.tourLogs.add(log);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getRouteImage() {
        return routeImage;
    }

    public void setRouteImage(String routeImage) {
        this.routeImage = routeImage;
    }

    @Override
    public String toString() {
        return name;
    }
}
