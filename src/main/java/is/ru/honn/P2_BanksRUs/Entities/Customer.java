package is.ru.honn.P2_BanksRUs.Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The class Customer (Customer.java)
 * Keeps info about the Bank, it's customers and accounts
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 05.09.16
 */
public class Customer {
    private int customerId;
    private String customerName;
    private Date customerDateOfBirth;
    private String customerAddress;

    public Customer(int cId, String cName, Date cDateOfBirth, String cAddress) {
        customerId = cId;
        customerName = cName;
        customerDateOfBirth = cDateOfBirth;
        customerAddress = cAddress;
    }

    public void setCustomerId(int cId) {
        customerId = cId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerName(String cName) {
        customerName = cName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerDateOfBirth(Date cDateOfBirth) {
        customerDateOfBirth = cDateOfBirth;
    }

    public Date getCustomerDateOfBirth() {
        return customerDateOfBirth;
    }

    public void setCustomerAddress(String cAddress) {
        customerAddress = cAddress;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return "{\"customerId\": " + customerId + ", \"customerName\": \""+ customerName +
               "\", \"customerDateOfBirth\": \"" + formatter.format(customerDateOfBirth) +
                "\", \"customerAddress\": \"" + customerAddress + "\"}";
    }
}
