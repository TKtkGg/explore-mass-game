package com.example.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.status.StatusResponse;
import com.example.backend.service.StatusService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class StatusController {
    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/status")
    public StatusResponse status() {
        return this.statusService.status();
    }
}
