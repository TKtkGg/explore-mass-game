package com.example.backend.dto.status;

public class StatusResponse {
    private String name;
    private int level;
    private int maxHp;
    private int hp;
    private int atk;
    private int def;
    private int spd;
    private int exp;
    private int gold;

    public StatusResponse(String name, int level, int maxHp, int hp, int atk, int def, int spd, int exp, int gold) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.exp = exp;
        this.gold = gold;
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
    
    public int getDef() {
        return this.def;
    }
    
    public int getSpd() {
        return this.spd;
    }
    
    public int getExp() {
        return this.exp;
    }
    
    public int getGold() {
        return this.gold;
    }
}
