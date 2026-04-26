package card;

import character.Player;

public class EquipmentMaster extends Card {
	public EquipmentMaster() {
		super("装備マスター", "装備のATKが1.5倍になる", 750);
	}
	
	public int applyEffect(int atk, Player p) {
		if(!p.hasCard(this.getName())) {
			return atk;
		}
		return (int) (atk * 1.5);
	}
}
