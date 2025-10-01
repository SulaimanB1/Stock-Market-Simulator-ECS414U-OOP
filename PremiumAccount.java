/**
 Class PremiumAccount represents a premium account
 A premium account has a minimum balance of -100 and an interest rate of 5%
 Upon each withdraw (including when purchasing stocks), an extra 5% of the amount is deducted

 @author Sulaiman Bakali Mueden
 @version 1
 */

import java.io.Serializable;

public class PremiumAccount extends Account implements Serializable {
    private final double interestRate = 0.05;


    public PremiumAccount() {
        super(-100);
    }


    /** Determines whether @param amount is over the accounts withdraw limit,
     *  and if not calculates the amount after applying 5% interest and withdraws the amount
     *  It returns true if the withdraw was successful, and false is the withdraw was unsuccessful.
     *  */
    public boolean withdraw(int amount) {
        if (isOverWithdrawLimit(amount))
            return false;
        else {
            int balance = this.getBalance();
            int amountAfterInterest = calculateActualAmount(amount);
            int newBalance = (balance -= amountAfterInterest);
            this.setBalance(newBalance);
            return true;
        }
    }



    public double getInterestRate() {
        return interestRate;
    }

    /** Calculates and returns the integer amount after applying 5% interest and rounding the number
     * @param amount - the amount of money the user wishes to withdraw
     * */
    private int calculateActualAmount(int amount) {
        return (int) Math.round((amount * (1+interestRate)));
    }


    public String getDescription() {
        String description = "Premium Account\nMinimum Balance: " + this.getMinBalance() + "\nInterest Rate: " + getInterestRate();
        return description;
    }
}
