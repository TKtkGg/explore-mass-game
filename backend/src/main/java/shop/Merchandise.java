package shop;

import character.Player;

public interface Merchandise {
	String getName();
	int getPrice();
	String getType();
	boolean canBeSoldTo(Player player);
	void grantTo(Player player);
}
