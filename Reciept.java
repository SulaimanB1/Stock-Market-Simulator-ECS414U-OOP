/**
 Class Reciept represents a transaction reciept

 @author Sulaiman Bakali-Mueden
 @version 1
*/

import java.io.Serializable;

public class Reciept implements Serializable {
	private String stockName;
	private int stockPrice;
	private boolean isPurchase;

	public Reciept(Stock stock, boolean isPurchase) {
		this.stockName = stock.getName();
		this.stockPrice = stock.getPrice();
		this.isPurchase = isPurchase;
	}
	
	public String toString() {
		String transaction = "";
		if (this.isPurchase == true)
			transaction = "purchased";
		else
			transaction = "sold";

		return stockName + " was " + transaction + " for £" + stockPrice + ".";
	}
}