package com.example.backend.dto;

import java.time.Instant;

public class RankingResponse {
    String playerName;
    int score;
    int level;
    Instant playedAt;

    public RankingResponse(String playerName, int score, int level, Instant playedAt) {
        this.playerName = playerName;
        this.score = score;
        this.level = level;
        this.playedAt = playedAt;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
    
    public int getLevel() {
        return level;
    }

    public Instant getPlayedAt() {
        return playedAt;
    }
}
