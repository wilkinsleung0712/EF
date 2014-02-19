/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.data;

import java.util.Random;

/**
 *
 * @author lucasang
 */
public class Savings
{
    //Instance Variables
    private int C_ID;
    private int accNumber;
    private double balance;
    private Random rand = new Random();
    private int max = 999999999;
    private int min = 1;

    //Constructor
    public Savings(int C_ID)
    {
        this.C_ID = C_ID;
        this.accNumber = rand.nextInt(max - min + 1) + min;
       
    }

    public Savings(int C_ID, int accNumber)
    {
        this.C_ID = C_ID;
        this.accNumber = accNumber;
        
    }
    
    public Savings(int C_ID, int accNumber,double balance )
    {
        this.C_ID = C_ID;
        this.balance=balance;
        this.accNumber = accNumber;
       
    }
    
    //auto generated getter and setter
    public int getC_ID() {
        return C_ID;
    }

    private void setC_ID(int C_ID) {
        this.C_ID = C_ID;
    }

    public int getAccNumber() {
        return accNumber;
    }

    private void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public double getBalance() {
        return balance;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    public Random getRand() {
        return rand;
    }

    private void setRand(Random rand) {
        this.rand = rand;
    }

    public int getMax() {
        return max;
    }

    private void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    
    public void makeDeposit(double amount){
        this.balance+=amount;
    }
    
    public boolean withDraw(double amount){
        if(balance>amount){
            this.balance-=amount;
            return true;
        }
        return false;
    }
}
