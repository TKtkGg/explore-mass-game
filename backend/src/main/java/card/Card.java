package card;


public abstract class Card{	
	private String name;
	private String text;
	private int price;
	
	public Card(String name, String text, int price) {
		this.name = name;
		this.text = text;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getText() {
		return this.text;
	}
	
	public int getPrice() {
		return this.price;
	}
	
}
