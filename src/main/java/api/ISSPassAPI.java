package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ISSPassAPI {

    private HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://api.open-notify.org/iss-now.json")).build();
    private String ISSPositionJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .join();
}
