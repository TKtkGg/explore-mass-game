package equipment;

public class EquipmentList {
	private Equipment[] equipments;
	public EquipmentList() {
		this.equipments = new Equipment[] {
			new Equipment("木の剣", 2, 50, 15),
			new Equipment("錆びた剣", 3, 100, 13),
			new Equipment("石の剣", 5, 300, 10),
			new Equipment("鉄の剣", 8, 1000, 6),
			new Equipment("鋼の剣", 12, 3000, 4),
			new Equipment("ダイヤモンドの剣", 20, 5000, 2),
			new Equipment("勇者の剣", 30, 10000, 1)
		};
	}
	public Equipment[] useEquipmentList() {
		return equipments.clone();
	}
	
	public Equipment getEquipmentByName(String name) {
		for (Equipment equipment : equipments) {
			if (equipment.getName().equals(name)) {
				return equipment;
			}
		}
		return null;
	}
}