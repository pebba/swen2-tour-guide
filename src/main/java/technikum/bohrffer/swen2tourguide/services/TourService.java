package technikum.bohrffer.swen2tourguide.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

public class TourService {

    private static final String API_URL = "https://api.openrouteservice.org";

    public void fetchCoordinates(String location, Consumer<double[]> callback) {
        HttpClient client = HttpClient.newHttpClient();
        String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/geocode/search?api_key=5b3ce3597851110001cf62488b716f6d578f44b09ead973ff4c922b6&text=" + encodedLocation))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    double[] coords = parseCoordinates(response);
                    callback.accept(coords);
                })
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }

    private double[] parseCoordinates(String response) {
        JSONObject json = new JSONObject(response);
        JSONArray features = json.getJSONArray("features");
        JSONObject geometry = features.getJSONObject(0).getJSONObject("geometry");
        JSONArray coordinates = geometry.getJSONArray("coordinates");
        double lng = coordinates.getDouble(0);
        double lat = coordinates.getDouble(1);
        return new double[]{lat, lng};
    }
}
