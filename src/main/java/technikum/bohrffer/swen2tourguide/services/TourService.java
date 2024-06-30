package technikum.bohrffer.swen2tourguide.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;

public class TourService {

    private static final String API_URL = "https://api.openrouteservice.org";

    public void fetchRouteData(String from, String to, Consumer<String> callback) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/v2/directions/driving-car?start=" + from + "&end=" + to))
                .header("Authorization", "5b3ce3597851110001cf62488b716f6d578f44b09ead973ff4c922b6")
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(callback)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }
}
