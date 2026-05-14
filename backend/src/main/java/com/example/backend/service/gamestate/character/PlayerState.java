package com.example.backend.service.gamestate.character;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.equipment.EquipmentListState;
import com.example.backend.service.gamestate.equipment.EquipmentState;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerState extends CharacterState {
    EquipmentState equipment;
    List<EquipmentState> ownedEquipmentList = new ArrayList<>();
    EquipmentListState equipmentList;
    List<CardState> ownedCards;

    public PlayerState(EquipmentListState equipmentList) {
        super("test", 1, 100, 100, 10, 10, 10, 0, 0);
        this.equipmentList = equipmentList;
        this.equipment = this.equipmentList.getEquipmentList()[0];
        this.ownedEquipmentList.add(equipment);
        this.ownedCards = new ArrayList<>();
    }

    @Override
    public int getAtk() {
        return this.atk + this.equipment.getAtk();
    }

    public int getOriginalAtk() {
        return this.atk;
    }

    public EquipmentState getEquipment() {
        return equipment;
    }
    public List<EquipmentState> getOwnedEquipmentList() {
        return ownedEquipmentList;
    }
    public List<CardState> getOwnedCards() {
        return ownedCards;
    }
    
    public int Heal(int amount) {
        int healAmount = Math.min(amount, this.maxHp - this.hp);
        this.hp += healAmount;
        return healAmount;
    }
    public void setEquipment(EquipmentState equipment) {
        this.equipment = equipment;
    }

    public void addEquipment(EquipmentState equipment) {
        this.ownedEquipmentList.add(equipment);
    }
    public void addCard(CardState card) {
        this.ownedCards.add(card);
    }

    public void init() {
        this.name = "test";
        this.level = 1;
        this.maxHp = 100;
        this.hp = 100;
        this.atk = 10;
        this.def = 10;
        this.spd = 10;
        this.exp = 0;
        this.gold = 0;
        this.equipment = this.equipmentList.getEquipmentList()[0];
        this.ownedEquipmentList.clear();
        this.ownedEquipmentList.add(equipment);
        this.ownedCards = new ArrayList<>();
    }
}
