package com.example.backend.service.gamestate.character;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.card.CardState;
import com.example.backend.service.gamestate.equipment.EquipmentListState;
import com.example.backend.service.gamestate.equipment.EquipmentState;
import com.example.backend.service.gamestate.item.ItemListState;
import com.example.backend.service.gamestate.item.ItemState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class PlayerState extends CharacterState {
    EquipmentState equipment;
    List<EquipmentState> ownedEquipmentList = new ArrayList<>();
    EquipmentListState equipmentList;
    List<CardState> ownedCards;
    int nextLevelExp = 100;
    boolean isRun = false;
    Map<String, Integer> ownedItems = new HashMap<>();

    public PlayerState(EquipmentListState equipmentList) {
        super("test", 1, 100, 100, 10, 10, 10, 0, 100);
        this.equipmentList = equipmentList;
        this.equipment = this.equipmentList.getEquipmentList()[0];
        this.ownedEquipmentList.add(equipment);
        this.ownedCards = new ArrayList<>();
        this.isRun = false;
        this.nextLevelExp = 100;
        this.ownedItems.put("回復薬(小)", 3);
    }

    @Override
    public int getAtk() {
        for(CardState card : this.ownedCards) {
            if(card.getName().equals("装備マスター")) {
                return this.atk + (int)(this.equipment.getAtk() * 1.5);
            }
        }
        return this.atk + this.equipment.getAtk();
    }

    public int getOriginalAtk() {
        return this.atk;
    }

    public EquipmentState getEquipment() {
        return equipment;
    }
    public List<EquipmentState> getOwnedEquipmentList() {
        return ownedEquipmentList;
    }
    public List<CardState> getOwnedCards() {
        return ownedCards;
    }
    public Map<String, Integer> getOwnedItems() {
        return ownedItems;
    }
    public int getNextLevelExp() {
        return nextLevelExp;
    }
    public boolean getIsRun() {
        return isRun;
    }

    public void setIsRun(boolean isRun) {
        this.isRun = isRun;
    }
    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public int Heal(int amount) {
        int healAmount = Math.min(amount, this.maxHp - this.hp);
        this.hp += healAmount;
        return healAmount;
    }

    public void setEquipment(EquipmentState equipment) {
        this.equipment = equipment;
    }

    public void addEquipment(EquipmentState equipment) {
        this.ownedEquipmentList.add(equipment);
    }

    public void addCard(CardState card) {
        this.ownedCards.add(card);
    }

    public void addItem(ItemState item, int count) {
        this.ownedItems.getOrDefault(item.getName(), this.ownedItems.get(item.getName()) + count);
    }

    public void removeItem(ItemState item, int count) {
        this.ownedItems.put(item.getName(), this.ownedItems.get(item.getName()) - count);
        if(this.ownedItems.get(item.getName()) <= 0) {
            this.ownedItems.remove(item.getName());
        }
    }

    public String calcExp(int gainExp) {
        String message = "";
		this.setExp(this.getExp() + gainExp);
		while(this.getExp() >= this.nextLevelExp) {
			message += this.levelUp();
			this.setExp(this.getExp() - this.nextLevelExp);
			this.nextLevelExp *= 1.2;
		}
        return message;
	}
	public String levelUp() {
		this.setLevel(this.getLevel() + 1);
        this.setMaxHp(this.getMaxHp() + 10);
		this.setHp(this.getMaxHp());
		this.setAtk(this.getOriginalAtk() + 1);
		this.setDef(this.getDef() + 1);
		this.setSpd(this.getSpd() + 1);
        return "レベルアップ！";
	}

    public void init() {
        this.name = "test";
        this.level = 1;
        this.maxHp = 100;
        this.hp = 100;
        this.atk = 10;
        this.def = 10;
        this.spd = 10;
        this.exp = 0;
        this.gold = 100;
        this.equipment = this.equipmentList.getEquipmentList()[0];
        this.ownedEquipmentList.clear();
        this.ownedEquipmentList.add(equipment);
        this.ownedCards = new ArrayList<>();
        this.isRun = false;
        this.nextLevelExp = 100;
        this.ownedItems.clear();
        this.ownedItems.put("回復薬(小)", 3);
    }
}
