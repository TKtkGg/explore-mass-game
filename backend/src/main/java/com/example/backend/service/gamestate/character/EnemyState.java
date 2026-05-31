package com.example.backend.service.gamestate.character;

public class EnemyState extends CharacterState {
    String imagePath;
    public EnemyState(String name, int level, int maxHp, int hp, int atk, int def, int spd, int exp, int gold, String imagePath) {
        super(name, level, maxHp, hp, atk, def, spd, exp, gold);
        this.imagePath = imagePath;
    }

    public void respawn() {
        this.setHp(this.getMaxHp());
    }

    public void adjustLevel(PlayerState p) {
		double playerAvgStatus = (p.getMaxHp() / 10 + p.getAtk() + p.getDef() + p.getSpd()) / 4 + p.getOwnedCards().size() * 5;
		double enemyAvgStatus = (this.getMaxHp() / 10 + this.getAtk() + this.getDef() + this.getSpd()) / 4;
		int levelDifference = (int) ((playerAvgStatus - enemyAvgStatus) / 5);
		
		this.setLevel(this.getLevel() + levelDifference);
		this.setMaxHp(this.getMaxHp() + levelDifference * 10);
		this.setAtk(this.getAtk() + levelDifference);
		this.setDef(this.getDef() + levelDifference);
		this.setSpd(this.getSpd() + levelDifference);
        this.setExp(this.getExp() + (this.getExp() / 5 * this.getLevel()));
        this.setGold(this.getGold() + (this.getGold() / 5 * this.getLevel()));
	}

    public String getImagePath() {
        return this.imagePath;
    }
}
