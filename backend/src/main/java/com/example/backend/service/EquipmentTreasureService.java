package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.PlayerState;
import com.example.backend.service.gamestate.EquipmentState;
import com.example.backend.service.gamestate.EquipmentTreasureState;

@Service
public class EquipmentTreasureService {
    private PlayerState playerState;
    private EquipmentTreasureState equipmentTreasureState;
    private int randomIndex;

    public EquipmentTreasureService(PlayerState playerState, EquipmentTreasureState equipmentTreasureState) {
        this.playerState = playerState;
        this.equipmentTreasureState = equipmentTreasureState;
        this.randomIndex = 0;
    }

    public String open() {
        this.randomIndex = equipmentTreasureState.getRandomIndex();
        EquipmentState getEquipment = equipmentTreasureState.getTreasureInside().get(this.randomIndex);
        playerState.addEquipment(getEquipment);
        return getEquipment.getName() + "を手に入れた！";
    }
}
