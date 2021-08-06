package com.bankapplication.account;
public class Account{
    private int customerId;
    private int balance;
    private long accountNo;
    public void setAccountNo(long accountNo){
        this.accountNo = accountNo;
    }
    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }
    public long getAccountNo(){
        return this.accountNo;
    }
    public int getCustomerId(){
        return this.customerId;
    }
    public int getBalance(){
        return this.balance;
    }
}