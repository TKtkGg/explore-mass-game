package item;

public abstract class HealItem extends Item {
	private int amount;
	public HealItem(String name, int amount) {
		super(name, "HEAL");
		this.amount = amount;
	}
	
	public int getAmount() {
		return this.amount;
	}
}
