package explore;

import java.util.Scanner;

public class TextEnter {
	Scanner sc;
	public TextEnter(Scanner sc) {
		this.sc = sc;
	}
	
	public void textEnter(String text) {
		System.out.println(text + "(Enterで進む)");
		sc.nextLine();
	}
}