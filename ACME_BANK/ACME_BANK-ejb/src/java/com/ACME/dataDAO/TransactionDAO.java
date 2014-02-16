/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.dataDAO;

import com.ACME.data.Transaction;
import java.util.Collection;

/**
 *
 * @author lucasang
 */
//Interface for RDBTransactionDAO
public interface TransactionDAO
{
    public void createTransaction(Transaction transaction);
    public Transaction readTransaction(int transactionId, int accountNumber);
    public Collection<Transaction> getAllTransactionAccount();
    public Collection<Transaction> getAllTransactionAccountByAccNum();
}
