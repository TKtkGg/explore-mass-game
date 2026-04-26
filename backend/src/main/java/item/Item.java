package item;

import character.Character;

public abstract class Item {
	private String name;
	private String Effect;
	public Item(String name, String Effect) {
		this.name = name;
		this.Effect = Effect;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEffect() {
		return this.Effect;
	}
	
	public abstract void use(Character user);
}
