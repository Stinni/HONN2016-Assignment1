package is.ru.honn.P2_BanksRUs;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by KristinnHeiðar on 6.9.2016.
 */
public class BankTest {
    private Bank theBank;

    @Before
    public void setUp() throws Exception {
        theBank = new Bank("testData.json");
    }

    @Test
    public void testAddCustomer() throws Exception {
        assertEquals(3.0, (double) theBank.getCustomerCount(), 0.1);
        Calendar c = Calendar.getInstance();
        c.set(1988, 10, 19);
        theBank.addCustomer("Catherine", c.getTime(), "Menntavegur 10");
        assertEquals(4.0, (double) theBank.getCustomerCount(), 0.1);
    }

    @Test
    public void testAddAccount() throws Exception {
        assertEquals(6.0, (double) theBank.getAccountsCount(), 0.1);
        theBank.addAccount(2, 1, "testing");
        assertEquals(7.0, (double) theBank.getAccountsCount(), 0.1);
    }

    /*
    @Test // TODO: implement a few tests for deposits
    public void depositToAccount() throws Exception {

    }

    @Test // TODO: implement a few tests for withdrawals
    public void withdrawFromAccount() throws Exception {

    }
    */
}