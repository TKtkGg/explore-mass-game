package com.example.backend.dto.status;

import java.util.List;

import com.example.backend.service.gamestate.EquipmentState;

public class EquipmentResponse {
    private List<EquipmentState> ownEquipmentList;
    private EquipmentState equipment;

    public EquipmentResponse(List<EquipmentState> ownEquipmentList, EquipmentState equipment) {
        this.ownEquipmentList = ownEquipmentList;
        this.equipment = equipment;
    }

    public List<EquipmentState> getOwnEquipmentList() {
        return this.ownEquipmentList;
    }

    public EquipmentState getEquipment() {
        return this.equipment;
    }
}
