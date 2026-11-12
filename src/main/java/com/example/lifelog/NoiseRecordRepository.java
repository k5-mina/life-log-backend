package com.example.lifelog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NoiseRecordRepository extends JpaRepository<NoiseRecord, Long> {

    @Query("""
            SELECT n.occurrenceDate, COUNT(n)
            FROM NoiseRecord n
            WHERE n.occurrenceDate >= :startDate
            AND n.occurrenceDate < :endDate
            AND n.deletedAt IS NULL
            GROUP BY n.occurrenceDate
            """)
    List<Object[]> getRecordCounts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
