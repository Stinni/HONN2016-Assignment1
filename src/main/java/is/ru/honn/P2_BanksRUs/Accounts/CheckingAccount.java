package is.ru.honn.P2_BanksRUs.Accounts;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The class CheckingAccount (CheckingAccount.java)
 * Implements the deposit and withdraw functions from
 * the Account class.
 * Is overdrawable and implements the IOverdrawable
 * class.
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
public class CheckingAccount extends Account implements IOverdrawable {

    private double allowedOverdraw;
    private int nrOfFreeWithdrawals;
    private int nrOfFreeWdDone;
    private double withdrawalFee;

    public CheckingAccount() {
        super();
        allowedOverdraw = 0.0;
        nrOfFreeWithdrawals = 0;
        nrOfFreeWdDone = 0;
        withdrawalFee = 0.0;
    }

    public CheckingAccount(int accNumber, int accOwner, String accName) {
        super(accNumber, accOwner, accName);
        allowedOverdraw = 0.0;
        nrOfFreeWithdrawals = 0;
        nrOfFreeWdDone = 0;
        withdrawalFee = 0.0;
    }

    /**
     * This constructor's only meant to be used when reading and populating the "database"
     * from a file.
     * @param aStatus The status of the account, true for active and false for inactive
     * @param b The account's balance
     */
    public CheckingAccount(int accNumber, int accOwner, boolean aStatus, String accName, double b,
                           double allowedOd, int numOfFreeWithdrawals, int numOfFreeWdDone, double wFee) {
        super(accNumber, accOwner, aStatus, accName, b);
        allowedOverdraw = allowedOd;
        nrOfFreeWithdrawals = numOfFreeWithdrawals;
        nrOfFreeWdDone = numOfFreeWdDone;
        withdrawalFee = wFee;
    }

    public void setNrOfFreeWithdrawals(int numOfFreeWithdrawals) {
        if(numOfFreeWithdrawals < 0) {
            System.out.println("The number of free withdrawals can't be negative!");
            return;
        }
        nrOfFreeWithdrawals = numOfFreeWithdrawals;
    }
    public int getNrOfFreeWithdrawals() {
        return nrOfFreeWithdrawals;
    }

    public void setNrOfFreeWdDone(int numOfFreeWdDone) {
        if(numOfFreeWdDone < 0) {
            System.out.println("The number of unused free withdrawals can't be negative!");
            return;
        }
        nrOfFreeWdDone = numOfFreeWdDone;
    }

    public int getNrOfFreeWdDone() {
        return nrOfFreeWdDone;
    }

    public void setWithdrawalFee(int wFee) {
        if(wFee < 0) {
            System.out.println("The withdrawal fee can't be negative!");
            return;
        }
        withdrawalFee = wFee;
    }

    public double getWithdrawalFee() {
        return withdrawalFee;
    }

    // Start of IOverdrawable interface methods
    public void setAllowedOverdrawAmount(double allowedOd) {
        if(allowedOd < 0) {
            System.out.println("The allowed overdraw amount can't be negative!");
        }
        allowedOverdraw = allowedOd;
    }

    public double getAllowedOverdrawAmount() {
        return allowedOverdraw;
    }
    // End of IOverdrawable interface methods

    @Override
    public void deposit(double amount) throws UnsupportedOperationException, IllegalArgumentException {
        if(!accountStatus) {
            throw new UnsupportedOperationException("This account isn't active!!!");
        } else if(amount < 0) {
            throw new IllegalArgumentException("Can't deposit a negative amount!");
        } else {
            balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) throws UnsupportedOperationException, IllegalArgumentException {
        if(!accountStatus) {
            throw new UnsupportedOperationException("This account isn't active!!!");
        } else if(amount < 0) {
            throw new IllegalArgumentException("Can't withdraw an negative amount!");
        } else if(nrOfFreeWdDone >= nrOfFreeWithdrawals) {
            double tmp = amount + withdrawalFee;
            if(balance < tmp) {
                throw new UnsupportedOperationException("The balance is too low to withdraw that amount!");
            } else {
                balance -= tmp;
                nrOfFreeWdDone++;
            }
        } else {
            if(balance < amount) {
                throw new UnsupportedOperationException("The balance is too low to withdraw that amount!");
            } else {
                balance -= amount;
                nrOfFreeWdDone++;
            }
        }
    }

    @Override
    public String toString() {
        return "{\"accountNumber\": " + accountNumber + ", \"accountOwner\": " + accountOwner +
                ", \"accountStatus\": " + accountStatus + ", \"accountName\": \"" + accountName +
                "\", \"balance\": " + balance + ", \"allowedOverdraw\": " + allowedOverdraw +
                ", \"nrOfFreeWithdrawals\": " + nrOfFreeWithdrawals + ", \"nrOfFreeWdDone\": " +
                nrOfFreeWdDone + ", \"withdrawalFee\": " + withdrawalFee + "}";
    }
}
