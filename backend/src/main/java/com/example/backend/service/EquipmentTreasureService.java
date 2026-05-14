package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.service.gamestate.equipment.EquipmentState;
import com.example.backend.service.gamestate.treasure.EquipmentTreasureState;

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
        if(playerState.getOwnedEquipmentList().contains(getEquipment)) {
            playerState.setGold(playerState.getGold() + getEquipment.getPrice() / 2);
            return getEquipment.getName() + "はすでに持っているので、売っぱらった。+" + getEquipment.getPrice() / 2 + "ゴールド";
        }
        playerState.addEquipment(getEquipment);
        return getEquipment.getName() + "を手に入れた！";
    }
}
