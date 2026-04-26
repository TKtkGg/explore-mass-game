package explore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import battle.Battle;
import card.CardList;
import character.Player;
import character.enemy.EnemyList;
import equipment.EquipmentList;
import shop.Shop;
import treasure.EquipmentTreasure;
import treasure.StatusTreasure;

public class Move {
	Scanner sc;
	Random rand = new Random();
	
	Battle battle;
	StatusTreasure st;
	EnemyList elist = new EnemyList();
	CardList cl;
	Shop shop;
	
	
	private int mass = 25;
	
	private Map<String, Runnable> option = new HashMap<>();
	
	public Move(Player p, EquipmentList el, EquipmentTreasure et, Scanner sc) {
		this.sc = sc;
		this.battle = new Battle(sc);
		this.cl = new CardList(sc);
		this.shop = new Shop(sc);
		this.st = new StatusTreasure(sc);
		option.put("BATTLE", () -> battle.battle(p, elist.getRandomEnemy()));
		option.put("TREASURE", () -> 
			{
				if(rand.nextBoolean()) {
					st.open(p);
				} else {
					et.open(p);
				}
			});
		option.put("REST", () -> p.rest());
		option.put("CARD", () -> cl.showCards(p));
		option.put("SHOP", () -> shop.enter(p, el, cl));
	}
	
	public void explore(Player p) {
		while(mass > 0) {
			if (cl.getUnHavingCards(p).isEmpty()) {
			    option.remove("CARD");
			}
			
			List<String> optionName = new ArrayList<>();
			for (String key : option.keySet()) {
				optionName.add(key);
			}
			String upOption = optionName.get(rand.nextInt(optionName.size()));
			String rightOption = optionName.get(rand.nextInt(optionName.size()));
			String leftOption = optionName.get(rand.nextInt(optionName.size()));
			
			System.out.println();
			System.out.println("どこへいきますか？(残り" + mass + "マス)");
			System.out.println("上(w) : " + upOption);
			System.out.println("右(d) : " + rightOption);
			System.out.println("左(a) : " + leftOption);
			System.out.println("ステータス(s)");
			String direction = sc.next();
			
			switch(direction) {
				case "w":
					option.get(upOption).run();
					break;
				case "d":
					option.get(rightOption).run();
					break;
				case "a":
					option.get(leftOption).run();
					break;
				case "s":
					p.status();
					break;
				default:
					break;
			}
			
			mass--;
		}
	}
}