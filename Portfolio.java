/**
 Class Portfolio represents a portfolio
 It stores Stock's and Reciept's for each transaction

 @author Sulaiman Bakali Mueden
 @version 1
*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Portfolio implements Serializable {
	private User user;
	private ArrayList<Stock> assets;
	private ArrayList<Reciept> reciepts;

	public Portfolio(User user) {
		this.user = user;
		this.assets = new ArrayList<>();
		this.reciepts = new ArrayList<>();
	}
	
	/** Adds a stock to the ArrayList
	 *	Returning true or false depending on whether the stock was successfully added
	 */
	public boolean addStock(Stock stock) {
		if (user.withdraw(stock.getPrice())) {
			this.assets.add(stock);
			this.reciepts.add(new Reciept(stock, true));
			return true;
		}
		else {
			return false;
		}
	}

	public void removeStock(Stock stock) {
		this.assets.remove(stock);
		user.deposit(stock.getPrice());
		this.reciepts.add(new Reciept(stock, false));
	}


	public Object[] getPortfolioArray() {
		return this.assets.toArray();
	}

	public Reciept getLastReciept() {
		int lastIndex = reciepts.size() - 1;
		return reciepts.get(lastIndex);
	}

	public String toString() {
		String text = "";
		Iterator<Stock> it = assets.iterator();
		text += "Balance: £" + user.getBalance() + "\n";
		while (it.hasNext()) {
			Stock s = it.next();
			text += "Name: " + s.getName() + "\t";
			text += "Price: £" + s.getPrice() + "\n";
		}
		
		return text;
	}	
}