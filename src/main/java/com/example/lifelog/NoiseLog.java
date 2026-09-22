package com.example.lifelog;

import jakarta.persistence.*;

@Entity
@Table(name = "noise_log")
public class NoiseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name="NOISE_LEVEL")
    private int noiseLevel;

    @Column(name="OCCURRENCE_DATE")
    private String occurrenceDate;

    @Column(name="START_TIME")
    private String startTime;

    @Column(name="END_TIME")
    private String endTime;

    @Column(name="NOISE_TYPE")
    private String noiseType;

    @Column(name="MEMO")
    private String memo;

    protected NoiseLog() {
    }

    public NoiseLog(
            int noiseLevel,
            String occurrenceDate,
            String startTime,
            String endTime,
            String noiseType,
            String memo) {
        this.noiseLevel = noiseLevel;
        this.occurrenceDate = occurrenceDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.noiseType = noiseType;
        this.memo = memo;
    }

    public long getId() {
        return id;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public String getOccurrenceDate() {
        return occurrenceDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getNoiseType() {
        return noiseType;
    }

    public String getMemo() {
        return memo;
    }
}
