package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.RankingService;
import com.example.backend.dto.RankingResponse;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class RankingController {
    private RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/ranking")
    public List<RankingResponse> getRanking() {
        return this.rankingService.getRanking();
    }
}
