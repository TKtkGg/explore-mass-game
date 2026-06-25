package com.example.backend.service.gamestate.treasure;

public class TreasureState {
    private String appearedTreasure;
    public TreasureState() {
        this.appearedTreasure = "";
    }

    public String getAppearedTreasure() {
        return this.appearedTreasure;
    }

    public void setAppearedTreasure(String appearedTreasure) {
        this.appearedTreasure = appearedTreasure;
    }
}
