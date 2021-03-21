package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@JsonIgnoreProperties
@Getter
@ToString
@Setter

public class ISSPosition extends BaseEntity {

    long timestamp;
    String latitude;
    String longitude;

    public ISSPosition() {
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ISSPosition setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public ISSPosition setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }
}