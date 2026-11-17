package com.example.lifelog;

import java.io.Serializable;
import java.util.Objects;

public class NoiseRecordSoundTypeId implements Serializable {

    private Long noiseRecord;
    private String soundType;

    public NoiseRecordSoundTypeId() {}

    public NoiseRecordSoundTypeId(Long noiseRecord, String soundType) {
        this.noiseRecord = noiseRecord;
        this.soundType = soundType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NoiseRecordSoundTypeId that = (NoiseRecordSoundTypeId) o;
        return Objects.equals(noiseRecord, that.noiseRecord) && Objects.equals(soundType, that.soundType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noiseRecord, soundType);
    }
}
