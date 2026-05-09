package com.example.backend.service.gamestate;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    EquipmentState equipment;
    List<EquipmentState> ownEquipmentList = new ArrayList<>();
    EquipmentListState equipmentList = new EquipmentListState();

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
        this.equipment = equipmentList.getEquipmentList()[0];
        this.ownEquipmentList.add(equipment);
        this.ownEquipmentList.add(equipmentList.getEquipmentList()[1]);
        this.ownEquipmentList.add(equipmentList.getEquipmentList()[2]);
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
        return atk + equipment.getAtk();
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
    public EquipmentState getEquipment() {
        return equipment;
    }
    public List<EquipmentState> getOwnEquipmentList() {
        return ownEquipmentList;
    }

    public int Heal(int amount) {
        int healAmount = Math.min(amount, this.maxHp - this.hp);
        this.hp += healAmount;
        return healAmount;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        if(this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
    }
    public void setHp(int hp) {
        this.hp = hp;
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
    public void setEquipment(EquipmentState equipment) {
        this.equipment = equipment;
    }

    public void addEquipment(EquipmentState equipment) {
        this.ownEquipmentList.add(equipment);
    }
}
