package api;

import org.json.JSONArray;
import org.json.JSONObject;
import repository.PassTimeAndDuration;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ISSPassAPI {

    private final HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;

    public ISSPassAPI() {
    }

    public PassTimeAndDuration getPassesAndDurations(Double latitude, Double longitude) {
        Long[] passTimes = getPassTimes(latitude, longitude);
        Integer[] durations = getDurations(latitude, longitude);
        return new PassTimeAndDuration(passTimes, durations);
    }


    // TODO: 23.03.2021 add validation for arguments outside of the proper range - should it be here or within main?
    public Long[] getPassTimes(Double latitude, Double longitude) {
        // ',' has to be replaced with '.' as it gets converted somewhere within the logic
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://api.open-notify.org/iss-pass.json?lat=%f&lon=%f",
                        latitude, longitude).replace(",", "."))).build();

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

    // TODO: 23.03.2021 add validation for arguments outside of the proper range - should it be here or within main?
    public Integer[] getDurations(Double latitude, Double longitude) {
        // ',' has to be replaced with '.' as it gets converted somewhere within the logic
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://api.open-notify.org/iss-pass.json?lat=%f&lon=%f",
                        latitude, longitude).replace(",", "."))).build();

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

    private void validate(Double latitude, Double longitude) {
        if ((latitude < -80 || latitude > 80) && (longitude < -180 || longitude > 180)) {
            throw new IllegalArgumentException("Valid ranges are: latitude: -80..80 / longitude: -180..180");
        }
    }
}
