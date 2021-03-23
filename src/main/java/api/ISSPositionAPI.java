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
        // TODO: 23.03.2021 extract some of the below logic to class field / variable(or rather constant as the URL
        //  is always the same. NOTE - this might've been done initially but caused ISSPosition.longitude and
        //  .latitude to not get refreshed using this method)?
        String ISSPositionJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        JSONObject json = new JSONObject(ISSPositionJSON);
        JSONObject coordinates = json.getJSONObject("iss_position");
        return Double.parseDouble(coordinates.get("latitude").toString());
    }

    public Double getLongitude() {
        // TODO: 23.03.2021 extract some of the most below logic to class field / variable(or rather constant as ?
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
