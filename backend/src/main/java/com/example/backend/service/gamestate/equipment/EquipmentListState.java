package com.example.backend.service.gamestate.equipment;

import org.springframework.stereotype.Service;

@Service
public class EquipmentListState {
    EquipmentState[] equipmentList;

    public EquipmentListState() {
        this.equipmentList = new EquipmentState[] {
            new EquipmentState("木の剣", 2, 50, 30),
            new EquipmentState("錆びた剣", 3, 100, 24),
            new EquipmentState("石の剣", 5, 300, 20),
            new EquipmentState("鉄の剣", 8, 1000, 10),
            new EquipmentState("山の剣", 10, 1500, 5),
            new EquipmentState("海の剣", 10, 1500, 5),
            new EquipmentState("鋼の剣", 15, 2500, 3),
            new EquipmentState("ダイヤモンドの剣", 20, 5000, 1),
        };
    }

    public EquipmentState[] getEquipmentList() {
        return this.equipmentList;
    }
}
