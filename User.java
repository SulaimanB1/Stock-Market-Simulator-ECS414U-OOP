/**
 Class User represents a user

 @author Sulaiman Bakali-Mueden
 @version 1
*/

import java.io.*;

public class User implements Serializable{
	private final Account account;
	private final Portfolio portfolio;

	public User(boolean accIsPremium) {
		if (accIsPremium){
			this.account = new PremiumAccount();
			this.portfolio = new Portfolio(this);
		}
		else {
			this.account = new BasicAccount();
			this.portfolio = new Portfolio(this);
		}

	}
	

	public void deposit(int amount) {
		this.account.deposit(amount);
	}

	public boolean withdraw(int amount) {
		return this.account.withdraw(amount);
	}

	public int getBalance() {
		return this.account.getBalance();
	}

	public String getAccountDescription() {
		return this.account.getDescription();
	}



	public boolean addStock(Stock stock) {
		return this.portfolio.addStock(stock);
	}

	public void removeStock(Stock stock) {
		this.portfolio.removeStock(stock);
	}

	public void printPortfolio() {
		System.out.println(this.portfolio);
	}

	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public Reciept getLastReciept() {
		return this.portfolio.getLastReciept();
	}
}