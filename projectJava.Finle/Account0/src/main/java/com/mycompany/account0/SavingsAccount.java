/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.account0;

import java.util.Random;

/**
 *
 * @author salsabeel
 */
 class SavingsAccount extends Account0 {
     private double interestRate;
     public SavingsAccount(String accountNumber,String accountHolderName,double initialDeposit,double interestRate ){
         super(accountNumber,accountHolderName,initialDeposit);
         this.interestRate=interestRate;
     }
     public double getInterestRate(){
         return interestRate;
     }
    // Override method to displayaccountdetails
    @Override
    public void displayAccountDetails() {
        System.out.println("Account Type: Savings Account");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder: " + getAccountHolderName());
        System.out.println("Balance: " + getBalance());
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}
