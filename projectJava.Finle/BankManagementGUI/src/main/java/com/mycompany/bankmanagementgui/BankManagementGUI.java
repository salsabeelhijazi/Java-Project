


package com.mycompany.bankmanagementgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

// Abstract class representing a generic bank account
abstract class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public abstract void displayAccountDetails();
}

class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double initialDeposit, double interestRate) {
        super(accountNumber, accountHolderName, initialDeposit);
        this.interestRate = interestRate;
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Savings Account: " + getAccountNumber());
    }
}

class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolderName, double initialDeposit, double overdraftLimit) {
        super(accountNumber, accountHolderName, initialDeposit);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Current Account: " + getAccountNumber());
    }
}

class BankManagementSystem {
    private ArrayList<Account> accounts;

    public BankManagementSystem() {
        accounts = new ArrayList<>();
    }

    public void createSavingsAccount(String accountNumber, String accountHolderName, double initialDeposit, double interestRate) {
        accounts.add(new SavingsAccount(accountNumber, accountHolderName, initialDeposit, interestRate));
    }

    public void createCurrentAccount(String accountNumber, String accountHolderName, double initialDeposit, double overdraftLimit) {
        accounts.add(new CurrentAccount(accountNumber, accountHolderName, initialDeposit, overdraftLimit));
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}

public class BankManagementGUI extends JFrame {
    private BankManagementSystem bank;

    public BankManagementGUI() {
        bank = new BankManagementSystem();

        setTitle("Bank Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        JButton createSavingsButton = new JButton("Create Savings Account");
        JButton createCurrentButton = new JButton("Create Current Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        mainPanel.add(createSavingsButton);
        mainPanel.add(createCurrentButton);
        mainPanel.add(depositButton);
        mainPanel.add(withdrawButton);

        add(mainPanel, BorderLayout.CENTER);

        createSavingsButton.addActionListener(e -> createSavingsAccount());
        createCurrentButton.addActionListener(e -> createCurrentAccount());
        depositButton.addActionListener(e -> deposit());
        withdrawButton.addActionListener(e -> withdraw());
    }

    private void createSavingsAccount() {
        String accountNumber = JOptionPane.showInputDialog(this, "Enter Account Number:");
        String accountHolderName = JOptionPane.showInputDialog(this, "Enter Account Holder Name:");
        double initialDeposit = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Initial Deposit:"));
        double interestRate = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Interest Rate:"));
        bank.createSavingsAccount(accountNumber, accountHolderName, initialDeposit, interestRate);
        JOptionPane.showMessageDialog(this, "Savings Account Created Successfully!");
    }

    private void createCurrentAccount() {
        String accountNumber = JOptionPane.showInputDialog(this, "Enter Account Number:");
        String accountHolderName = JOptionPane.showInputDialog(this, "Enter Account Holder Name:");
        double initialDeposit = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Initial Deposit:"));
        double overdraftLimit = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Overdraft Limit:"));
        bank.createCurrentAccount(accountNumber, accountHolderName, initialDeposit, overdraftLimit);
        JOptionPane.showMessageDialog(this, "Current Account Created Successfully!");
    }

    private void deposit() {
        String accountNumber = JOptionPane.showInputDialog(this, "Enter Account Number:");
        Account account = bank.findAccount(accountNumber);
        if (account != null) {
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Deposit Amount:"));
            account.deposit(amount);
            JOptionPane.showMessageDialog(this, "Deposit Successful! New Balance: " + account.getBalance());
        } else {
            JOptionPane.showMessageDialog(this, "Account Not Found!");
        }
    }

    private void withdraw() {
        String accountNumber = JOptionPane.showInputDialog(this, "Enter Account Number:");
        Account account = bank.findAccount(accountNumber);
        if (account != null) {
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Withdrawal Amount:"));
            account.withdraw(amount);
            JOptionPane.showMessageDialog(this, "Withdrawal Successful! New Balance: " + account.getBalance());
        } else {
            JOptionPane.showMessageDialog(this, "Account Not Found!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankManagementGUI gui = new BankManagementGUI();
            gui.setVisible(true);
        });
    }
}