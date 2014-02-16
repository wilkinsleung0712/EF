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
public class Transaction
{
    //Instance Variables
    public int T_ID;
    public int accNumber;
    public double amount;
    public String description;
    public boolean result;
  
    
    public Random rand = new Random();
    public int max = 99999999;
    public int min = 1;

    //Constructor
    public Transaction(int accNumber, double amount, String description)
    {
        this.T_ID = rand.nextInt(max - min + 1) + min;
        this.accNumber = accNumber;
        this.amount = amount;
        this.description = description;
    }

    public Transaction(int T_ID, int accNumber, double amount, String description)
    {
        this.T_ID = T_ID;
        this.accNumber = accNumber;
        this.amount = amount;
        this.description = description;
    }
    
    //getter and setter
    
}
