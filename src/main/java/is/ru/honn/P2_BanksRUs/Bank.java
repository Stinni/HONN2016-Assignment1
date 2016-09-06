package is.ru.honn.P2_BanksRUs;

import is.ru.honn.P2_BanksRUs.Accounts.*;
import is.ru.honn.P2_BanksRUs.Entities.*;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The class and program Bank (Bank.java)
 * Keeps info about the Bank, it's customers and accounts
 *
 * The main method is mainly for testing the program
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
public class Bank {

    private String filePath;
    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;

    public Bank() {
        filePath = "AllCustomersAndAccounts.json";
        customers = new ArrayList<Customer>();
        accounts = new ArrayList<Account>();
        readEntitiesFromFile();
    }

    public Bank(String fp) {
        filePath = fp;
        customers = new ArrayList<Customer>();
        accounts = new ArrayList<Account>();
        readEntitiesFromFile();
    }

    /**
     * The addCustomer adds a new customer to the "database" unless one or both of the
     * strings that hold the name and address are empty/null
     * @param cName The customer's name
     * @param cDateOfBirth The customer's date of birth
     * @param cAddress The customer's address
     */
    public void addCustomer(String cName, Date cDateOfBirth, String cAddress) {
        if(cName == null || cAddress == null) {
            System.out.println("A customer has to have a name and an address!");
            System.out.println("Please try again");
            return;
        }

        Customer newCustomer = new Customer(checkForNextCId(), cName, cDateOfBirth, cAddress);
        customers.add(newCustomer);
    }

    /**
     * Checks if the customers array includes a customer
     * with a certain Id and returns that customer or
     * null if not found.
     *
     * @param cId the customer's Id
     * @return the customer or null
     */
    private Customer findCustomer(int cId) {
        Customer cTmp = null;
        for(Customer c : customers) {
            if(c.getCustomerId() == cId) {
                cTmp = (Customer) c;
                break;
            }
        }
        return cTmp;
    }

    /**
     * Checks if the accounts array includes an account
     * with a certain accountNumber and returns that
     * account or null if not found.
     *
     * @param aNumber the customer's Id
     * @return the account or null
     */
    private Account findAccount(int aNumber) {
        Account aTmp = null;
        for(Account a : accounts) {
            if(a.getAccountNumber() == aNumber) {
                aTmp = (Account) a;
                break;
            }
        }
        return aTmp;
    }

    /* Here should be more customer related functions, like updating name or address
    * I'll leave a 'todo' here but please note that I didn't intend to implement more
    * customer related function at this time...
    * TODO: Implement more customer related functions    */


    public void addAccount(int type, int accOwner, String accName) {
        // There're only 3 types of accounts. Therefore it's enough to check if the
        // I would like to do this differently
        if(type >= 0 && type <= 2) {
            if(!(findCustomer(accOwner) == null)) {
                int accNumber = checkForNextAId();
                Account tmp;
                if (type == 0) {
                    tmp = new CheckingAccount(accNumber, accOwner, accName);
                } else if (type == 1) {
                    tmp = new SavingsAccount(accNumber, accOwner, accName);
                } else {
                    tmp = new Type_401kAccount(accNumber, accOwner, accName);
                }
                accounts.add(tmp);
            } else {
                System.out.println("A customer with Id: " + accOwner + " doesn't exist!");
            }
        } else {
            System.out.println("Invalid account type! Use 0, 1 or 2 accordingly.");
        }
    }

