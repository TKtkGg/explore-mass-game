package com.example.backend.service.gamestate;

public class EquipmentState {
    String name;
    int atk;
    int price;
    int chance;

    public EquipmentState(String name, int atk, int price, int chance) {
        this.name = name;
        this.atk = atk;
        this.price = price;
        this.chance = chance;
    }

    public String getName() {
        return this.name;
    }

    public int getAtk() {
        return this.atk;
    }

    public int getPrice() {
        return this.price;
    }
    
    public int getChance() {
        return this.chance;
    }
}
