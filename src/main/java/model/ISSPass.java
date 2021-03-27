package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import repository.PassTimeAndDuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "iss_passes")
@Setter
@Getter
@NoArgsConstructor
@ToString

public class ISSPass extends BaseEntity {

    /*
    this API return 5 nearest instances of ISS pass times and durations over a specified coordinates
    Thus, 2 x 5 fields are created to present data in a table as mySQL doesn't support arrays
    todo: ask Lukasz for alternatives + how to specify order of columns
     */

    // TODO: 23.03.2021 change Type of below fields / vars to Duration / Date
    @Column(name = "duration_1")
    private Integer duration1;

    @Column(name = "pass_time1")
    private Long passTime1;

    @Column(name = "duration_2")
    private Integer duration2;

    @Column(name = "pass_time2")
    private Long passTime2;

    @Column(name = "duration_3")
    private Integer duration3;

    @Column(name = "pass_time3")
    private Long passTime3;

    @Column(name = "duration_4")
    private Integer duration4;

    @Column(name = "pass_time4")
    private Long passTime4;

    @Column(name = "duration_5")
    private Integer duration5;

    @Column(name = "pass_time5")
    private Long passTime5;

    public ISSPass(PassTimeAndDuration passTimeAndDuration) {
        this.duration1 = passTimeAndDuration.getDurations()[0];
        this.duration2 = passTimeAndDuration.getDurations()[1];
        this.duration3 = passTimeAndDuration.getDurations()[2];
        this.duration4 = passTimeAndDuration.getDurations()[3];
        this.duration5 = passTimeAndDuration.getDurations()[4];
        this.passTime1 = passTimeAndDuration.getPassTimes()[0];
        this.passTime2 = passTimeAndDuration.getPassTimes()[1];
        this.passTime3 = passTimeAndDuration.getPassTimes()[2];
        this.passTime4 = passTimeAndDuration.getPassTimes()[3];
        this.passTime5 = passTimeAndDuration.getPassTimes()[4];

    }

}
