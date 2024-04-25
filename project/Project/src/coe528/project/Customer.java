/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */

/**
 * Overview: 
 * A mutable class that represents a customer at a bank
 * Each customer has a username, password, role, associated bank account, and a level
 * This class manages banking operations such as deposits, withdrawals, and online purchases
 * Additional fees and operations vary by the customers level
 * 
 * Abstraction Function: 
 * Represents a customer of a bank
 * - username is a unique identifier for the customer
 * - password is used for authentication
 * - role is a fixed string that indicates the users role
 * - bankAccount represents the customers account with the bank and contains details like balance
 * - level indicates the customers status within the bank, which applies additional purchase fees
 * This allows for customers to interact with the bank account
 * 
 * Representation Invariant:
 * - username, password, and bankAccount must not be null
 * - role must equal "customer"
 * - level must be an instance of either SilverLevel, GoldLevel, or PlatinumLevel
 */

public class Customer {
    private final String username;
    private final String password;
    public String role = "customer";
    private final BankAccount bankAccount;
    public Level level;

    /**
     * Initializes a customer with a username, password, and initial balance, as well as sets the customers level based on their balance
     * 
     * @param username the customers username
     * @param password the customers password
     * @param balance  the initial balance of the customers account
     * @effects Initializes this with the new username, password, bankAccount, and sets the initial level
     * @modifies this.username, this.password, this.bankAccount, this.level
     * @requires balance >= 0
     */
    public Customer(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.bankAccount = new BankAccount(balance);
        this.level = new SilverLevel(this);
        checkLevel();
    }

    /**
     * Deposits a specified amount into the customers bank account
     * 
     * @param amount the amount to deposit
     * @effects Deposits the specified amount into the customers account and updates the level if needed
     * @modifies this.bankAccount, this.level
     * @requires amount > 0
     */
    public void deposit(double amount) {
        this.bankAccount.deposit(amount);
        checkLevel();
    }

    /**
     * Withdraws a specified amount from the customers bank account if their balance is sufficient
     * 
     * @param amount the amount to withdraw
     * @effects Withdraws the specified amount from the customers account and updates the level if needed
     * @modifies this.bankAccount, this.level
     * @requires amount > 0 && this.bankAccount.getBalance() >= amount
     */
    public void withdraw(double amount) {
        if (this.bankAccount.getBalance() >= amount) {
            this.bankAccount.withdraw(amount);
            checkLevel();
        }
    }

    /**
     * Makes a purchase of a specified amount if the balance is sufficient
     * 
     * @param amount the amount for the online purchase
     * @effects Makes a purchase by withdrawing the specified amount plus any additional fees determined by the level
     * @modifies this.bankAccount, this.level
     * @requires amount >= 50 && this.bankAccount.getBalance() >= amount + (fee determined by level)
     */
    public void purchase(double amount) {
        if (this.bankAccount.getBalance() >= amount && amount >= 50) {
            this.bankAccount.withdraw(level.purchase(amount));
            checkLevel();
        }
    }

    /**
     * Checks and updates the customers level based on their balance
     * 
     * @effects Checks and updates the customers level according to their balance
     * @modifies this.level
     */
    public void checkLevel() {
        this.level.changeLevel();        
        this.level.changeLevel();
    }

    /**
     * Sets the customers level
     * 
     * @param level the new level to set for the customer
     * @effects Sets the customers level to the specified level
     * @modifies this.level
     * @requires level != null
     */
    public void setLevel(Level level) {
        this.level = level;
    }
    
    /**
     * Returns the current balance from the customers account
     * 
     * @return The current balance of the customers bank account
     * @effects Returns the balance of this customers bank account without modifying anything
     */
    public double getBalance() {
        return this.bankAccount.getBalance();
    }
    
    /**
     * Checks the representation invariant of the Customer class
     * 
     * @return true if the rep invariant holds, otherwise false
     */
    public boolean repOk() {
        return username != null &&
                password != null &&
                bankAccount != null &&
                role.equals("customer") &&
                (level instanceof SilverLevel || 
                level instanceof GoldLevel ||
                level instanceof PlatinumLevel);
    }

    /**
     * Returns the string representation of the customer object
     * 
     * @return a string that represents the current Customer object state
     */
    public String toString() {
        return "Username: " + username + "\'" +
                "Role: " + role + "\'" +
                "Balance: " + bankAccount.getBalance() + "\'" +
                "Level: " + level.getClass().getSimpleName();
    }
    
}
