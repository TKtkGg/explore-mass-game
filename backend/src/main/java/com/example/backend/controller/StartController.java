package com.example.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.StartService;
import com.example.backend.dto.StartRequest;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class StartController {
    private StartService startService;

    public StartController(StartService startService) {
        this.startService = startService;
    }

    @PostMapping("/start")
    public Map<String, String> start(@RequestBody StartRequest request) {
        this.startService.start(request.getName());
        return Map.of("message", "ok");
    }
}
