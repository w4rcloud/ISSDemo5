package api;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ISSPositionAPI {

    private final HttpClient client = HttpClient.newHttpClient();
    private final HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://api.open-notify.org/iss-now.json")).build();

    public ISSPositionAPI() {
    }

    public Double getLatitude() {
        String ISSPositionJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        JSONObject json = new JSONObject(ISSPositionJSON);
        JSONObject coordinates = json.getJSONObject("iss_position");
        return Double.parseDouble(coordinates.get("latitude").toString());
    }

    public Double getLongitude() {
        String ISSPositionJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        JSONObject json = new JSONObject(ISSPositionJSON);
        JSONObject coordinates = json.getJSONObject("iss_position");
        return Double.parseDouble(coordinates.get("longitude").toString());
    }

    public long getTimestamp() {
        String ISSPositionJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        JSONObject json = new JSONObject(ISSPositionJSON);
        return json.getLong("timestamp");
    }


}
