/**
 Class Stock represents a stock
 Stocks can be added and removed from a user's Portfolio, in exchange for money in the user's Account

 @author Sulaiman Bakali-Mueden
 @version 1
*/

import java.io.Serializable;

public class Stock implements Serializable {
	private String name;
	private int price;

	public Stock(String name, int price){
		this.name = name;
		this.price = price;
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getPrice(){
		return this.price;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public void changePrice(int amount) {
		this.price = this.price + amount;
	}

	public String toString() {
		String text = "";

		text += "Name: " + this.getName() + " \n";
		text += "Price: " + this.getPrice() + " \n\n";

		return text;
	}
}