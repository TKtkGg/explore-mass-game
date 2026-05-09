package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.status.StatusResponse;
import com.example.backend.service.gamestate.PlayerState;

@Service
public class StatusService {
    private PlayerState playerState;
    public StatusService(PlayerState playerState) {
        this.playerState = playerState;
    }
    public StatusResponse status() {
        return new StatusResponse(this.playerState.getName(), this.playerState.getLevel(), this.playerState.getMaxHp(), this.playerState.getHp(), this.playerState.getAtk(), this.playerState.getDef(), this.playerState.getSpd(), this.playerState.getExp(), this.playerState.getGold(), this.playerState.getEquipment(), this.playerState.getOwnEquipmentList());
    }
}
