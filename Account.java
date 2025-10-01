/**
 Abstract Class Account represents an account
 An Account has a balance and a minimum balance
 An Account can perform common behaviours like withdraw and deposit

 @author Sulaiman Bakali Mueden
 @version 1
*/

import java.io.Serializable;

public abstract class Account implements Serializable {
	private int balance;
	private final int MIN_BALANCE;

	public Account(int minBalance) {
		this.balance = 0;
		this.MIN_BALANCE = minBalance;
	}

	
	public void deposit(int amount) {
		this.balance += amount;
	}

	public abstract boolean withdraw(int amount);

	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int amount) {
		this.balance = amount;
	}

	public int getMinBalance() {
		return this.MIN_BALANCE;
	}


	/** Returns true or false depending on whether the account has enough
	 *  money to withdraw, based upon the amount and minimum balance passed arguments
	 * @param amount - amount user attempts to withdraw
	 * @param MIN_BALANCE - the minimum balance of the account
	 * */
	public boolean isOverWithdrawLimit(int amount) {
		if ((this.getBalance()-amount) < this.getMinBalance()) {
			return true;
		}
		else{
			return false;
		}
	}

	/** Method that must be overriden, that returns a string description
	 *  of the account.
	 *  The description will vary depending on which subclass it is
	 *  (i.e BasicAccount or PremiumAccount).
	 * */
	public abstract String getDescription();
}	

	