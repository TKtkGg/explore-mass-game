package com.example.backend.service.gamestate.treasure;

import org.springframework.stereotype.Service;

@Service
public class StatusTreasureState {
    private String[] statusArray;
    private int randomIndex;

    public StatusTreasureState() {
        this.statusArray = new String[] {"HP", "ATK", "DEF", "SPD"};
        this.randomIndex = 0;
    }

    public String[] getStatusArray() {
        return this.statusArray;
    }

    public int getRandomIndex() {
        this.randomIndex = (int) (Math.random() * this.statusArray.length);
        return this.randomIndex;
    }
}
