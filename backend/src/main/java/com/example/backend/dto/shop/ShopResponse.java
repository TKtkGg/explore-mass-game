package com.example.backend.dto.shop;

import java.util.List;

import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.equipment.EquipmentState;

public class ShopResponse {
    private int gold;
    private List<CardState> unownedCards;
    private List<EquipmentState> unownedEquipments;
    private String message;

    public ShopResponse(int gold, List<CardState> unownedCards, List<EquipmentState> unownedEquipments, String message) {
        this.gold = gold;
        this.unownedCards = unownedCards;
        this.unownedEquipments = unownedEquipments;
        this.message = message;
    }

    public int getGold() {
        return this.gold;
    }

    public List<CardState> getUnownedCards() {
        return this.unownedCards;
    }

    public List<EquipmentState> getUnownedEquipments() {
        return this.unownedEquipments;
    }

    public String getMessage() {
        return this.message;
    }
}
