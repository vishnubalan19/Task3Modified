package com.bankapplication.customer;
public class Customer{
    private int customerId;
    private String name;
    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getCustomerId(){
        return customerId;
    }
    public String getName(){
        return name;
    }
}