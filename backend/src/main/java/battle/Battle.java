package battle;

import java.util.Random;
import java.util.Scanner;

import character.Player;
import character.enemy.Enemy;
import explore.TextEnter;

public class Battle {
	Scanner sc;
	Random rand = new Random();
	
	TextEnter text;
	
	public Battle(Scanner sc) {
		this.sc = sc;
		this.text = new TextEnter(sc);
	}
	
	public void battle(Player p, Enemy e) {
		e.adjustLevel(p);
		System.out.println("戦闘開始");
		System.out.println(e.getName() + ":" + e.getLevel() + "LV");
		
		e.respawn();
		
		while(p.isAlive() && e.isAlive()) {
			System.out.println("1:攻撃 2:防御 3:アイテム");
			p.setDefend(false);
			e.setDefend(false);
			int playerChoice = sc.nextInt();
			int enemyChoice = rand.nextInt(2) + 1;
			
			if(playerChoice != 2 && enemyChoice != 2) {
				if(p.getSpd() >= e.getSpd()) {
					playerAction(p, e, playerChoice);
					if(e.isAlive()) {
						enemyAction(p, e, enemyChoice);
					}
				} else {
					enemyAction(p, e, enemyChoice);
					if(p.isAlive()) {
						playerAction(p, e, playerChoice);
					}
				}
			} else if(playerChoice == 2) {
				playerAction(p, e, playerChoice);
				enemyAction(p, e, enemyChoice);
			} else if(enemyChoice == 2) {
				enemyAction(p, e, enemyChoice);
				playerAction(p, e, playerChoice);
			}
			
		}
		if(p.isAlive()) {
			System.out.println(p.getName() + "の勝利！");
			p.calcExp(e.getExp());
			p.calcGold(e.getGold());
			text.textEnter("");
			return;
		} else {
			System.out.println(e.getName() + "の勝利！");
			return;
		}
	}
	
	public void playerAction(Player p, Enemy e, int playerChoice) {
			if(playerChoice == 1) {
				p.attack(e);
			} else if(playerChoice == 2) {
				p.defend();
			} else if(playerChoice == 3) {
				p.Item();
			} else {
				System.out.println("無効な選択肢です。");
			}
			text.textEnter("");
	}
	
	public void enemyAction(Player p, Enemy e, int enemyChoice) {
		if(enemyChoice == 1) {
			e.attack(p);
		} else if(enemyChoice == 2) {
			e.defend();
		}
		text.textEnter("");
	}
	
}