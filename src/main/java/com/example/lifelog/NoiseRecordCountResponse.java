package com.example.lifelog;

import java.time.LocalDate;

public record NoiseRecordCountResponse(
        LocalDate occurrenceDate,
        Long count
) {
}
