package treasure;

import java.util.ArrayList;
import java.util.Scanner;

import character.Player;
import equipment.Equipment;
import equipment.EquipmentList;
import explore.TextEnter;

public class EquipmentTreasure extends Treasure {
	TextEnter text;
	Equipment equipment;
	ArrayList<Equipment> equipmentArray = new ArrayList<>();
	public EquipmentTreasure(EquipmentList list, Scanner sc) {
		super("装備宝箱");
		Equipment[] equipOption = list.useEquipmentList();
		for (Equipment eq : equipOption) {
			for (int i = 0; i < eq.getChance(); i++) {
				this.equipmentArray.add(eq);
			}
		}
		this.text = new TextEnter(sc);
	}
	
	public void open(Player player) {
		int randomIndex = (int) (Math.random() * equipmentArray.size());
		Equipment receiveEquipment = equipmentArray.get(randomIndex);
		
		String eqName = receiveEquipment.getName();
		int eqAtk = receiveEquipment.getAtk();
		
		System.out.println(this.name + "を見つけた！");
		System.out.println(eqName + "(ATK:" + eqAtk + ")を手に入れた！");
		text.textEnter("");
		ArrayList<Equipment> equipments = player.getOwnEquipment();
		if(equipments.contains(receiveEquipment)) {
			System.out.println("すでに持っている装備なので、売っぱらった。");
			player.calcGold(receiveEquipment.getPrice() / 2);
			text.textEnter("");
		} else {
			player.gotEquipment(receiveEquipment);
			player.equip();
		}
		return;
	}
}
