package com.example.lifelog;

import jakarta.persistence.*;

@Entity
@Table(name = "noise_record_sound_type")
@IdClass(NoiseRecordSoundTypeId.class)
public class NoiseRecordSoundType {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noise_record_id", nullable = false)
    private NoiseRecord noiseRecord;

    @Id
    @Column(name = "sound_type", nullable = false)
    private String soundType;

    protected NoiseRecordSoundType() {}

    public NoiseRecordSoundType(NoiseRecord noiseRecord, String soundType) {
        this.noiseRecord = noiseRecord;
        this.soundType = soundType;
    }

    public NoiseRecord getNoiseRecord() {
        return noiseRecord;
    }

    public void setNoiseRecord(NoiseRecord noiseRecord) {
        this.noiseRecord = noiseRecord;
    }

    public String getSoundType() {
        return soundType;
    }

    public void setSoundType(String soundType) {
        this.soundType = soundType;
    }
}
