package character;

public abstract class Character {
	private String name;
	private int level;
	private int max_hp;
	private int hp;
	private int atk;
	private int def;
	private int spd;
	private int exp;
	private int gold;
	private boolean isDefend = false;
	
	public Character(String name, int level, int max_hp, int atk, int def, int spd, int exp, int gold) {
		this.level = level;
		this.name = name;
		this.max_hp = max_hp;
		this.hp = max_hp;
		this.atk = atk;
		this.def = def;
		this.spd = spd;
		this.exp = exp;
		this.gold = gold;
	}
	
	public void status() {
		System.out.println(this.name);
		System.out.println("LV : " + this.level);
		System.out.println("HP : " + this.hp + "/" + this.max_hp);
		System.out.println("ATK : " + this.atk);
		System.out.println("DEF : " + this.def);
		System.out.println("SPD : " + this.spd);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getMaxHp() {
		return this.max_hp;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public int getSpd() {
		return this.spd;
	}
	
	public int getExp() {
		return this.exp;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void setDefend(boolean isDefend) {
		this.isDefend = isDefend;
	}
	
	public void attack(Character target) {
		int damage = this.calcDamage(target);
		int tagHp = target.getHp();
		target.setHp(tagHp - damage);
		System.out.println(this.name + "の攻撃！");
		System.out.println(damage + "ダメージ！");
		if(target.getHp() < 0) {
			target.setHp(0);
		}
		System.out.println("HP : " + target.getHp() + "/" + target.getMaxHp() + "(" + target.getName() + ")");
	}
	
	public int calcDamage(Character target) {
		double min = 0.5;
		double max = 1.0;
		double randomValue = Math.random() * (max - min) + min;
		int defendMultiplier = target.isDefend ? 1 : 3;
		int damage = (int) ((this.getAtk() - target.getDef() / defendMultiplier) * 5 * randomValue); 
		if(damage < 0) {
			damage = 0;
		}
		return damage;
	}
	
	public void defend() {
		this.isDefend = true;
		System.out.println(this.name + "は防御した！");
	}
	
	public boolean isAlive() {
		return this.hp > 0;
	}
	
	public int Heal(int heal) {
		this.hp += heal;
		if(this.hp > this.max_hp) {
			this.hp = this.max_hp;
		}
		return hp;
	}
	
	// Setter that updates max HP and adjusts current HP by the same difference so callers
	// that convert from increase -> set(get + increase) keep identical behavior.
	public void setMaxHp(int maxHp) {
		int diff = maxHp - this.max_hp;
		this.max_hp = maxHp;
		this.hp += diff;
		if (this.hp > this.max_hp) {
			this.hp = this.max_hp;
		}
	}
	
	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	public void setDef(int def) {
		this.def = def;
	}
	
	public void setSpd(int spd) {
		this.spd = spd;
	}
}