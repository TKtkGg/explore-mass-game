package com.example.backend.service.treasure;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.equipment.EquipmentState;
import com.example.backend.service.gamestate.treasure.EquipmentTreasureState;
import com.example.backend.service.gamestate.session.GameSession;

@Service
public class EquipmentTreasureService {
    private EquipmentTreasureState equipmentTreasureState;

    public EquipmentTreasureService(EquipmentTreasureState equipmentTreasureState) {
        this.equipmentTreasureState = equipmentTreasureState;
    }

    public String open(GameSession gameSession) {
        int randomIndex = equipmentTreasureState.getRandomIndex();
        EquipmentState getEquipment = equipmentTreasureState.getTreasureInside().get(randomIndex);
        if(gameSession.getPlayerState().getOwnedEquipmentList().contains(getEquipment)) {
            gameSession.getPlayerState().setGold(gameSession.getPlayerState().getGold() + getEquipment.getPrice() / 2);
            return getEquipment.getName() + "はすでに持っているので、売っぱらった。+" + getEquipment.getPrice() / 2 + "ゴールド";
        }
        gameSession.getPlayerState().addEquipment(getEquipment);
        return getEquipment.getName() + "を手に入れた！";
    }
}
