/*
a class to define a BankAccount object that stores information about a specific, single
BankAccount with that account's owner info and current balance
the balance of the account will be updated using deposit and withdrawal transactions
 */

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BankAccount {
    //data variables, instance variables (these are the values stored in each object separately)
    private double balance; //warning!  this is NOT a great choice for storing money amounts
    private String customerName;
    private final String accountNumber; //this part of the object will be immutable
    private ArrayList<TransactionRecord> allTransactions; //an ArrayList to store records of every transaction on this account

    //class variables, static variables (these are variables shared by all objects, stored once in the class)
    private static int nextAccountNumber = 1;

    //constructor methods: two versions
    //one version to take in an initial deposit
    //one version to open an account with a default $0 balance
    //this is called constructor overloading

    public BankAccount(double initialDeposit, String name) {
        if (initialDeposit >= 0) {
            balance = initialDeposit;
            customerName = name;
            accountNumber = Integer.toString(nextAccountNumber); //use the next available number, as a String
            nextAccountNumber++; //since we just used the number for this new account, increment it for the next new account
            allTransactions = new ArrayList<TransactionRecord>(); //create a fresh, empty ArrayList for this account
        }
        else {
            throw new IllegalArgumentException("cannot open an account with negative initial deposit");
        }
    }

    public BankAccount(String name) {
        //assume in this constructor the account is opened with $0 balance
        //we can use "constructor chaining" to do the actual work in the constructor above
        //this way we only have the code written in one place and other constructors just call that code
        this(0, name); //pass the information to the constructor above and let it run
    }

    /*
    method that overrides the default version that Java provides (which just gives the memory address)
    this method returns a printable version of this object
     */
    public String toString() {
        return "Account #" + accountNumber + " belongs to " + customerName +
                " with current balance $" + String.format("%.2f", balance);
    }

    /*
    method to simulate a withdrawal of money from this account
     */
    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("cannot withdraw $" + String.format("%.2f", amount) +
                    " from this account, current balance is $" + String.format("%.2f", balance));
        } else if (amount < 0) {
            throw new IllegalArgumentException("cannot withdraw a negative amount");
        }
        transaction(-amount);
    }

    /*
    method to simulate a deposit of money to this account
     */
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("cannot deposit a negative amount");
        }
        transaction(amount);
    }

    /*
    private helper method to do the actual work of updating the account
    after a transaction (a withdrawl or a deposit)
     */
    private void transaction(double amount) {
        //note: assume here that the amount is positive for deposits, negative for withdrawals
        balance += amount; //update the balance with the given amount
        //also, we want to record this transaction in the log of all transactions on this account
        //so we create a new TransactionRecord object (with this transaction's info) and
        //add it to the ArrayList that stores all of them for this account
        allTransactions.add(new TransactionRecord(amount, balance, LocalDateTime.now()));
    }

    public ArrayList<TransactionRecord> getAllTransactions() {
        return allTransactions;
    }


    /*
    this method prints the transactions on this account directly, instead of returning the ArrayList
    the printout will generally be more readable, "prettier" using this method
     */
    public void displayAllTransactions() {
        System.out.println(this.toString()); //print the basic info of this account to start
        //next we need to loop through the ArrayList and retrieve each TransactionRecord one at a time
        //and print each transaction's info on separate lines
        //we will use an "enhanced for loop" to do this
        for (TransactionRecord t : allTransactions) {
            //this loop will get the next TransactionRecord from the ArrayList allTransactions
            //and store it in the variable t so that we can print it
            System.out.println(t); //print the current transaction
        }
    }

}
