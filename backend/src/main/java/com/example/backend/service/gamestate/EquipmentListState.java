package com.example.backend.service.gamestate;

public class EquipmentListState {
    EquipmentState[] equipmentList;

    public EquipmentListState() {
        this.equipmentList = new EquipmentState[] {
            new EquipmentState("木の剣", 2, 50, 15),
            new EquipmentState("錆びた剣", 3, 100, 13),
            new EquipmentState("石の剣", 5, 300, 10),
            new EquipmentState("鉄の剣", 8, 1000, 6),
        };
    }

    public EquipmentState[] getEquipmentList() {
        return this.equipmentList;
    }
}
