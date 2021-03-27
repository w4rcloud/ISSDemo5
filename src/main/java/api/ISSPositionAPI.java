package api;

import lombok.Getter;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Getter
public class ISSPositionAPI {

    private final HttpClient client = HttpClient.newHttpClient();
    private final HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://api.open-notify.org/iss-now.json")).build();
    private String fetchedAPI;
    private JSONObject jsonObject;

    public ISSPositionAPI() {
    }

    public Double fetchLatitude() {
        fetchedAPI = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        jsonObject = new JSONObject(fetchedAPI);
        JSONObject coordinates = jsonObject.getJSONObject("iss_position");
        return Double.parseDouble(coordinates.get("latitude").toString());
    }

    public Double fetchLongitude() {
        fetchedAPI = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        jsonObject = new JSONObject(fetchedAPI);
        JSONObject coordinates = jsonObject.getJSONObject("iss_position");
        return Double.parseDouble(coordinates.get("longitude").toString());
    }

    public long fetchTimestamp() {
        fetchedAPI = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        jsonObject = new JSONObject(fetchedAPI);
        return jsonObject.getLong("timestamp");
    }
}
