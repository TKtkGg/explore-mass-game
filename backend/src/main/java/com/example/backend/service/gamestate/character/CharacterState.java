package com.example.backend.service.gamestate.character;

public abstract class CharacterState {
    String name;
    int level;
    int maxHp;
    int hp;
    int atk;
    int def;
    int spd;
    int exp;
    int gold;
    boolean isDefend = false;

    public CharacterState(String name, int level, int maxHp, int hp, int atk, int def, int spd, int exp, int gold) {
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

    public boolean getDefend() {
        return isDefend;
    }


    public void setName(String name) {
        this.name = name;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        if(this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
    }
    public void setAtk(int atk) {
        this.atk = atk;
    }
    public void setDef(int def) {
        this.def = def;
    }
    public void setSpd(int spd) {
        this.spd = spd;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setDefend(boolean isDefend) {
        this.isDefend = isDefend;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }
}
