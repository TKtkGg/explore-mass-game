package com.example.backend.dto.status;

import java.util.List;
import java.util.Map;

import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.equipment.EquipmentState;

public class StatusResponse {
    private String name;
    private int level;
    private int maxHp;
    private int hp;
    private int atk;
    private int totalAtk;
    private int def;
    private int spd;
    private int exp;
    private int nextLevelExp;
    private int gold;
    private EquipmentState equipment;
    private List<EquipmentState> ownedEquipmentList;
    private List<CardState> ownedCards;
    private Map<String, Integer> ownedItems;

    public StatusResponse(String name, int level, int maxHp, int hp, int atk, int totalAtk, int def, int spd, int exp, int nextLevelExp, int gold, EquipmentState equipment, List<EquipmentState> ownEquipmentList, List<CardState> ownedCards, Map<String, Integer> ownedItems) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp;
        this.hp = hp;
        this.atk = atk;
        this.totalAtk = totalAtk;
        this.def = def;
        this.spd = spd;
        this.exp = exp;
        this.nextLevelExp = nextLevelExp;
        this.gold = gold;
        this.equipment = equipment;
        this.ownedEquipmentList = ownEquipmentList;
        this.ownedCards = ownedCards;
        this.ownedItems = ownedItems;
    }

    public String getName() {
        return this.name;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public int getMaxHp() {
        return this.maxHp;
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public int getAtk() {
        return this.atk;
    }

    public int getTotalAtk() {
        return this.totalAtk;
    }
    
    public int getDef() {
        return this.def;
    }
    
    public int getSpd() {
        return this.spd;
    }
    
    public int getExp() {
        return this.exp;
    }
    
    public int getNextLevelExp() {
        return this.nextLevelExp;
    }
    
    public int getGold() {
        return this.gold;
    }

    public EquipmentState getEquipment() {
        return this.equipment;
    }

    public List<EquipmentState> getOwnedEquipmentList() {
        return this.ownedEquipmentList;
    }

    public List<CardState> getOwnedCards() {
        return this.ownedCards;
    }

    public Map<String, Integer> getOwnedItems() {
        return this.ownedItems;
    }
}
