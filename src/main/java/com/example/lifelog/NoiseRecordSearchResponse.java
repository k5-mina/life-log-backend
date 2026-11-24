package com.example.lifelog;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record NoiseRecordSearchResponse(
        long id,
        LocalDate occurrenceDate,
        LocalTime occurrenceTime,
        Short durationCategory,
        String occurrenceSource,
        int soundLevel,
        List<String> soundTypes,
        String memo
) {
}
