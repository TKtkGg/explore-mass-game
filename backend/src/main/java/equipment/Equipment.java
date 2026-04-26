package equipment;

import java.util.Objects;

public class Equipment {
	private String name;
	private int atk;
	private int price;
	private int chance;
	
	public Equipment(String name, int atk, int price, int chance) {
		this.name = name;
		this.atk = atk;
		this.price = price;
		this.chance = chance;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public int getChance() {
		return this.chance;
	}
	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Equipment)) return false;
        Equipment other = (Equipment) obj;
        return this.atk == other.atk && Objects.equals(this.name, other.name);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(name, atk);
    }
}
