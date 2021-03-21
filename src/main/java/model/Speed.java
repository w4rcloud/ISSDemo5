package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "current_speeds")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Speed extends BaseEntity{

    private LocalDateTime time;

    private double speed;


}
