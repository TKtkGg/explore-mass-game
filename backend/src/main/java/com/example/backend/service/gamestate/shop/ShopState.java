package com.example.backend.service.gamestate.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopState {
    private List<Merchandise> display;

    public ShopState() {
        this.display = new ArrayList<>();
    }

    public List<Merchandise> getDisplay() {
        return this.display;
    }

    public void setDisplay(List<Merchandise> display) {
        this.display = display;
    }
}
