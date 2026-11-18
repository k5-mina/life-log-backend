package com.example.lifelog;

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
    public void register(@RequestBody NoiseRecordCreateRequest request) {
        noiseRecordService.register(request);
    }

    @GetMapping("/all")
    public List<NoiseRecord> findAll() {
        return noiseRecordService.findAll();
    }

    @GetMapping("/{id}")
    public NoiseRecord findById(@PathVariable long id) {
        return noiseRecordService.findById(id);
    }
}
