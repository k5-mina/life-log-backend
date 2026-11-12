package com.example.lifelog;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoiseRecordService {

    private final NoiseRecordRepository noiseRecordRepository;

    public NoiseRecordService(NoiseRecordRepository noiseRecordRepository) {
        this.noiseRecordRepository = noiseRecordRepository;
    }

    public List<Object[]> getRecordCounts(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1);

        return noiseRecordRepository.getRecordCounts(startDate, endDate);
    }

    public NoiseRecord register(NoiseRecord request) {
        NoiseRecord noiseRecord = new NoiseRecord(
                request.getOccurrenceDate(),
                request.getOccurrenceTime(),
                request.getDurationCategory(),
                request.getNoiseLevel(),
                request.getNoiseType(),
                request.getMemo());
        return noiseRecordRepository.save(noiseRecord);
    }

    public List<NoiseRecord> findAll() {
        return noiseRecordRepository.findAll();
    }

    public NoiseRecord findById(long id) {
        return noiseRecordRepository.findById(id).orElseThrow();
    }
}
