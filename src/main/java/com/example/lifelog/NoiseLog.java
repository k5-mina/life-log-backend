package com.example.lifelog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NoiseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int noiseLevel;

    private String memo;

    protected NoiseLog() {
    }

    public NoiseLog(int noiseLevel, String memo) {
        this.noiseLevel = noiseLevel;
        this.memo = memo;
    }

    public long getId() {
        return id;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public String getMemo() {
        return memo;
    }
}
