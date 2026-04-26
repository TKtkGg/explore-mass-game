package shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import card.Card;
import card.CardList;
import character.Player;
import equipment.Equipment;
import equipment.EquipmentList;

public class Shop {
	private Scanner sc;
	
	private List<Merchandise> catalog = new ArrayList<>();
	private List<Merchandise> lineup = new ArrayList<>();
	private List<Merchandise> display = new ArrayList<>();
	private ArrayList<Card> shopCards = new ArrayList<>();
	private Equipment[] shopEquipments;
	
	public Shop(Scanner sc) {
		this.sc = sc;
	}
	
	public List<Merchandise> getLineups(Player player, EquipmentList el, CardList cl) {
		this.shopEquipments = el.useEquipmentList();
		this.shopCards = cl.useCardList();
		
		catalog.clear();
		lineup.clear();
		
		for (Card card : this.shopCards) {
			catalog.add(new CardMerchandise(card));
		}
		for (Equipment equipment : this.shopEquipments) {
			catalog.add(new EquipmentMerchandise(equipment));
		}
		
		Collections.shuffle(catalog);
		
		for (Merchandise merchandise : catalog) {
			if(merchandise.canBeSoldTo(player)) {
				lineup.add(merchandise);
			}
			if(lineup.size() == 4) {
				break;
			}
		}
		
		return lineup;
	}
	
	public void enter(Player player, EquipmentList el, CardList cl) {
		this.display = getLineups(player, el, cl);
		
		System.out.println("ショップに入りました。");
		
		while(this.display.size() > 0) {
			System.out.println("何を得る？" + "(所持金:" + player.getGold() + "G)");
			for(int i = 0; i < this.display.size(); i++) {
				int price = this.display.get(i).getPrice();
				String itemType = this.display.get(i).getType();
				System.out.println(i+1 + ":" + this.display.get(i).getName() + " (" + price +  "G)" + " (" + itemType + ")");
			}
			System.out.println("0:ショップを出る");
			
			int choiceNum = sc.nextInt() - 1;
			if(choiceNum == -1) {
				System.out.println("ショップを出ました。");
				System.out.println();
				break;
			}
			
			Merchandise chosenItem = this.display.get(choiceNum);
			int price = chosenItem.getPrice();
			if(player.getGold() < price) {
				System.out.println("金がねぇぞ！");
				System.out.println();
				continue;
			}
			if(player.getGold() >= price) {
				chosenItem.grantTo(player);
			}
			
			System.out.println(chosenItem.getName() + "を手に入れた！");
			this.display.remove(choiceNum);
			System.out.println();
		}
		
	}
}