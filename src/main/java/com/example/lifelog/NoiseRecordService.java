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
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoiseRecordService {

    private final NoiseRecordRepository noiseRecordRepository;
    private final NoiseRecordSoundTypeRepository noiseRecordSoundTypeRepository;

    public NoiseRecordService(
            NoiseRecordRepository noiseRecordRepository,
            NoiseRecordSoundTypeRepository noiseRecordSoundTypeRepository) {
        this.noiseRecordRepository = noiseRecordRepository;
        this.noiseRecordSoundTypeRepository = noiseRecordSoundTypeRepository;
    }

    /**
     * 対象年月の記録件数を検索する
     *
     * @param year  対象年
     * @param month 対象月
     * @return 対象年月の記録件数
     */
    public List<NoiseRecordCountResponse> getRecordCounts(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1);

        return noiseRecordRepository.getRecordCounts(startDate, endDate);
    }

    /**
     * 騒音記録を新規登録する
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

        // 検索条件を基に騒音記録を検索する
        Page<NoiseRecord> result = noiseRecordRepository.findAll(spec, pageable);

        // 検索結果からIDのみを抽出する
        List<Long> recordIds = result.getContent().stream()
                .map(NoiseRecord::getId)
                .toList();

        // IDを基に音の種類を検索する
        List<NoiseRecordSoundType> noiseRecordSoundTypes =
                recordIds.isEmpty()
                        ? List.of()
                        : noiseRecordSoundTypeRepository.getNoiseRecordSoundTypes(recordIds);

        // 音の種類のリストをID単位でグループ化する
        Map<Long, List<String>> soundTypeByRecordId =
                noiseRecordSoundTypes.stream()
                        .collect(Collectors.groupingBy(
                                soundType -> soundType.getNoiseRecord().getId(),
                                Collectors.mapping(
                                        NoiseRecordSoundType::getSoundType,
                                        Collectors.toList()
                                )
                        ));

        // 返却用DTOに値を設定する
        return result.map(record -> new NoiseRecordSearchResponse(
                record.getId(),
                record.getOccurrenceDate(),
                record.getOccurrenceTime(),
                record.getDurationCategory(),
                record.getOccurrenceSource(),
                record.getSoundLevel(),
                soundTypeByRecordId.getOrDefault(record.getId(), List.of()),
                record.getMemo()
        ));
    }
}
