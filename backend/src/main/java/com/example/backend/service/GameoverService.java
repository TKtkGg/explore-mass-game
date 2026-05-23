package com.example.backend.service;

import com.example.backend.dto.Gameover.GameoverResponse;
import com.example.backend.service.gamestate.character.PlayerState;
import org.springframework.stereotype.Service;

import com.example.backend.entity.ScoreRecord;
import java.time.Instant;
import com.example.backend.repository.ScoreRecordRepository;

@Service
public class GameoverService {
    private PlayerState playerState;
    private ScoreRecordRepository scoreRecordRepository;

    public GameoverService(PlayerState playerState, ScoreRecordRepository scoreRecordRepository) {
        this.playerState = playerState;
        this.scoreRecordRepository = scoreRecordRepository;
    }

    public GameoverResponse gameover() {
        int score = playerState.getLevel() * 100 + playerState.getOwnedEquipmentList().size() * 100 + playerState.getOwnedCards().size() * 70;
        return new GameoverResponse(score);
    }

    public void registerScore(int score) {
        ScoreRecord scoreRecord = new ScoreRecord(playerState.getName(), score, playerState.getLevel(), Instant.now());
        scoreRecordRepository.save(scoreRecord);
    }
}
