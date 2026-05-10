package com.example.backend.service.gamestate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EquipmentTreasureState {
    private EquipmentState[] equipmentList;
    private List<EquipmentState> treasureInside = new ArrayList<>();
    private EquipmentListState equipmentListState;
    public EquipmentTreasureState(EquipmentListState equipmentListState) {
        this.equipmentListState = equipmentListState;
        this.equipmentList = equipmentListState.getEquipmentList();
        for(EquipmentState equipment : equipmentList) {
            for(int i = 0; i < equipment.getChance(); i++) {
                this.treasureInside.add(equipment);
            }
        }
    }

    public List<EquipmentState> getTreasureInside() {
        return this.treasureInside;
    }

    public int getRandomIndex() {
        return (int) (Math.random() * this.treasureInside.size());
    }
}
