package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseEntity{

    private String name;

    private double longitude;

    private double latitude;

    @OneToMany(mappedBy = "city")
    private List<PassPrediction> passPredictions;

}
