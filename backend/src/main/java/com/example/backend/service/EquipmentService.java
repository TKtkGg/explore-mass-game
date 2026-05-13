package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.PlayerState;
import com.example.backend.service.gamestate.equipment.EquipmentListState;
import com.example.backend.service.gamestate.equipment.EquipmentState;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {
    private PlayerState playerState;
    private EquipmentListState equipmentListState;
    public EquipmentService(PlayerState playerState, EquipmentListState equipmentListState) {
        this.playerState = playerState;
        this.equipmentListState = equipmentListState;
    }

    public List<EquipmentState> getUnownedEquipments() {
        return Arrays.stream(this.equipmentListState.getEquipmentList()).filter(equipment -> !this.playerState.getOwnedEquipmentList().contains(equipment)).collect(Collectors.toList());
    }
}
