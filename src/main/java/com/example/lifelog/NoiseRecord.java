package com.example.lifelog;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "occurrence_source", nullable = false)
    private String occurrenceSource;

    @Column(name = "sound_level", nullable = false)
    private int soundLevel;

    @OneToMany(mappedBy = "noiseRecord", cascade = CascadeType.ALL)
    private List<NoiseRecordSoundType> soundTypes = new ArrayList<>();

    @Column(name = "memo")
    private String memo;

    protected NoiseRecord() {
    }

    public NoiseRecord(
            LocalDate occurrenceDate,
            LocalTime occurrenceTime,
            Short durationCategory,
            String occurrenceSource,
            int soundLevel,
            List<String> soundTypes,
            String memo) {
        this.occurrenceDate = occurrenceDate;
        this.occurrenceTime = occurrenceTime;
        this.durationCategory = durationCategory;
        this.occurrenceSource = occurrenceSource;
        this.soundLevel = soundLevel;
        this.memo = memo;

        for (String soundType : soundTypes) {
            this.addSoundType(soundType);
        }
    }

    private void addSoundType(String soundType) {
        NoiseRecordSoundType child = new NoiseRecordSoundType(this, soundType);
        soundTypes.add(child);
    }

    public long getId() {
        return id;
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

    public String getOccurrenceSource() {
        return occurrenceSource;
    }

    public int getSoundLevel() {
        return soundLevel;
    }

    public List<NoiseRecordSoundType> getSoundTypes() {
        return soundTypes;
    }

    public String getMemo() {
        return memo;
    }
}
