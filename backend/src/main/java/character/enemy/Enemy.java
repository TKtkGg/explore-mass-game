package character.enemy;

import character.Character;
import character.Player;

public class Enemy extends Character {
	private int original_level;
	private int original_max_hp;
	private int original_atk;
	private int original_def;
	private int original_spd;
	public Enemy(String name, int level, int max_hp, int atk, int def, int spd, int exp, int gold) {
		super(name, level, max_hp, atk, def, spd, exp, gold);
		this.original_level = level;
		this.original_max_hp = max_hp;
		this.original_atk = atk;
		this.original_def = def;
		this.original_spd = spd;
	}
	
	public void status() {
		super.status();
		System.out.println("EXP : " + this.getExp());
	}
	
	public void attack(Character target) {
		super.attack(target);
	}
	
	public void adjustLevel(Player p) {
		double playerAvgStatus = (p.getMaxHp() / 10 + p.getAtk() + p.getDef() + p.getSpd()) / 4 + p.getOwnedCards().size() * 5;
		double enemyAvgStatus = (this.getOriginalMaxHp() / 10 + this.getOriginalAtk() + this.getOriginalDef() + this.getOriginalSpd()) / 4;
		int levelDifference = (int) ((playerAvgStatus - enemyAvgStatus) / 5);
		System.out.println(levelDifference);
		
		this.setLevel(this.getOriginalLevel() + levelDifference);
		this.setMaxHp(this.getOriginalMaxHp() + levelDifference * 10);
		this.setAtk(this.getOriginalAtk() + levelDifference);
		this.setDef(this.getOriginalDef() + levelDifference);
		this.setSpd(this.getOriginalSpd() + levelDifference);
	}
	
	public void respawn() {
		this.setHp(this.getMaxHp());
	}
	
	public int getOriginalLevel() {
		return this.original_level;
	}
	
	public int getOriginalMaxHp() {
		return this.original_max_hp;
	}

	public int getOriginalAtk() {
		return this.original_atk;
	}
		
	public int getOriginalDef() {
		return this.original_def;
	}
	
	public int getOriginalSpd() {
		return this.original_spd;
	}

	
}