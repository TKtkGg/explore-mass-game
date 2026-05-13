package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.status.EquipmentResponse;
import com.example.backend.dto.status.EquipmentRequest;
import com.example.backend.dto.status.StatusResponse;
import com.example.backend.service.gamestate.PlayerState;

@Service
public class StatusService {
    private PlayerState playerState;
    public StatusService(PlayerState playerState) {
        this.playerState = playerState;
    }
    public StatusResponse status() {
        return new StatusResponse(this.playerState.getName(), this.playerState.getLevel(), this.playerState.getMaxHp(), this.playerState.getHp(), this.playerState.getAtk(), this.playerState.getTotalAtk(), this.playerState.getDef(), this.playerState.getSpd(), this.playerState.getExp(), this.playerState.getGold(), this.playerState.getEquipment(), this.playerState.getOwnedEquipmentList(), this.playerState.getOwnedCards());
    }

    public EquipmentResponse equipment() {
        return new EquipmentResponse(this.playerState.getOwnedEquipmentList(), this.playerState.getEquipment());
    }

    public EquipmentResponse changeEquipment(EquipmentRequest request) {
        this.playerState.setEquipment(this.playerState.getOwnedEquipmentList().stream().filter(e -> e.getName().equals(request.getName())).findFirst().orElse(null));
        return new EquipmentResponse(this.playerState.getOwnedEquipmentList(), this.playerState.getEquipment());
    }
}