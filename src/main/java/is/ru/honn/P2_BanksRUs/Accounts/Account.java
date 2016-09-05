package is.ru.honn.P2_BanksRUs.Accounts;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The Layer Supertype class Account (Account.java)
 * The functions deposit and withdraw are abstract
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
abstract public class Account {

    private int accountNumber;
    // accountOwner: an integer that corresponds to a customerId
    private int accountOwner;
    // accountStatus: true if it's active, false if it's closed
    private boolean accountStatus;
    private String accountName;
    protected double balance;

    // Default constructor sets accountStatus to false, balance to 0 and other
    // variables to -1 or null so that the account won't get used before setting
    // all the variables.
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

    public void setAccountStatus(boolean accStatus) {
        if(accStatus && !(accountNumber < 0 || accountOwner < 0 || accountName == null)) {
            accountStatus = accStatus;
        } else {
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

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);
}
