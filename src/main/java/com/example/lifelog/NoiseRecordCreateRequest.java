package com.example.lifelog;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record NoiseRecordCreateRequest(
        @NotNull
        LocalDate occurrenceDate,
        LocalTime occurrenceTime,
        Short durationCategory,
        @NotNull
        String occurrenceSource,
        @NotNull
        int soundLevel,
        List<String> soundTypes,
        String memo
) {
}
