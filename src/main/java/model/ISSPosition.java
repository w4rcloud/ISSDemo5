package model;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "iss_positions")
@ToString

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
        // * 1000 to convert milliseconds to seconds - otherwise year was 1970
        this.timestamp = new Date(timestamp * 1000);
        return this;
    }

}