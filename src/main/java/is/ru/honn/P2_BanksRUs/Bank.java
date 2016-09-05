package is.ru.honn.P2_BanksRUs;

import is.ru.honn.P2_BanksRUs.Accounts.*;
import is.ru.honn.P2_BanksRUs.Entities.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import org.json.simple.*;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The class Bank (Bank.java)
 * Keeps info about the Bank, it's customers and accounts
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
public class Bank {

    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;

    public Bank() {
        customers = new ArrayList<Customer>();
        accounts = new ArrayList<Account>();
    }

    public void prettyPrintAllAccounts() {
        JSONArray jArr = new JSONArray();
        for(Account a : accounts){
            jArr.add(a);
        }
        System.out.println(jArr.toJSONString());
    }

    public void prettyPrintAllCustomersToFile() {
        JSONArray jArr = new JSONArray();
        for(Customer c : customers){
            Customer tmp = (Customer)c;
            jArr.add(tmp);
        }
        printToFile(jArr, "AllCustomers.json");
    }

    public void prettyPrintAllAccountsToFile() {
        JSONArray jArr = new JSONArray();
        for(Account a : accounts){
            Account tmp = (Account)a;
            jArr.add(tmp);
        }
        printToFile(jArr, "AllAccounts.json");
    }

    /**
     * The function printToFile
     * refactored/moved code from two print to file functions
     * since they had the exact same code.
     * @param jArr JSONArray to be written to a file
     * @param filePath The path to the file
     */
    public void printToFile(JSONArray jArr, String filePath) {
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(jArr.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Bank theBank = new Bank();
        Calendar c = Calendar.getInstance();
        c.set(1981, 9, 1);
        Customer ctmp = new Customer(1, "Stinni", c.getTime(), "Flétturimi 33");
        theBank.customers.add(ctmp);
        CheckingAccount atmp = new CheckingAccount(100, 1, "bleh");
        theBank.accounts.add(atmp);

        String cmd = "";
        while(!cmd.equalsIgnoreCase("q") && !cmd.equalsIgnoreCase("quit")) {
            System.out.print("Enter command (save to write to file or q/Q to quit): ");
            cmd = in.next();
            if(cmd.equalsIgnoreCase("save")) {
                theBank.prettyPrintAllCustomersToFile();
                theBank.prettyPrintAllAccountsToFile();
            }
        }
    }
}
