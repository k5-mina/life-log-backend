package com.example.lifelog;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noise-record")
@CrossOrigin(origins = "${app.cors.allowed-origin}")
public class NoiseRecordController {

    private final NoiseRecordService noiseRecordService;

    public NoiseRecordController(NoiseRecordService noiseRecordService) {
        this.noiseRecordService = noiseRecordService;
    }

    @GetMapping("/counts")
    public List<NoiseRecordCountResponse> getRecordCounts(
            @RequestParam int year,
            @RequestParam int month) {
        return noiseRecordService.getRecordCounts(year, month);
    }

    @PostMapping
    public void register(@Valid @RequestBody NoiseRecordCreateRequest request) {
        noiseRecordService.register(request);
    }

    @PostMapping("/search")
    public Page<NoiseRecordSearchResponse> search(
            @RequestBody NoiseRecordSearchCondition request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return noiseRecordService.searchNoiseRecord(request, page, size);
    }
}
