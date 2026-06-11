package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.repository.ScoreRecordRepository;
import com.example.backend.dto.RankingResponse;
import com.example.backend.entity.ScoreRecord;

import java.util.List;
import java.util.ArrayList;

@Service
public class RankingService {
    private ScoreRecordRepository scoreRecordRepository;
    public RankingService(ScoreRecordRepository scoreRecordRepository) {
        this.scoreRecordRepository = scoreRecordRepository;
    }

    public List<RankingResponse> getRanking() {
        List<ScoreRecord> scoreRecords = scoreRecordRepository.findTop100ByOrderByScoreDesc();
        List<RankingResponse> result = new ArrayList<>();
        for(ScoreRecord record : scoreRecords) {
            result.add(new RankingResponse(
                record.getPlayerName(), 
                record.getScore(), 
                record.getLevel(), 
                record.getPlayedAt()));
        }
        return result;
    }
}
