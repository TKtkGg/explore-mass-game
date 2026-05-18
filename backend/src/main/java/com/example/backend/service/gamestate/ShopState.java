package com.example.backend.service.gamestate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
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
