package com.example.lifelog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoiseRecordSoundTypeRepository extends JpaRepository<NoiseRecordSoundType, NoiseRecordSoundTypeId> {

    @Query("""
            SELECT s
            FROM NoiseRecordSoundType s
            WHERE s.noiseRecord.id IN :recordIds
            """)
    List<NoiseRecordSoundType> getNoiseRecordSoundTypes(
            @Param("recordIds") List<Long> recordIds
    );
}
