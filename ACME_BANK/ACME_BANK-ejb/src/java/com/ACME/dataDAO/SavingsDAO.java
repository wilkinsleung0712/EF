/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.dataDAO;

import com.ACME.data.Savings;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author lucasang
 */
//Interface for RDBSavingsDAO
public interface SavingsDAO
{
    public void createSavingsAccount(Savings savingsAccount);
    public Savings readSavingsAccount(int accountNumber);
    public void updateSavingsAccount(Savings savingsAccount);
    public void deleteSavingsAccount(int accountNumber);
    public Collection<Savings> getAllSavingsAccount();
   
}
