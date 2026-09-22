package com.example.lifelog;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noise-log")
@CrossOrigin(origins = "http://localhost:4200")
public class NoiseLogController {

    private final NoiseLogService noiseLogService;

    public NoiseLogController(NoiseLogService noiseLogService) {
        this.noiseLogService = noiseLogService;
    }

    @PostMapping
    public NoiseLog register(@RequestBody NoiseLog request) {
        return noiseLogService.register(request);
    }

    @GetMapping("/all")
    public List<NoiseLog> findAll() {
        return noiseLogService.findAll();
    }

    @GetMapping("/{id}")
    public NoiseLog findById(@PathVariable long id) {
        return noiseLogService.findById(id);
    }
}
