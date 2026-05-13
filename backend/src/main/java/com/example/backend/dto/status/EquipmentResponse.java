package com.example.backend.dto.status;

import java.util.List;

import com.example.backend.service.gamestate.equipment.EquipmentState;

public class EquipmentResponse {
    private List<EquipmentState> ownedEquipmentList;
    private EquipmentState equipment;

    public EquipmentResponse(List<EquipmentState> ownedEquipmentList, EquipmentState equipment) {
        this.ownedEquipmentList = ownedEquipmentList;
        this.equipment = equipment;
    }

    public List<EquipmentState> getOwnedEquipmentList() {
        return this.ownedEquipmentList;
    }

    public EquipmentState getEquipment() {
        return this.equipment;
    }
}
