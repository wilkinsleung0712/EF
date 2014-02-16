/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.dataDAO.dataRDB;

import com.ACME.data.Transaction;
import com.ACME.dataDAO.TransactionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author lucasang
 */
public class RDBTransactionDAO implements TransactionDAO
{
    // Instance Variables
    private Connection dbConnection = null;
    private HashSet transactionList = new HashSet();
    
    private final String SQL_CREATE_TRANSACTIONS="INSERT INTO ACME_BANK.TRANSACTIONS(T_ID, ACCNUM,AMOUNT,DESCRIPTION )"+ " VALUES (?,?, ?, ?)";
    private final String SQL_READ_TRANSACTIONS="SELECT * FROM ACME_BANK.TRANSACTIONS WHERE T_ID = ?  AND ACCNUM=?";
    private final String SQL_UPDATE_TRANSACTIONS="UPDATE ACME_BANK.TRANSACTIONS SET ACCNUM=?, AMOUNT=? DESCRIPTION=? WHERE T_ID=?";
    private final String SQL_DELETE_TRANSACTIONS="DELTE FROM ACME_BANK.TRANSACTIONS WHERE T_ID=?";
    private final String SQL_GETALL_TRANSACTIONS="SELECT * FROM ACME_BANK.TRANSACTIONS";
    private final String SQL_GETALL_BY_T_ID_TRANSACTIONS="SELECT * FROM ACME_BANK.TRANSACTIONS WHERE T_ID LIKE ?";
    private final String SQL_GETALL_BY_ACC_NYM_TRANSACTIONS="SELECT * FROM ACME_BANK.TRANSACTIONS WHERE ACCNUM = ?";

    // Constructor
    public RDBTransactionDAO(Connection connection)
    {
        this.dbConnection = connection;
    }

    @Override
    public void createTransaction(Transaction transaction)
    {
        try
        {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    SQL_CREATE_TRANSACTIONS, Statement.RETURN_GENERATED_KEYS);

            sqlStatement.setInt(1, transaction.T_ID);
            sqlStatement.setInt(2, transaction.accNumber);
            sqlStatement.setDouble(3, transaction.amount);
            sqlStatement.setString(4, transaction.description);

            sqlStatement.executeUpdate();

            ResultSet result = sqlStatement.getGeneratedKeys();
            result.next();
            transaction.T_ID = result.getInt(1);
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public Transaction readTransaction(int transactionId, int accountNumber)
    {
        try
        {
            
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_READ_TRANSACTIONS);
            sqlStatement.setInt(1, transactionId);
            sqlStatement.setInt(2, accountNumber);

            ResultSet result = sqlStatement.executeQuery();

            result.next();

            Transaction transaction = new Transaction(result.getInt(1),
                    result.getInt(2), result.getDouble(3),
                    result.getString(4));

            return transaction;
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public Collection<Transaction> getAllTransactionAccount()
    {
        try
        {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_GETALL_TRANSACTIONS);

            ResultSet result = sqlStatement.executeQuery();

            while (result.next())
            {
                Transaction transactions = new Transaction(result.getInt(1),
                        result.getInt(2), result.getDouble(3), result.getString(4));

                transactionList.add(transactions);
            }
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }

        return transactionList;
    }

    @Override
    public Collection<Transaction> getAllTransactionAccountByAccNum() {
         try
        {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_GETALL_BY_ACC_NYM_TRANSACTIONS);

            ResultSet result = sqlStatement.executeQuery();

            while (result.next())
            {
                Transaction transactions = new Transaction(result.getInt(1),
                        result.getInt(2), result.getDouble(3), result.getString(4));

                transactionList.add(transactions);
            }
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }

        return transactionList;
    }
    
    
}
