package is.ru.honn.P2_BanksRUs.Accounts;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The class CheckingAccount (CheckingAccount.java)
 * Implements the deposit and withdraw functions from
 * the Account class
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
public class CheckingAccount extends Account {

    public CheckingAccount() {
        super();
    }

    public CheckingAccount(int accNumber, int accOwner, String accName) {
        super(accNumber, accOwner, accName);
    }

    @Override
    public void deposit(double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Can't deposit a negative amount!");
        } else {
            balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) {
        if(balance < amount) {
            throw new UnsupportedOperationException("The balance is too low to withdraw that amount!");
        } else if(amount < 0) {
            throw new IllegalArgumentException("Can't withdraw a negative amount!");
        } else {
            balance -= amount;
        }
    }
}
