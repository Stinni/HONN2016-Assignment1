package is.ru.honn.P2_BanksRUs.Accounts;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The class SavingsAccount (SavingsAccount.java)
 * Implements the deposit and withdraw functions from
 * the Account class
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
public class SavingsAccount extends Account {

    private int nrOfAllowedTransactions;
    private int nrOfTransactionsDone;

    public SavingsAccount() {
        super();
        nrOfAllowedTransactions = 0;
        nrOfTransactionsDone = 0;
    }

    public SavingsAccount(int accNumber, int accOwner, String accName) {
        super(accNumber, accOwner, accName);
        nrOfAllowedTransactions = 0;
        nrOfTransactionsDone = 0;
    }

    /**
     * This constructor's only meant to be used when reading and populating the "database"
     * from a file.
     * @param aStatus The status of the account, true for active and false for inactive
     * @param b The account's balance
     */
    public SavingsAccount(int accNumber, int accOwner, boolean aStatus, String accName, double b,
                          int nrOfAllowedTs, int nrOfTsDone) {
        super(accNumber, accOwner, aStatus, accName, b);
        nrOfAllowedTransactions = nrOfAllowedTs;
        nrOfTransactionsDone = nrOfTsDone;
    }

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
            throw new IllegalArgumentException("Can't withdraw a negative amount!");
        } else if(nrOfTransactionsDone >= nrOfAllowedTransactions) {
            System.out.println("The limit of transactions has been reached!");
        } else if(balance < amount) {
            throw new UnsupportedOperationException("The balance is too low to withdraw that amount!");
        }else {
            balance -= amount;
            nrOfTransactionsDone++;
        }
    }

    @Override
    public String toString() {
        return "{\"accountNumber\": " + accountNumber + ", \"accountOwner\": " + accountOwner +
                ", \"accountStatus\": " + accountStatus + ", \"accountName\": \"" + accountName +
                "\", \"balance\": " + balance + ", \"nrOfAllowedTransactions\": " +
                nrOfAllowedTransactions + ", \"nrOfTransactionsDone\": " + nrOfTransactionsDone + "}";
    }
}
