package api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ISSPassAPI {

    private final HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;

    public ISSPassAPI(String latitude, String longitude) {
        this.request = HttpRequest.newBuilder().uri(URI.create(String.format("http://api.open-notify.org/iss-pass.json?lat=%s&lon=%s",
                latitude, longitude))).build();
    }

    public Long[] getPassTimes() {
        //new Array for storing pass times
        Long[] passTimesAsLongArray = new Long[5];

        // fetching API as String
        String ISSPassTimesJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        // fetching 'response' JSONArray
        JSONArray ISSPassTimesJSONArray = new JSONObject(ISSPassTimesJSON).getJSONArray("response");

        // fetching 'pass time / rise time' from each JSONObject and storing it in the Array initialized at method's
        // beginning
        for (int i = 0; i < ISSPassTimesJSONArray.length(); i++) {
            passTimesAsLongArray[i] = ISSPassTimesJSONArray.getJSONObject(i).getLong("risetime");
        }
        return passTimesAsLongArray;
    }

    public Integer[] getDurations() {
        //new Array for storing duration
        Integer[] passTimesAsIntegerArray = new Integer[5];

        // fetching API as String
        String ISSPassTimesJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        // fetching 'response' JSONArray
        JSONArray ISSPassTimesJSONArray = new JSONObject(ISSPassTimesJSON).getJSONArray("response");

        // fetching 'duration' from each JSONObject and storing it in the Array initialized at method's beginning
        for (int i = 0; i < ISSPassTimesJSONArray.length(); i++) {
            passTimesAsIntegerArray[i] = ISSPassTimesJSONArray.getJSONObject(i).getInt("duration");
        }
        return passTimesAsIntegerArray;
    }

}
