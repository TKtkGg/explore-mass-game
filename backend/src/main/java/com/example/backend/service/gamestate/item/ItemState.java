package com.example.backend.service.gamestate.item;

import com.example.backend.service.gamestate.shop.Merchandise;

public class ItemState extends Merchandise {
    int amount;
    String effectType;

    public ItemState(String name, int amount, int price, String effectType) {
        super(name, price, "ITEM");
        this.amount = amount;
        this.effectType = effectType;
    }
    
    public int getAmount() {
        return this.amount;
    }
    public String getEffectType() {
        return this.effectType;
    }
}
