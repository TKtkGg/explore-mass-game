package com.example.backend.service.gamestate;

import org.springframework.stereotype.Service;

@Service
public class PlayerState {
    String name;
    int level;
    int maxHp;
    int hp;
    int atk;
    int def;
    int spd;
    int exp;
    int gold;

    public PlayerState() {
        this.name = "test";
        this.level = 1;
        this.maxHp = 100;
        this.hp = 100;
        this.atk = 10;
        this.def = 10;
        this.spd = 10;
        this.exp = 0;
        this.gold = 0;
    }

    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getHp() {
        return hp;
    }
    public int getAtk() {
        return atk;
    }
    public int getDef() {
        return def;
    }
    public int getSpd() {
        return spd;
    }
    public int getExp() {
        return exp;
    }
    public int getGold() {
        return gold;
    }

    public int Heal(int amount) {
        int healAmount = Math.min(amount, this.maxHp - this.hp);
        this.hp += healAmount;
        return healAmount;
    }

}
