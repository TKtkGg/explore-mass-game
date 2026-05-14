package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.shop.ShopResponse;
import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.dto.card.CardRequest;
import com.example.backend.dto.equipment.EquipmentRequest;

@Service
public class ShopService {
    private PlayerState playerState;
    private CardService cardService;
    private EquipmentService equipmentService;

    public ShopService(PlayerState playerState, CardService cardService, EquipmentService equipmentService) {
        this.playerState = playerState;
        this.cardService = cardService;
        this.equipmentService = equipmentService;
    }

    public ShopResponse showShop() {
        return new ShopResponse(this.playerState.getGold(), this.cardService.getUnownedCards(), this.equipmentService.getUnownedEquipments(), "");
    }

    public ShopResponse buyCard(CardRequest request) {
        if(this.playerState.getGold() < request.getChosenCard().getPrice()) {
            return new ShopResponse(this.playerState.getGold(), this.cardService.getUnownedCards(), this.equipmentService.getUnownedEquipments(), "ゴールドが足りません");
        }
        this.playerState.setGold(this.playerState.getGold() - request.getChosenCard().getPrice());
        this.playerState.addCard(request.getChosenCard());
        return new ShopResponse(this.playerState.getGold(), this.cardService.getUnownedCards(), this.equipmentService.getUnownedEquipments(), request.getChosenCard().getName() + "を手に入れた！");
    }

    public ShopResponse buyEquipment(EquipmentRequest request) {
        if(this.playerState.getGold() < request.getChosenEquipment().getPrice()) {
            return new ShopResponse(this.playerState.getGold(), this.cardService.getUnownedCards(), this.equipmentService.getUnownedEquipments(), "ゴールドが足りません");
        }
        this.playerState.setGold(this.playerState.getGold() - request.getChosenEquipment().getPrice());
        this.playerState.addEquipment(request.getChosenEquipment());
        return new ShopResponse(this.playerState.getGold(), this.cardService.getUnownedCards(), this.equipmentService.getUnownedEquipments(), request.getChosenEquipment().getName() + "を手に入れた！");
    }
}
