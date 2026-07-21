package com.example.backend.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "score_record")
public class ScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer userId;
    private String playerName;
    private int score;
    private int level;
    private Instant playedAt;

    public ScoreRecord(Integer userId, String playerName, int score, int level, Instant playedAt) {
        this.userId = userId;
        this.playerName = playerName;
        this.score = score;
        this.level = level;
        this.playedAt = playedAt;
    }

    protected ScoreRecord() {}

    public Integer getUserId() {
        return userId;
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
