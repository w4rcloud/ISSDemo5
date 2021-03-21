package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "people_in_space")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Astronaut extends BaseEntity{

    @Column(name = "craft")
    private String craftName;

    @Column(name = "name")
    private String nameAndSurname;
}
