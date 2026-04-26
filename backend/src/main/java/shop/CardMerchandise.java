package shop;

import card.Card;
import character.Player;

public class CardMerchandise implements Merchandise {
	private final Card card;
	
	public CardMerchandise(Card card) {
		this.card = card;
	}
	
	@Override
	public String getName() {
		return card.getName();
	}
	
	@Override
	public int getPrice() {
		return card.getPrice();
	}
	
	@Override
	public String getType() {
		return "カード";
	}
	
	@Override
	public boolean canBeSoldTo(Player player) {
		boolean isPlayerHas = player.hasCard(card.getName());
		
		return !isPlayerHas;
	}
	
	@Override
	public void grantTo(Player player) {
		player.setGold(player.getGold() - card.getPrice());
		player.obtainCard(card.getName());
	}

}