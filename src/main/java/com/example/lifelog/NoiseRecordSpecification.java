package com.example.lifelog;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class NoiseRecordSpecification {

    public static Specification<NoiseRecord> occurrenceDateFrom(LocalDate date) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(
                        root.get("occurrenceDate"),
                        date);
    }

    public static Specification<NoiseRecord> occurrenceDateTo(LocalDate date) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(
                        root.get("occurrenceDate"),
                        date);
    }

    public static Specification<NoiseRecord> occurrenceTimeFrom(LocalTime time) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(
                        root.get("occurrenceTime"),
                        time);
    }

    public static Specification<NoiseRecord> occurrenceTimeTo(LocalTime time) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(
                        root.get("occurrenceTime"),
                        time);
    }

    public static Specification<NoiseRecord> durationCategories(List<Short> categories) {
        return (root, query, cb) ->
                root.get("durationCategory").in(categories);
    }

    public static Specification<NoiseRecord> occurrenceSources(List<String> sources) {
        return (root, query, cb) ->
                root.get("occurrenceSource").in(sources);
    }

    public static Specification<NoiseRecord> noiseLevels(List<Integer> levels) {
        return (root, query, cb) ->
                root.get("soundLevel").in(levels);
    }

    public static Specification<NoiseRecord> soundTypes(List<String> types) {
        return (root, query, cb) -> {
            Subquery<Integer> subquery = query.subquery(Integer.class);

            Root<NoiseRecordSoundType> soundTypeRoot = subquery.from(NoiseRecordSoundType.class);

            subquery.select(cb.literal(1))
                    .where(
                            cb.equal(
                                    soundTypeRoot.get("noiseRecord"),
                                    root
                            ),
                            soundTypeRoot.get("soundType").in(types)
                    );
            return cb.exists(subquery);
        };
    }

    public static Specification<NoiseRecord> notDeleted() {
        return (root, query, cb) ->
                cb.isNull(root.get("deletedAt"));
    }
}
