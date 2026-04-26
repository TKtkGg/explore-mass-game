package item;

import character.Character;

public class SmallHealPotion extends HealItem {
	public SmallHealPotion() {
		super("回復薬(小)", 20);
	}
	
	@Override
	public void use(Character user) {
		user.setHp(Math.min(user.getHp() + this.getAmount(), user.getMaxHp()));
		System.out.println("HPが" + this.getAmount() + "回復した！");
		System.out.println("HP : " + user.getHp() + "/" + user.getMaxHp());
	}

}
