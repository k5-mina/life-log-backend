package com.example.lifelog;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
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

    public void register(NoiseRecordCreateRequest request) {
        NoiseRecord noiseRecord = new NoiseRecord(
                request.occurrenceDate(),
                request.occurrenceTime(),
                request.durationCategory(),
                request.occurrenceSource(),
                request.soundLevel(),
                request.memo());
        for (String soundType : request.soundTypes()) {
            noiseRecord.addSoundType(soundType);
        }
        noiseRecordRepository.save(noiseRecord);
    }

    public List<NoiseRecord> findAll() {
        return noiseRecordRepository.findAll();
    }

    public NoiseRecord findById(long id) {
        return noiseRecordRepository.findById(id).orElseThrow();
    }
}
