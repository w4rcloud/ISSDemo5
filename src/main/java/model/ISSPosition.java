package model;

import javax.persistence.Entity;
import java.util.Date;

@Entity

public class ISSPosition extends BaseEntity {

    private Date timestamp;
    private Double latitude;
    private Double longitude;

    public ISSPosition() {
    }

    public ISSPosition setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public ISSPosition setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public ISSPosition setTimestamp(long timestamp) {
        this.timestamp = new Date(timestamp * 1000); // * 1000 to convert milliseconds to seconds - otherwise year
        // was 1970
        return this;
    }

}