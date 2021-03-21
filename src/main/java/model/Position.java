package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "iss_positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Position extends BaseEntity {
    private LocalDateTime time;
    private double longitude;
    private double latitude;

}
