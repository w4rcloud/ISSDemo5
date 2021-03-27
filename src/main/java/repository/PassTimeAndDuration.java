package repository;

public class PassTimeAndDuration {

    private Long[] passTimes;
    private Integer[] durations;

    public PassTimeAndDuration(Long[] passTimes, Integer[] durations) {
        this.passTimes = passTimes;
        this.durations = durations;
    }

    public Long[] getPassTimes(){
        return this. passTimes;
    }
    public Integer[] getDurations(){
        return this. durations;
    }

}
