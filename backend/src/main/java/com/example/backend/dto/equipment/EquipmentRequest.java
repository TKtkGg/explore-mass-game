package com.example.backend.dto.equipment;

import com.example.backend.service.gamestate.equipment.EquipmentState;

public class EquipmentRequest {
    private String name;
    private EquipmentState chosenEquipment;

    public EquipmentRequest(String name, EquipmentState chosenEquipment) {
        this.name = name;
        this.chosenEquipment = chosenEquipment;
    }

    public String getName() {
        return this.name;
    }

    public EquipmentState getChosenEquipment() {
        return this.chosenEquipment;
    }
}
