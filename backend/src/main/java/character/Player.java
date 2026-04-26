package character;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import card.EquipmentMaster;
import card.GoblinKiller;
import card.SlimeKiller;
import equipment.Equipment;
import explore.TextEnter;
import item.Item;
import item.SmallHealPotion;

public class Player extends Character {
	Scanner sc;
	TextEnter text;
	EquipmentMaster em = new EquipmentMaster();
	SlimeKiller sk = new SlimeKiller();
	GoblinKiller gk = new GoblinKiller();
	private int nextLevelExp = 100;
	
	Equipment equipment;
	private ArrayList<Equipment> ownEquipments = new ArrayList<>();
	private Set<String> ownedCards = new HashSet<>();
	private Map<String, Integer> ownedItems = new HashMap<>();
	private Map<String, Item> itemCatalog = new HashMap<>();

	public Player(String name, int level, int max_hp, int atk, int def, int spd, int exp, Equipment e, Scanner sc) {
		super(name, level, max_hp, atk, def, spd, exp, 100);
		this.equipment = e;
		gotEquipment(e);
		ownedItems.put("回復薬(小)", 3);
		itemCatalog.put("回復薬(小)", new SmallHealPotion());
		this.sc = sc;
		this.text = new TextEnter(sc);
	} 	
	
	public void status() {
		super.status();
		System.out.println("EXP : " + this.getExp() + "/" + this.nextLevelExp);
		System.out.println("Gold : " + this.getGold());
		System.out.println("装備 : " + this.equipment.getName() + "(ATK:+" + this.equipment.getAtk() + ")");
		System.out.println("所持カード : " + this.ownedCards);
		text.textEnter("");
		this.equip();
	}
	
	public void attack(Character target) {
		super.attack(target);
	}
	
	@Override
	public int getAtk() {
		return super.getAtk() + em.applyEffect(this.equipment.getAtk(), this);
	}
	
	@Override
	public int calcDamage(Character target) {
		int damage = super.calcDamage(target);
		damage = sk.applyEffect(damage, target.getName(), this);
		damage = gk.applyEffect(damage, target.getName(), this);
		return damage;
	}
	
	public void calcExp(int gainExp) {
		System.out.println("EXP : " + this.getExp() + " -> " + (this.getExp() + gainExp));
		this.setExp(this.getExp() + gainExp);
		while(this.getExp() >= this.nextLevelExp) {
			this.levelUp();
			this.setExp(this.getExp() - this.nextLevelExp);
			this.nextLevelExp *= 1.2;
		}
	}
	
	public void calcGold(int gainGold) {
		System.out.println("Gold : " + this.getGold() + " -> " + (this.getGold() + gainGold));
		this.setGold(this.getGold() + gainGold);
	}
	
	public void levelUp() {
		System.out.println("レベルアップ！");
		System.out.println("LV : " + this.getLevel() + " -> " + (this.getLevel() + 1));
		this.setLevel(this.getLevel() + 1);
		this.setHp(this.getMaxHp());
		System.out.println("HP : " + this.getMaxHp() + "->" + (this.getMaxHp() + 10));
		this.setMaxHp(this.getMaxHp() + 10);
		System.out.println("ATK : " + this.getAtk() + " -> " + (this.getAtk() + 1));
		this.setAtk(this.getAtk() + 1);
		System.out.println("DEF : " + this.getDef() + " -> " + (this.getDef() + 1));
		this.setDef(this.getDef() + 1);
		System.out.println("SPD : " + this.getSpd() + " -> " + (this.getSpd() + 1));
		this.setSpd(this.getSpd() + 1);
		text.textEnter("");
	}

	public void rest() {
		this.Heal(100);
		System.out.println("休んで回復した！");
		text.textEnter("");
	}
	
	public void gotEquipment(Equipment e) {
		this.ownEquipments.add(e);
	}
	
	public ArrayList<Equipment> getOwnEquipment() {
		return this.ownEquipments;
	}
	
	public void equip() {
		System.out.println("装備を変更しますか？ (y/n)");
		String input = sc.next();
		if (input.equals("y")) {
			System.out.println("装備の番号を選択してください。");
			int num = 1;
			for(Equipment e : ownEquipments) {
				System.out.println(num + " : " + e.getName() + "(ATK:+" + e.getAtk() + ")");
				num++;
			}
			int choice = sc.nextInt();
			this.equipment = ownEquipments.get(choice - 1);
			return;
		} else {
			return;
		}
	}
	
	public void obtainCard(String cardName) {
	    this.ownedCards.add(cardName);
	}
	
	public boolean hasCard(String cardName) {
	    return this.ownedCards.contains(cardName);
	}
	
	public Set<String> getOwnedCards() {
	    return this.ownedCards;
	}
	
	public void Item() {
		System.out.println("アイテムを選べ");
		int num = 1;
		for (Map.Entry<String, Integer> entry : ownedItems.entrySet()) {
			System.out.println(num + ":" + entry.getKey() + "(" + entry.getValue() + ")");
			num++;
		}
		
		int choice = sc.nextInt();
		String itemName = (String) ownedItems.keySet().toArray()[choice - 1];
		if (ownedItems.get(itemName) > 0) {
			Item item = itemCatalog.get(itemName);
			item.use(this);
			ownedItems.put(itemName, ownedItems.get(itemName) - 1);
		} else {
			System.out.println("そのアイテムは持っていません。");
		}
		text.textEnter("");
	}
}
