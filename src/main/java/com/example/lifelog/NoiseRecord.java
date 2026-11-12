package com.example.lifelog;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "noise_records")
public class NoiseRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "occurrence_date", nullable = false)
    private LocalDate occurrenceDate;

    @Column(name = "occurrence_time")
    private LocalTime occurrenceTime;

    @Column(name = "duration_category")
    private Short durationCategory;

    @Column(name = "noise_level", nullable = false)
    private int noiseLevel;

    @Column(name = "noise_type", nullable = false)
    private String noiseType;

    @Column(name = "memo")
    private String memo;

    protected NoiseRecord() {
    }

    public NoiseRecord(
            LocalDate occurrenceDate,
            LocalTime occurrenceTime,
            Short durationCategory,
            int noiseLevel,
            String noiseType,
            String memo) {
        this.occurrenceDate = occurrenceDate;
        this.occurrenceTime = occurrenceTime;
        this.durationCategory = durationCategory;
        this.noiseLevel = noiseLevel;
        this.noiseType = noiseType;
        this.memo = memo;
    }

    public long getId() {
        return id;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public LocalDate getOccurrenceDate() {
        return occurrenceDate;
    }

    public LocalTime getOccurrenceTime() {
        return occurrenceTime;
    }

    public Short getDurationCategory() {
        return durationCategory;
    }

    public String getNoiseType() {
        return noiseType;
    }

    public String getMemo() {
        return memo;
    }
}
