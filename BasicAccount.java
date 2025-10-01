/**
 Class BasicAccount represents a basic account
 A basic account has a minimum balance of 0
 It cannot withdraw any amount that will cause its account to have a negative balance

 @author Sulaiman Bakali Mueden
 @version 1
 */

import java.io.Serializable;

public class BasicAccount extends Account implements Serializable{

    public BasicAccount() {
        super(0);
    }

    public boolean withdraw(int amount) {
        if (isOverWithdrawLimit(amount))
            return false;
        else {
            int balance = this.getBalance();
            int newBalance = (balance -= amount);
            this.setBalance(newBalance);
            return true;
        }
    }

    public String getDescription() {
        String description = "Basic Account\nMinimum Balance: " + this.getMinBalance();
        return description;
    }
}
