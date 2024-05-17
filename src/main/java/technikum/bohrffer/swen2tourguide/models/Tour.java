package technikum.bohrffer.swen2tourguide.models;

public class Tour {
    private String name;
    private String description;
    private String from;
    private String to;
    private String transportType;
    private double distance;
    private double estimatedTime;
    private String routeImage;

    public Tour(String name, String description, String from, String to, String transportType, double distance, double estimatedTime, String routeImage) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.routeImage = routeImage;
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
