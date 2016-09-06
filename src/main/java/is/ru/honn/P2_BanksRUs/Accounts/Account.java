package is.ru.honn.P2_BanksRUs.Accounts;

import java.text.SimpleDateFormat;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The Layer Supertype class Account (Account.java)
 * The functions deposit and withdraw are abstract
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
abstract public class Account {

    protected int accountNumber;
    // accountOwner: an integer that corresponds to a customerId
    protected int accountOwner;
    // accountStatus: true if it's active, false if it's closed
    protected boolean accountStatus;
    protected String accountName;
    protected double balance;

    public Account() {
        accountNumber = -1;
        accountOwner = -1;
        accountStatus = false;
        accountName = null;
        balance = 0;
    }

    public Account(int accNumber, int accOwner, String accName) {
        accountNumber = accNumber;
        accountOwner = accOwner;
        accountStatus = true;
        accountName = accName;
        balance = 0;
    }

    /**
     * This constructor's only meant to be used when reading and populating the "database"
     * from a file and for testing.
     *
     * @param accNumber the account number
     * @param accOwner the account's owner's Id
     * @param aStatus the status of the account, true for active and false for inactive
     * @param accName the account name
     * @param b the account's balance
     */
    public Account(int accNumber, int accOwner, boolean aStatus, String accName, double b) {
        accountNumber = accNumber;
        accountOwner = accOwner;
        accountStatus = aStatus;
        accountName = accName;
        balance = b;
    }

    public void setAccountNumber(int accNumber) {
        accountNumber = accNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountOwner(int accOwner) {
        accountOwner = accOwner;
    }

    public int getAccountOwner() {
        return accountOwner;
    }

    /**
     * The setAccountStatus function sets the accountStatus bool variable
     * to the parameter's value. Changing it to false will always work but
     * if any of the other variables haven't been set, it's not possible
     * to activate the account!
     *
     * @param accStatus bool value to set accountStatus to
     */
    public void setAccountStatus(boolean accStatus) {
        if(!accStatus) {
            accountStatus = accStatus;
        } else if(accStatus && !(accountNumber < 0 || accountOwner < 0 || accountName == null)) {
            accountStatus = accStatus;
        }
    }

    public boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountName(String accName) {
        accountName = accName;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void deposit(double amount) throws UnsupportedOperationException, IllegalArgumentException;

    public abstract void withdraw(double amount) throws UnsupportedOperationException, IllegalArgumentException;

    @Override
    public String toString() {
        return "{\"accountNumber\": " + accountNumber + ", \"accountOwner\": " + accountOwner +
               ", \"accountStatus\": " + accountStatus + ", \"accountName\": \"" + accountName +
               "\", \"balance\": " + balance + "}";
    }
}
