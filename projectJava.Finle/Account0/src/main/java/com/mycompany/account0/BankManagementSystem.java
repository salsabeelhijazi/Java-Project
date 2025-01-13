/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.account0;

/**
 *
 * @author salsabeel
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

 class BankManagementSystem{
    private ArrayList<Account0> accounts;

    public BankManagementSystem() {
        accounts = new ArrayList<>();
    }

    public void saveAccountToFile(Account0 account0) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true))) {
            if (account0 instanceof SavingsAccount) {
                SavingsAccount savings = (SavingsAccount) account0;
                writer.write("SavingsAccount," + account0.getAccountNumber() + "," +
                        account0.getAccountHolderName() + "," + account0.getBalance() + "," +
                        savings.getInterestRate());
            } else if (account0 instanceof CurrentAccount) {
                CurrentAccount current = (CurrentAccount) account0;
                writer.write("CurrentAccount," + account0.getAccountNumber() + "," +
                        account0.getAccountHolderName() + "," + account0.getBalance() + "," +
                        current.getOverdraftLimit());
            }
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String accountNumber = parts[1];
                String accountHolderName = parts[2];
                double balance = Double.parseDouble(parts[3]);

                if (type.equals("SavingsAccount")) {
                    double interestRate = Double.parseDouble(parts[4]);
                    accounts.add(new SavingsAccount(accountNumber, accountHolderName, balance, interestRate));
                } else if (type.equals("CurrentAccount")) {
                    double overdraftLimit = Double.parseDouble(parts[4]);
                    accounts.add(new CurrentAccount(accountNumber, accountHolderName, balance, overdraftLimit));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void createSavingsAccount(String accountNumber, String accountHolderName, double initialDeposit, double interestRate) 
    {
        Scanner scanner = new Scanner(System.in);
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        System.out.print("Enter password length: ");
        int length = scanner.nextInt();
        if (length <= 0) {
            System.out.println("Password length must be greater than 0.");
            return;
        }

        System.out.print("Include uppercase? (true/false): ");
        boolean includeUppercase = scanner.nextBoolean();

        System.out.print("Include digits? (true/false): ");
        boolean includeDigits = scanner.nextBoolean();

        System.out.print("Include special characters? (true/false): ");
        boolean includeSpecialChars = scanner.nextBoolean();

        String password = passwordGenerator.generatePassword(length, includeUppercase, includeDigits, includeSpecialChars);
        System.out.println("Generated Password: " + password);

        Account0 newAccount = new SavingsAccount(accountNumber, accountHolderName, initialDeposit, interestRate);
        accounts.add(newAccount);
        saveAccountToFile(newAccount);
        System.out.println("Savings account created successfully!");
    }

    public void createCurrentAccount(String accountNumber, String accountHolderName, double initialDeposit, double overdraftLimit) {
        Scanner scanner = new Scanner(System.in);
       PasswordGenerator passwordGenerator = new PasswordGenerator();

        System.out.print("Enter password length: ");
        int length = scanner.nextInt();
        if (length <= 0) {
            System.out.println("Password length must be greater than 0.");
            return;
        }

        System.out.print("Include uppercase? (true/false): ");
        boolean includeUppercase = scanner.nextBoolean();

        System.out.print("Include digits? (true/false): ");
        boolean includeDigits = scanner.nextBoolean();

        System.out.print("Include special characters? (true/false): ");
        boolean includeSpecialChars = scanner.nextBoolean();

        String password = passwordGenerator.generatePassword(length, includeUppercase, includeDigits, includeSpecialChars);
        System.out.println("Generated Password: " + password);

        Account0 newAccount = new CurrentAccount(accountNumber, accountHolderName, initialDeposit, overdraftLimit);
        accounts.add(newAccount);
        saveAccountToFile(newAccount);
        System.out.println("Current account created successfully!");
    }

    public Account0 findAccount(String accountNumber) {
        for (Account0 account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("Account not found.");
        return null;
    }

    public void showAccountDetails(String accountNumber) {
        Account0 account = findAccount(accountNumber);
        if (account != null) {
            account.displayAccountDetails();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankManagementSystem bank = new BankManagementSystem();

        bank.loadAccountsFromFile();

        while (true) {
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Show Account Details");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String savingsAccountNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String savingsHolderName = scanner.nextLine();
                    System.out.print("Enter initial deposit: ");
                    double savingsInitialDeposit = scanner.nextDouble();
                    System.out.print("Enter interest rate: ");
                    double interestRate = scanner.nextDouble();
                    bank.createSavingsAccount(savingsAccountNumber, savingsHolderName, savingsInitialDeposit, interestRate);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String currentAccountNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String currentHolderName = scanner.nextLine();
                    System.out.print("Enter initial deposit: ");
                    double currentInitialDeposit = scanner.nextDouble();
                    System.out.print("Enter overdraft limit: ");
                    double overdraftLimit = scanner.nextDouble();
                    bank.createCurrentAccount(currentAccountNumber, currentHolderName, currentInitialDeposit, overdraftLimit);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String detailsAccountNumber = scanner.nextLine();
                    bank.showAccountDetails(detailsAccountNumber);
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    String depositAccountNumber = scanner.nextLine();
                    Account0 depositAccount = bank.findAccount(depositAccountNumber);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                    }
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    String withdrawAccountNumber = scanner.nextLine();
                    Account0 withdrawAccount = bank.findAccount(withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    }
                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}



