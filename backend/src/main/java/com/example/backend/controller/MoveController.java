package com.example.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.MoveRequest;
import com.example.backend.dto.MoveResponse;
import com.example.backend.service.MoveService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class MoveController {
    private MoveService moveService;

    public MoveController(MoveService moveService) {
        this.moveService = moveService;
    }

    @PostMapping("/move")
    public MoveResponse move(@RequestBody MoveRequest request) {
        return this.moveService.move(request);
    }

    @PostMapping("/reset")
    public MoveResponse reset() {
        return this.moveService.reset();
    }
}