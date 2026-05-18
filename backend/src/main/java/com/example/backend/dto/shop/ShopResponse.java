package com.example.backend.dto.shop;

import java.util.List;

import com.example.backend.service.gamestate.Merchandise;

public class ShopResponse {
    private int gold;
    private List<Merchandise> display;
    private String message;

    public ShopResponse(int gold, List<Merchandise> display, String message) {
        this.gold = gold;
        this.display = display;
        this.message = message;
    }

    public int getGold() {
        return this.gold;
    }

    public List<Merchandise> getDisplay() {
        return this.display;
    }

    public String getMessage() {
        return this.message;
    }
}
