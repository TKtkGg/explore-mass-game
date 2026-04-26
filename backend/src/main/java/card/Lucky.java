package card;

import character.Player;

public class Lucky extends Card {
	public Lucky() {
		super("ラッキー", "ステータス宝箱から2倍の力を得る", 700);
	}
	
	public int applyEffect(int status, Player p) {
		if(!p.hasCard(this.getName())) {
			return status;
		}
		return status * 2;
	}
}
