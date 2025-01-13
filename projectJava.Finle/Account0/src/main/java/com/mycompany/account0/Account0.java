/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.account0;

/**
 *
 * @author salsabeel
 */
abstract class Account0 {

    private String accountNumber;
    private String accountHolderName;
    private double balance;
    
    public Account0(String accountNumber , String accountHolderName , double balance){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
   public String getAccountNumber(){
       return accountNumber;
   }
   public String getAccountHolderName(){
       return accountHolderName;
   }
   public double getBalance(){
       return balance;
   }
   
   // function to deposit
   public void deposit(double amount){
       if(amount>0){
           balance+=amount;
           System.out.println("Deposit successful .");
           System.out.println("New balance: " + balance );
       } 
       else {
           System.out.println("Invalid deposit amount");
       }
   }
   // function withdraw
    public void withdraw(double amount) {
        if (amount>0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful.");
            System.out.println("New balance :"+balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
    public abstract void displayAccountDetails(); 
}
