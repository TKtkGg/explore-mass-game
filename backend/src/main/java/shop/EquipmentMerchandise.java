package shop;

import java.util.ArrayList;

import character.Player;
import equipment.Equipment;

public class EquipmentMerchandise implements Merchandise {
	private final Equipment equipment;
	
	public EquipmentMerchandise(Equipment equipment) {
		this.equipment = equipment;
	}
	
	@Override
	public String getName() {
		return equipment.getName();
	}
	
	@Override
	public int getPrice() {
		return equipment.getPrice();
	}
	
	@Override
	public String getType() {
		return "武器";
	}
	
	@Override
	public boolean canBeSoldTo(Player player) {
		ArrayList<Equipment> ownEquipments = player.getOwnEquipment();
		boolean isPlayerHas = ownEquipments.stream().anyMatch(e -> e.getName().equals(equipment.getName()));
		
		return !isPlayerHas;
	}
	
	@Override
	public void grantTo(Player player) {
		player.setGold(player.getGold() - equipment.getPrice());
		player.gotEquipment(equipment);
	}

}
