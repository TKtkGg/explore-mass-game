package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.equipment.EquipmentListState;
import com.example.backend.service.gamestate.equipment.EquipmentState;
import com.example.backend.service.gamestate.session.GameSession;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {
    private EquipmentListState equipmentListState;
    public EquipmentService(EquipmentListState equipmentListState) {
        this.equipmentListState = equipmentListState;
    }

    public List<EquipmentState> getUnownedEquipments(GameSession gameSession) {
        return Arrays.stream(this.equipmentListState.getEquipmentList()).filter(equipment -> !gameSession.getPlayerState().getOwnedEquipmentList().contains(equipment)).collect(Collectors.toList());
    }
}
