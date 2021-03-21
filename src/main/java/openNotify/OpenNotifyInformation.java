package openNotify;

import model.Astronaut;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenNotifyInformation {

    public static List<Astronaut> getPeopleInSpace() throws IOException {
        String jsonAsString = OpenNotifyConnection.requestForData(OpenNotifyConnection.ASTRONAUTS_IN_SPACE_URL);
        int howManyPeople = new JSONObject(jsonAsString).getInt("number");
        ArrayList<Astronaut> astronauts = new ArrayList<>();
        for (int i = 0;i<howManyPeople;i++){
            Astronaut tempAstronaut = new Astronaut();
            JSONObject astronautObjectJSON = new JSONObject(jsonAsString).getJSONArray("people").getJSONObject(i);
            tempAstronaut.setCraftName(astronautObjectJSON.getString("craft"));
            tempAstronaut.setNameAndSurname(astronautObjectJSON.getString("name"));
            astronauts.add(tempAstronaut);
        }
        return astronauts;
    }

    public static void getCurrentSpeed() throws IOException {
        String jsonAsStringFirstResponse = OpenNotifyConnection.requestForData(OpenNotifyConnection.CURRENT_LOCATION_URL);
        String jsonAsStringSecondResponse = OpenNotifyConnection.requestForData(OpenNotifyConnection.CURRENT_LOCATION_URL);




       /* Timestamp ts = new Timestamp(System.currentTimeMillis());
        LocalDateTime date = ts.toLocalDateTime();
        System.out.println(date);*/
    }


    public static float distanceFromInMeters(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);
        return dist;
    }
}
