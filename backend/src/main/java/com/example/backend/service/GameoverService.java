package com.example.backend.service;

import com.example.backend.dto.Gameover.GameoverResponse;
import com.example.backend.service.gamestate.session.GameSession;
import org.springframework.stereotype.Service;

import com.example.backend.entity.ScoreRecord;
import java.time.Instant;
import com.example.backend.repository.ScoreRecordRepository;

@Service
public class GameoverService {
    private GameSessionManager gameSessionManager;
    private ScoreRecordRepository scoreRecordRepository;

    public GameoverService(GameSessionManager gameSessionManager, ScoreRecordRepository scoreRecordRepository) {
        this.gameSessionManager = gameSessionManager;
        this.scoreRecordRepository = scoreRecordRepository;
    }

    private int calculateScore(GameSession gameSession) {
        return gameSession.getPlayerState().getLevel() * 100 
                + gameSession.getPlayerState().getOwnedEquipmentList().size() * 100 
                + gameSession.getPlayerState().getOwnedCards().size() * 70
                + gameSession.getPlayerState().getMaxHp() * 3
                + gameSession.getPlayerState().getAtk() * 10
                + gameSession.getPlayerState().getDef() * 10
                + gameSession.getPlayerState().getSpd() * 10;
    }

    public GameoverResponse gameover(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);
        int score = calculateScore(gameSession);
        return new GameoverResponse(score);
    }

    public void registerScore(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);

        ScoreRecord scoreRecord = new ScoreRecord(
                gameSession.getPlayerState().getName(),
                calculateScore(gameSession),
                gameSession.getPlayerState().getLevel(),
                Instant.now()
        );
    
        scoreRecordRepository.save(scoreRecord);
        
    }
}
