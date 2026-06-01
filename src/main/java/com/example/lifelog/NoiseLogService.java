package com.example.lifelog;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoiseLogService {

    private final NoiseLogRepository noiseLogRepository;

    public NoiseLogService(NoiseLogRepository noiseLogRepository) {
        this.noiseLogRepository = noiseLogRepository;
    }

    public NoiseLog register(int noiseLevel, String memo) {
        NoiseLog noiseLog = new NoiseLog(noiseLevel, memo);
        return noiseLogRepository.save(noiseLog);
    }

    public List<NoiseLog> findAll() {
        return noiseLogRepository.findAll();
    }

    public NoiseLog findById(long id) {
        return noiseLogRepository.findById(id).orElseThrow();
    }
}
