package com.example.backend.dto.Gameover;

public class GameoverResponse {
    private int score;

    public GameoverResponse(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
