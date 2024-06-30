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


    @Transient
    private double fromLat;
    @Transient
    private double fromLng;
    @Transient
    private double toLat;
    @Transient
    private double toLng;
    public Tour() {
        this.tourLogs = new ArrayList<>();
    }

    public Tour(String name, String description, String from, String to, String transportType,
            double distance, double estimatedTime, double fromLat, double fromLng, double toLat, double toLng) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.fromLat = fromLat;
        this.fromLng = fromLng;
        this.toLat = toLat;
        this.toLng = toLng;
        this.tourLogs = new ArrayList<>();
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

    public double getFromLat() {
        return fromLat;
    }

    public void setFromLat(double fromLat) {
        this.fromLat = fromLat;
    }

    public double getFromLng() {
        return fromLng;
    }

    public void setFromLng(double fromLng) {
        this.fromLng = fromLng;
    }

    public double getToLat() {
        return toLat;
    }

    public void setToLat(double toLat) {
        this.toLat = toLat;
    }

    public double getToLng() {
        return toLng;
    }

    public void setToLng(double toLng) {
        this.toLng = toLng;
    }

    public List<TourLog> getTourLogs() {
        return tourLogs;
    }

    public void addTourLog(TourLog log) {
        this.tourLogs.add(log);
    }

    @Override
    public String toString() {
        return name;
    }
}
