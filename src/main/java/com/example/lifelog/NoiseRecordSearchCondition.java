package com.example.lifelog;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record NoiseRecordSearchCondition(
        LocalDate occurrenceDateFrom,
        LocalDate occurrenceDateTo,
        LocalTime occurrenceTimeFrom,
        LocalTime occurrenceTimeTo,
        List<Short> durationCategories,
        List<String> occurrenceSources,
        List<Integer> soundLevels,
        List<String> soundTypes
) {
}
