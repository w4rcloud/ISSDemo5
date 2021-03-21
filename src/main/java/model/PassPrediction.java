package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "iss_pass_predictions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassPrediction extends BaseEntity{

    @ManyToOne
    private City city;

    private long duration;

    @Column(name = "rise_time")
    private LocalDateTime riseTime;


}
