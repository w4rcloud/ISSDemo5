package openNotify;

import lombok.Data;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Data
public class OpenNotifyConnection {
    public final static String ASTRONAUTS_IN_SPACE_URL = "http://api.open-notify.org/astros.json";
    public final static String CURRENT_LOCATION_URL = "http://api.open-notify.org/iss-now.json";
    private final static String OVERHEAD_PASS_PREDICTIONS_URL = "http://api.open-notify.org/iss-pass.json?lat=";


    public static String requestForPassPrediction(double latitude, double longitude) throws IOException {
        Request request = new Request.Builder()
                .url(OVERHEAD_PASS_PREDICTIONS_URL+latitude+ "&lon="+longitude)
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public static String requestForData(String urlAPI) throws IOException {
        Request request = new Request.Builder()
                .url(urlAPI)
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }



}
