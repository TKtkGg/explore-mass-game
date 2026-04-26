package character.enemy;

import character.Character;

public class Slime extends Enemy{
	public Slime() {
		super("スライム", 1, 50, 3, 3, 3, 20, 50);
	}
	
	public void attack(Character target) {
		super.attack(target);
		
		//50%で粘液を付与する
		if(Math.random() < 0.5) {
			System.out.println("粘液でヌルヌルになった！");
		}
	}
}
