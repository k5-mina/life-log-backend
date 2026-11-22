package com.example.lifelog;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public List<NoiseRecordCountResponse> getRecordCounts(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1);

        return noiseRecordRepository.getRecordCounts(startDate, endDate);
    }

    /**
     * 騒音記録をDBに登録する
     *
     * @param request 新規登録画面から渡されたリクエストパラメータ
     */
    public void register(NoiseRecordCreateRequest request) {
        NoiseRecord noiseRecord = new NoiseRecord(
                request.occurrenceDate(),
                request.occurrenceTime(),
                request.durationCategory(),
                request.occurrenceSource(),
                request.soundLevel(),
                request.soundTypes(),
                request.memo());
        noiseRecordRepository.save(noiseRecord);
    }

    /**
     * 検索画面で指定した検索条件で検索する
     *
     * @param request 検索画面から渡されたリクエストパラメータ
     */
    public Page<NoiseRecordSearchResponse> searchNoiseRecord(
            NoiseRecordSearchCondition request,
            int page,
            int size) {
        Specification<NoiseRecord> spec = Specification.allOf(
                NoiseRecordSpecification.notDeleted(),
                request.occurrenceDateFrom() != null
                        ? NoiseRecordSpecification.occurrenceDateFrom(request.occurrenceDateFrom())
                        : Specification.unrestricted(),
                request.occurrenceDateTo() != null
                        ? NoiseRecordSpecification.occurrenceDateTo(request.occurrenceDateTo())
                        : Specification.unrestricted(),
                request.occurrenceTimeFrom() != null
                        ? NoiseRecordSpecification.occurrenceTimeFrom(request.occurrenceTimeFrom())
                        : Specification.unrestricted(),
                request.occurrenceTimeTo() != null
                        ? NoiseRecordSpecification.occurrenceTimeTo(request.occurrenceTimeTo())
                        : Specification.unrestricted(),
                request.durationCategories() != null && !request.durationCategories().isEmpty()
                        ? NoiseRecordSpecification.durationCategories(request.durationCategories())
                        : Specification.unrestricted(),
                request.occurrenceSources() != null && !request.occurrenceSources().isEmpty()
                        ? NoiseRecordSpecification.occurrenceSources(request.occurrenceSources())
                        : Specification.unrestricted(),
                request.noiseLevels() != null && !request.noiseLevels().isEmpty()
                        ? NoiseRecordSpecification.noiseLevels(request.noiseLevels())
                        : Specification.unrestricted(),
                request.soundTypes() != null && !request.soundTypes().isEmpty()
                        ? NoiseRecordSpecification.soundTypes(request.soundTypes())
                        : Specification.unrestricted()
        );

        if (page < 0) {
            throw new IllegalArgumentException("ページ番号が不正です");
        }
        if (size != 10 && size != 15 && size != 20) {
            throw new IllegalArgumentException("表示件数が不正です");
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(
                        Sort.Order.desc("occurrenceDate"),
                        Sort.Order.desc("occurrenceTime"),
                        Sort.Order.desc("id")
                ));

        Page<NoiseRecord> result = noiseRecordRepository.findAll(spec, pageable);

        return result.map(record -> new NoiseRecordSearchResponse(
                record.getId(),
                record.getOccurrenceDate(),
                record.getOccurrenceTime(),
                record.getDurationCategory(),
                record.getOccurrenceSource(),
                record.getSoundLevel(),
                record.getSoundTypes().stream()
                        .map(NoiseRecordSoundType::getSoundType)
                        .toList(),
                record.getMemo()
        ));
    }

    public NoiseRecord findById(long id) {
        return noiseRecordRepository.findById(id).orElseThrow();
    }
}
