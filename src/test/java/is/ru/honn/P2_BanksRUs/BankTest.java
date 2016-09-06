package is.ru.honn.P2_BanksRUs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(3.0, theBank.);

    }

    @Test
    public void addAccount() throws Exception {

    }

    @Test
    public void depositToAccount() throws Exception {

    }

    @Test
    public void withdrawFromAccount() throws Exception {

    }

    @Test
    public void readEntitiesFromFile() throws Exception {

    }

    @Test
    public void fillCustomersArray() throws Exception {

    }

    @Test
    public void fillAccountsArray() throws Exception {

    }

    @Test
    public void findAllActiveAccountsFor() throws Exception {

    }

    @Test
    public void serializeAccounts() throws Exception {

    }

    @Test
    public void printToFile() throws Exception {

    }

    /*@Test
    public void toString() throws Exception {

    }*/

    @After
    public void tearDown() throws Exception {

    }
}