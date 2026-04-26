package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import character.Player;
import explore.TextEnter;

public class CardList {
	private ArrayList<Card> cards;
	Scanner sc;

	TextEnter text;
	
	public CardList(Scanner sc) {
		this.sc = sc;
		this.text = new TextEnter(sc);
		this.cards = new ArrayList<>();
		this.cards.add(new EquipmentMaster());
		this.cards.add(new SlimeKiller());
		this.cards.add(new GoblinKiller());
		this.cards.add(new Lucky());
	}
	
	public ArrayList<Card> useCardList() {
		return cards;
	}
	
	public Card getCardByName(String name) {
		for(Card c : this.cards) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Card> getUnHavingCards(Player p) {
		ArrayList<Card> unHavingCards = new ArrayList<>();
		for(Card c : this.cards) {
			if(!p.hasCard(c.getName())) {
				unHavingCards.add(c);
			}
		}
		return unHavingCards;
	}
	
	public void showCards(Player p) {
		ArrayList<Card> chooseCards = new ArrayList<>();
		chooseCards.addAll(getUnHavingCards(p));
		
		Collections.shuffle(chooseCards);
		System.out.println("カードを選べ");
		for(int i = 0; i < chooseCards.size(); i++) {
			System.out.println((i + 1) + " : " + chooseCards.get(i).getName());
			System.out.println("    " + chooseCards.get(i).getText());
			if(i == 2) {
				break;
			}
		}
		int choiceNum = sc.nextInt() - 1;
		p.obtainCard(chooseCards.get(choiceNum).getName());
		System.out.println(chooseCards.get(choiceNum).getName() + "を手に入れた！");
		text.textEnter("");
	}
}