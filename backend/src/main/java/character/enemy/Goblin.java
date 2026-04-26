package character.enemy;

import character.Character;

public class Goblin extends Enemy {
	public Goblin() {
		super("ゴブリン", 1, 80, 5, 3, 3, 30, 80);
	}
	
	public void attack(Character target) {
		super.attack(target);
	}
}
