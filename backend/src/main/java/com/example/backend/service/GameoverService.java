package com.example.backend.service;

import com.example.backend.dto.Gameover.GameoverResponse;
import com.example.backend.service.gamestate.session.GameSession;
import org.springframework.stereotype.Service;

import com.example.backend.entity.ScoreRecord;
import java.time.Instant;
import com.example.backend.repository.ScoreRecordRepository;
import com.example.backend.service.gamestate.card.CardState;

@Service
public class GameoverService {
    private GameSessionManager gameSessionManager;
    private ScoreRecordRepository scoreRecordRepository;

    public GameoverService(GameSessionManager gameSessionManager, ScoreRecordRepository scoreRecordRepository) {
        this.gameSessionManager = gameSessionManager;
        this.scoreRecordRepository = scoreRecordRepository;
    }

    private int calculateScore(GameSession gameSession) {
        int score = gameSession.getPlayerState().getLevel() * 100 
                    + gameSession.getPlayerState().getOwnedEquipmentList().size() * 100 
                    + gameSession.getPlayerState().getOwnedCards().size() * 70
                    + gameSession.getPlayerState().getMaxHp() * 3
                    + gameSession.getPlayerState().getAtk() * 10
                    + gameSession.getPlayerState().getDef() * 10
                    + gameSession.getPlayerState().getSpd() * 10;
        score = applyCards(score, gameSession);
        return score;
    }

    public GameoverResponse gameover(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);
        int score = calculateScore(gameSession);
        return new GameoverResponse(score);
    }

    public void registerScore(String sessionId, UserPrincipal principal) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);

        ScoreRecord scoreRecord = new ScoreRecord(
                principal.getUserId(),
                gameSession.getPlayerState().getName(),
                calculateScore(gameSession),
                gameSession.getPlayerState().getLevel(),
                Instant.now()
        );
    
        scoreRecordRepository.save(scoreRecord);
    }

    public int applyCards(int score, GameSession gameSession) {
        for(CardState card : gameSession.getPlayerState().getOwnedCards()) {
            if(card.getName().equals("アディショナルスコア")) {
                score = (int) (score * 1.5);
            }
        }
        return score;
    }
}
