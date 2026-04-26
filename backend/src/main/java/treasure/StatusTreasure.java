package treasure;

import java.util.Scanner;

import card.Lucky;
import character.Player;
import explore.TextEnter;

public class StatusTreasure extends Treasure {
	TextEnter text;
	Lucky lucky = new Lucky();
	public StatusTreasure(Scanner sc) {
		super("ステータス宝箱");
		this.text = new TextEnter(sc);
	}
	
	public void open(Player p) {
		String[] statusArray = {"HP", "ATK", "DEF", "SPD"};
		int point = 0;
		int randomIndex = (int) (Math.random() * statusArray.length);
		String targetName = statusArray[randomIndex];
		
		if(targetName.equals("HP")) {
			point = 10;
		} else {
			point = 1;
		}
		
		point = lucky.applyEffect(point, p);
		
		switch(targetName) {
			case "HP":
				p.setMaxHp(p.getMaxHp() + point);
				break;
			case "ATK":
				p.setAtk(p.getAtk() + point);
				break;
			case "DEF":
				p.setDef(p.getDef() + point);
				break;
			case "SPD":
				p.setSpd(p.getSpd() + point);
				break;
			default:
				break;
		}
		
		
		System.out.println(this.name + "を見つけた！");
		System.out.println(p.getName() + "の" + targetName + "が" + point + "上昇した！");
		text.textEnter("");
		
	}

}