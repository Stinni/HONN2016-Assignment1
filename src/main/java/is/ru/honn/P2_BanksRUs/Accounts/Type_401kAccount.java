package is.ru.honn.P2_BanksRUs.Accounts;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The class Type_401kAccount (Type_401kAccount.java)
 * Implements the deposit and withdraw functions from
 * the Account class
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
public class Type_401kAccount extends Account {

    public Type_401kAccount() {
        super();
    }

    public Type_401kAccount(int accNumber, int accOwner, String accName) {
        super(accNumber, accOwner, accName);
    }

    /**
     * This constructor's only meant to be used when reading and populating the "database"
     * from a file.
     * @param aStatus The status of the account, true for active and false for inactive
     * @param b The account's balance
     */
    public Type_401kAccount(int accNumber, int accOwner, boolean aStatus, String accName, double b) {
        super(accNumber, accOwner, aStatus, accName, b);
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
        } else if(balance < amount) {
            throw new UnsupportedOperationException("The balance is too low to withdraw that amount!");
        } else {
            balance -= amount;
        }
    }
}
