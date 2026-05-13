package com.example.backend.service.gamestate.treasure;

import org.springframework.stereotype.Service;

@Service
public class StatusTreasureState {
    private String[] statusArray;
    private int point;
    private int randomIndex;
    private String targetName;

    public StatusTreasureState() {
        this.statusArray = new String[] {"HP", "ATK", "DEF", "SPD"};
        this.point = 0;
        this.randomIndex = 0;
        this.targetName = "";
    }

    public String[] getStatusArray() {
        return this.statusArray;
    }

    public int getPoint() {
        return this.point;
    }

    public int getRandomIndex() {
        this.randomIndex = (int) (Math.random() * this.statusArray.length);
        return this.randomIndex;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setRandomIndex(int randomIndex) {
        this.randomIndex = randomIndex;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
