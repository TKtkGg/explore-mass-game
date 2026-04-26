import java.util.Scanner;

import character.Player;
import equipment.Equipment;
import equipment.EquipmentList;
import explore.Move;
import treasure.EquipmentTreasure;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		EquipmentList el = new EquipmentList();
		EquipmentTreasure et = new EquipmentTreasure(el, sc);
		Equipment default_equipment = el.useEquipmentList()[0];
		Player p = new Player("TKG", 1, 100, 5, 5, 5, 0 ,default_equipment, sc);
		Move mv = new Move(p, el, et, sc);
		
		System.out.println();
		mv.explore(p);
		
	}

}