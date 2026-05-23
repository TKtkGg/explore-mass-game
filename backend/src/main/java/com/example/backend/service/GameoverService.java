package com.example.backend.service;

import com.example.backend.dto.Gameover.GameoverResponse;
import com.example.backend.service.gamestate.character.PlayerState;
import org.springframework.stereotype.Service;

@Service
public class GameoverService {
    private PlayerState playerState;

    public GameoverService(PlayerState playerState) {
        this.playerState = playerState;
    }

    public GameoverResponse gameover() {
        int score = playerState.getLevel() * 100 + playerState.getOwnedEquipmentList().size() * 100 + playerState.getOwnedCards().size() * 70;
        return new GameoverResponse(score);
    }
}
