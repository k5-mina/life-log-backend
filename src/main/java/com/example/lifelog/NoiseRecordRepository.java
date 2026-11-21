package com.example.lifelog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NoiseRecordRepository extends JpaRepository<NoiseRecord, Long>, JpaSpecificationExecutor<NoiseRecord> {

    @Query("""
            SELECT new com.example.lifelog.NoiseRecordCountResponse(
                n.occurrenceDate,
                COUNT(n)
            )
            FROM NoiseRecord n
            WHERE n.occurrenceDate >= :startDate
            AND n.occurrenceDate < :endDate
            AND n.deletedAt IS NULL
            GROUP BY n.occurrenceDate
            """)
    List<NoiseRecordCountResponse> getRecordCounts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