    public void depositToAccount(int accountNumber, double amount) {
        Account aTmp = findAccount(accountNumber);

        if(aTmp == null) {
            System.out.println("There's no account registered with Id: " + accountNumber);
        } else {
            try {
                aTmp.deposit(amount); // :)
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void withdrawFromAccount(int accountNumber, double amount) {
        Account aTmp = findAccount(accountNumber);

        if(aTmp == null) {
            System.out.println("There's no account registered with Id: " + accountNumber);
        } else if(aTmp instanceof Type_401kAccount) {
            Date customerDOB = findCustomer(aTmp.getAccountOwner()).getCustomerDateOfBirth();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -65);
            Date todayMinus65Years = cal.getTime();
            if(customerDOB.before(todayMinus65Years)) {
                try {
                    aTmp.withdraw(amount); // :)
                } catch(UnsupportedOperationException e) {
                    System.out.println(e.getMessage());
                } catch(IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Withdrawal from account not possible until the owner is 65 years old!");
            }
        } else {
            try {
                aTmp.withdraw(amount); // :)
            } catch(UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * New customers get an Id that is 1 greater than the number of customers
     * in the current "database." The given mock data follows that rule.
     * In future implementations, we would use a database or some other way
     * to create these unique Id's for new customers
     * @return customers array size + 1
     */
    private int checkForNextCId() {
        return customers.size() + 1;
    }

    /**
     * The same rule applies for account Id's as with the customer Id's
     * @return accounts array size + 1
     */
    private int checkForNextAId() {
        return accounts.size() + 1;
    }

    public void readEntitiesFromFile() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jObj = (JSONObject) obj;
            JSONArray allCustomers = (JSONArray) jObj.get("Customers");
            JSONArray allAccounts = (JSONArray) jObj.get("Accounts");
            fillCustomersArray(allCustomers);
            fillAccountsArray(allAccounts);

        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void fillCustomersArray(JSONArray allCustomers) {
        Iterator<JSONObject> iterator = allCustomers.iterator();
        while(iterator.hasNext()) {
            JSONObject jTmp = (JSONObject) iterator.next();

            // The only way I found to change an integer in json format to int was to first change it to a string
            int cId = Integer.parseInt(jTmp.get("customerId").toString());
            String cName = jTmp.get("customerName").toString();
            String cDOB = jTmp.get("customerDateOfBirth").toString();
            String[] cDOBArray = cDOB.split("-");
            int yearOfBirth = Integer.parseInt(cDOBArray[0]);
            int monthOfBirth = Integer.parseInt(cDOBArray[1]) - 1; // -1 because calendar counts months from 0
            int dayOfBirth = Integer.parseInt(cDOBArray[2]);
            Calendar cal = Calendar.getInstance();
            cal.set(yearOfBirth, monthOfBirth, dayOfBirth);
            Date cDateOfBirth = cal.getTime();
            String cAddress = jTmp.get("customerAddress").toString();

            Customer c = new Customer(cId, cName, cDateOfBirth, cAddress);
            customers.add(c);
        }
    }

    public void fillAccountsArray(JSONArray allAccounts) {
        Iterator<JSONObject> iterator = allAccounts.iterator();
        while(iterator.hasNext()) {
            JSONObject jTmp = (JSONObject) iterator.next();
            JSONObject cObj = (JSONObject) jTmp.get("CheckingAccount");
            JSONObject sObj = (JSONObject) jTmp.get("SavingsAccount");
            JSONObject tObj = (JSONObject) jTmp.get("Type_401kAccount");

            if(cObj != null) {
                int aNumber = Integer.parseInt(cObj.get("accountNumber").toString());
                int oNumber = Integer.parseInt(cObj.get("accountOwner").toString());
                boolean aStatus = Boolean.valueOf(cObj.get("accountStatus").toString());
                String aName = cObj.get("accountName").toString();
                double balance = (Double) cObj.get("balance");
                double allowedOd = (Double) cObj.get("allowedOverdraw");
                int nrOfFreeWithdrawals = Integer.parseInt(cObj.get("nrOfFreeWithdrawals").toString());
                int nrOfFreeWdDone = Integer.parseInt(cObj.get("nrOfFreeWdDone").toString());
                double wFee = (Double) cObj.get("withdrawalFee");

                CheckingAccount cTmp = new CheckingAccount(aNumber, oNumber, aStatus, aName, balance,
                        allowedOd, nrOfFreeWithdrawals, nrOfFreeWdDone, wFee);
                accounts.add(cTmp);
            } else if(sObj != null) {
                int aNumber = Integer.parseInt(sObj.get("accountNumber").toString());
                int oNumber = Integer.parseInt(sObj.get("accountOwner").toString());
                boolean aStatus = Boolean.valueOf(sObj.get("accountStatus").toString());
                String aName = sObj.get("accountName").toString();
                double balance = (Double) sObj.get("balance");
                int nrOfAllowedTs = Integer.parseInt(sObj.get("nrOfAllowedTransactions").toString());
                int nrOfTsDone = Integer.parseInt(sObj.get("nrOfTransactionsDone").toString());

                SavingsAccount sTmp = new SavingsAccount(aNumber, oNumber, aStatus, aName, balance,
                        nrOfAllowedTs, nrOfTsDone);
                accounts.add(sTmp);
            } else if(tObj != null) {
                int aNumber = Integer.parseInt(tObj.get("accountNumber").toString());
                int oNumber = Integer.parseInt(tObj.get("accountOwner").toString());
                boolean aStatus = Boolean.valueOf(tObj.get("accountStatus").toString());
                String aName = tObj.get("accountName").toString();
                double balance = (Double) tObj.get("balance");

                Type_401kAccount tTmp = new Type_401kAccount(aNumber, oNumber, aStatus, aName, balance);
                accounts.add(tTmp);
            }
        }
    }

    /**
     *
     */
    public void prettyPrintAllEntitiesToFile() {
        JSONObject jObj = serializeAccounts(accounts);
        JSONArray jArr = new JSONArray();
        for(Customer c : customers){
            Customer cTmp = (Customer)c;
            jArr.add(c);
        }
        jObj.put("Customers", jArr);
        printToFile(jObj, filePath);
    }

    public void prettyPrintAllAccounts() {
        System.out.println(serializeAccounts(accounts).toJSONString());
    }

    /**
     *
     */
    public void prettyPrintAllActiveAccountsFor() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the customer's Id: ");
        String cmd = in.next();
        try {
            int tmp = Integer.parseInt(cmd);
            prettyPrintAllActiveAccountsFor(tmp);
        } catch (NumberFormatException e) {
            System.out.println("You have to enter a number!");
        }
    }

    public void prettyPrintAllActiveAccountsFor(int customerId) {
        ArrayList<Account> tmpList = findAllActiveAccountsFor(customerId);
        if(tmpList.isEmpty()) {
            System.out.println("There're no active accounts for customer with Id: " + customerId);
        } else {
            System.out.println("All active accounts for customer with Id: " + customerId);
            System.out.println(serializeAccounts(tmpList).toJSONString());
        }
    }

    public ArrayList<Account> findAllActiveAccountsFor(int customerId) {
        ArrayList<Account> tmpList = new ArrayList<Account>();
        for(Account a : accounts) {
            Account tmpAccount = (Account)a;
            if(tmpAccount.getAccountOwner() == customerId && tmpAccount.getAccountStatus()) {
                tmpList.add(tmpAccount);
            }
        }
        return tmpList;
    }

    public JSONObject serializeAccounts(ArrayList<Account> acc) {
        JSONObject jObj = new JSONObject();
        JSONArray jArr = new JSONArray();
        for(Account a : acc){
            Account aTmp = (Account)a;
            JSONObject jTmp = new JSONObject();
            jTmp.put(aTmp.getClass().getSimpleName(), aTmp);
            jArr.add(jTmp);
        }
        jObj.put("Accounts", jArr);
        return jObj;
    }

    /**
     * The function printToFile
     * refactored/moved code from two print to file functions
     * since they had the exact same code.
     * @param jObj JSONObject to be written to a file
     * @param filePath The path to the file
     */
    public void printToFile(JSONObject jObj, String filePath) {
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(jObj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Something went wrong with opening or writing to the file");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String tmp = "------ List of all customers: ------\n";
        if(customers.isEmpty()) {
            tmp += "- The bank has no listed customers -\n";
        } else {
            for (Customer c : customers) {
                tmp += c.toString() + "\n";
            }
            tmp += "------ List of all accounts: -------\n";
            if(accounts.isEmpty()) {
                tmp += "- The bank has no listed accounts  -\n";
            } else {
                for(Account a : accounts) {
                    tmp += a.toString() + "\n";
                }
            }
        }
        return tmp;
    }

    /**
     * The main function creates an instance of the Bank class. If no argument is
     * present, the Bank tries to read from a default file path. If that fails,
     * some mock data is made and inserted into the Bank's "database."
     *
     * Saving
     * @param args Program arguments, leave empty or path for an input file
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Bank theBank;
        if(args.length == 0) {
            theBank  = new Bank();
            if(theBank.customers.isEmpty()) {
                Calendar c = Calendar.getInstance();
                c.set(1981, 9, 1);
                Customer cTmp1 = new Customer(1, "Kristinn", c.getTime(), "Menntavegur 1");
                theBank.customers.add(cTmp1);
                c.set(1981, 8, 27);
                Customer cTmp2 = new Customer(2, "Halldór", c.getTime(), "Skólavegur 2");
                theBank.customers.add(cTmp2);
                c.set(1951, 8, 5); // Just 1 day over 65 years old
                Customer cTmp3 = new Customer(3, "Freysteinn", c.getTime(), "Skólavegur 3");
                theBank.customers.add(cTmp3);
            }
            if(theBank.accounts.isEmpty()) {
                CheckingAccount aTmp1 = new CheckingAccount(1, 1, "Tékkareikningur");
                theBank.accounts.add(aTmp1);
                CheckingAccount aTmp2 = new CheckingAccount(2, 2, "Tékkareikningur");
                theBank.accounts.add(aTmp2);
                SavingsAccount aTmp3 = new SavingsAccount(3, 1, "Uppleið 75");
                theBank.accounts.add(aTmp3);
                Type_401kAccount aTmp4 = new Type_401kAccount(4, 2, "Lífeyrissparnaður");
                theBank.accounts.add(aTmp4);
                Type_401kAccount aTmp5 = new Type_401kAccount(5, 1, true, "Lífeyrir", 5000.0);
                theBank.accounts.add(aTmp5);
                Type_401kAccount aTmp6 = new Type_401kAccount(6, 3, true, "Lífeyrir", 5000.0);
                theBank.accounts.add(aTmp6);
            }
        } else {
            theBank  = new Bank(args[0]);
        }

        String cmd = "";
        while (!cmd.equalsIgnoreCase("q") && !cmd.equalsIgnoreCase("quit")) {
            System.out.println("Available commands:");
            System.out.println("p/print: Prints all accounts for all customers");
            System.out.println("pf/printfor: Prints all active accounts for a certain customer");
            System.out.println("pa/printall: Prints all customers and accounts");
            System.out.println("s/save: Saves all customers and accounts to files");
            System.out.println("q/quit: Terminates the program");
            System.out.print("Enter command: ");
            cmd = in.next();
            if (cmd.equalsIgnoreCase("s") || cmd.equalsIgnoreCase("save")) {
                theBank.prettyPrintAllEntitiesToFile();
            } else if (cmd.equalsIgnoreCase("p") || cmd.equalsIgnoreCase("print")) {
                theBank.prettyPrintAllAccounts();
            } else if(cmd.equalsIgnoreCase("pf") || cmd.equalsIgnoreCase("printfor")) {
                theBank.prettyPrintAllActiveAccountsFor();
            } else if(cmd.equalsIgnoreCase("pa") || cmd.equalsIgnoreCase("printall")) {
                System.out.println(theBank);
            } else if(cmd.equalsIgnoreCase("wa")) {
                theBank.withdrawFromAccount(5, 1000.0);
            } else if(cmd.equalsIgnoreCase("wb")) {
                theBank.withdrawFromAccount(6, 1000.0);
            } else if(!(cmd.equalsIgnoreCase("q") || cmd.equalsIgnoreCase("quit"))) {
                System.out.println("Unknown command");
            }
        }
    }
}
