package com.example.lifelog;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noise-log")
public class NoiseLogController {

    private final NoiseLogService noiseLogService;

    public NoiseLogController(NoiseLogService noiseLogService) {
        this.noiseLogService = noiseLogService;
    }

    @GetMapping
    public NoiseLog register(@RequestParam int noiseLevel, @RequestParam String memo) {
        return noiseLogService.register(noiseLevel, memo);
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
