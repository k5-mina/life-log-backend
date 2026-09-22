package com.example.lifelog;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoiseLogService {

    private final NoiseLogRepository noiseLogRepository;

    public NoiseLogService(NoiseLogRepository noiseLogRepository) {
        this.noiseLogRepository = noiseLogRepository;
    }

    public NoiseLog register(NoiseLog request) {
        NoiseLog noiseLog = new NoiseLog(
                request.getNoiseLevel(),
                request.getOccurrenceDate(),
                request.getStartTime(),
                request.getEndTime(),
                request.getNoiseType(),
                request.getMemo());
        return noiseLogRepository.save(noiseLog);
    }

    public List<NoiseLog> findAll() {
        return noiseLogRepository.findAll();
    }

    public NoiseLog findById(long id) {
        return noiseLogRepository.findById(id).orElseThrow();
    }
}
