package com.example.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.backend.service.treasure.TreasureService;
import com.example.backend.dto.TreasureResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class TreasureController {
    private TreasureService treasureService;

    public TreasureController(TreasureService treasureService) {
        this.treasureService = treasureService;
    }

    @GetMapping("/treasure")
    public TreasureResponse showTreasure(@RequestHeader("X-Session-Id") String sessionId) {
        return this.treasureService.showTreasure(sessionId);
    }

    @PostMapping("/treasure/open")
    public TreasureResponse openTreasure(@RequestHeader("X-Session-Id") String sessionId) {
        return this.treasureService.open(sessionId);
    }
}
