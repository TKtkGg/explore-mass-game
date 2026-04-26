package card;

import character.Player;

public class SlimeKiller extends Card {
	public SlimeKiller() {
		super("スライムキラー", "スライムに対して与えるダメージが1.5倍になる", 300);
	}
	
	public int applyEffect(int damage, String enemy, Player p) {
		if(!p.hasCard(this.getName())) {
			return damage;
		}
		if(enemy.contains("スライム")) {
			return (int) (damage * 1.5);
		}
		return damage;
	}

}
