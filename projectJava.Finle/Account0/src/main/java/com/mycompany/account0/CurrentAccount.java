/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.account0;

/**
 *
 * @author salsabeel
 */
import java.util.Random;
 class CurrentAccount extends Account0 {
    private double overdraftLimit;
    
    public CurrentAccount(String accountNumber, String accountHolderName, double initialDeposit, double overdraftLimit) {
        super(accountNumber, accountHolderName, initialDeposit);
        this.overdraftLimit = overdraftLimit;
    }
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    // Override method to display account details
    @Override
    public void displayAccountDetails() {
        System.out.println("Account Type: Current Account");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder: " + getAccountHolderName());
        System.out.println("Balance: " + getBalance());
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }}
    // class for generating passwords 
    
    class PasswordGenerator {
    private String lowercase = "abcdefghijklmnopqrstuvwxyz";
    private String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String digits = "0123456789";
    private String specialChars = "@#$&*-_+";

    // Generate a random password
    public String generatePassword(int length, boolean includeUppercase, boolean includeDigits, boolean includeSpecialChars) {
        StringBuilder charPool = new StringBuilder(lowercase);

        if (includeUppercase) {
            charPool.append(uppercase);
        }
        if (includeDigits) {
            charPool.append(digits);
        }
        if (includeSpecialChars) {
            charPool.append(specialChars);
        }
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }
        return password.toString();
    }
}

