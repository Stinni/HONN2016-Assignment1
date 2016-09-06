package is.ru.honn.P3_Videos;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 3:
 * The class and program Videos (Videos.java)
 *
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 06.09.16
 */
public class Videos {

    private String filePath;

    public Videos() {
        filePath = "videos.json";
    }

    public void readFromFile() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jObj = (JSONObject) obj;
            //JSONArray allCustomers = (JSONArray) jObj.get("Customers");
            //JSONArray allAccounts = (JSONArray) jObj.get("Accounts");
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

        String cmd = "";
        while (!cmd.equalsIgnoreCase("q") && !cmd.equalsIgnoreCase("quit")) {
            System.out.println("Available commands:");
            System.out.println("p/print: Prints all accounts for all customers");
            System.out.println("pf/printfor: Prints all active accounts for a certain customer");
            System.out.println("pa/printall: Prints all customers and accounts");
            System.out.println("s/save: Saves all customers and accounts to files");
            System.out.println("q/quit: Terminates the program");
            System.out.print("Enter command: ");
            //cmd = in.next();
            if (cmd.equalsIgnoreCase("s") || cmd.equalsIgnoreCase("save")) {
            } else if (cmd.equalsIgnoreCase("p") || cmd.equalsIgnoreCase("print")) {
            } else if(cmd.equalsIgnoreCase("pf") || cmd.equalsIgnoreCase("printfor")) {
            } else if(cmd.equalsIgnoreCase("pa") || cmd.equalsIgnoreCase("printall")) {
            } else if(cmd.equalsIgnoreCase("wa")) {
            } else if(cmd.equalsIgnoreCase("wb")) {
            } else if(!(cmd.equalsIgnoreCase("q") || cmd.equalsIgnoreCase("quit"))) {
                System.out.println("Unknown command");
            }
        }
    }
}

/*
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


}
 */