/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.dataDAO.dataRDB;

import com.ACME.data.Savings;
import com.ACME.dataDAO.SavingsDAO;
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
public class RDBSavingsDAO implements SavingsDAO
{
    // Instance Variables
    private Connection dbConnection = null;
    private HashSet savingsAccountList = new HashSet();
    
    private final String SQL_CREATE_SAVINGS="INSERT INTO ACME_BANK.SAVINGS(ACCNUM, C_ID)"+ " VALUES (?, ?)";
    private final String SQL_READ_SAVINGS_BY_C_ID="SELECT * FROM ACME_BANK.SAVINGS WHERE C_ID = ?";
    private final String SQL_READ_SAVINGS_BY_ACC_NUM="SELECT * FROM ACME_BANK.SAVINGS WHERE ACCNUM = ?";
    private final String SQL_GETALL_SAVINGS="SELECT * FROM ACME_BANK.SAVINGS";
    private final String SQL_UPDATE_SAVINGS="UPDATE ACME_BANK.SAVINGS SET BALANCE=? WHERE ACCNUM=?";
    private final String SQL_DELETE_SAVINGS_BY_C_ID="DELTE FROM ACME_BANK.SAVINGS WHERE C_ID=?";
    private final String SQL_DELETE_SAVINGS_BY_ACC_NUM="DELTE FROM ACME_BANK.SAVINGS WHERE ACCNUM=?";
    private final String SQL_VIEW_BALANCE="SELECT BALANCE FROM ACME_BANK.SAVINGS WHERE C_ID=?";
    private final String SQL_SET_BALANCE="UPDATE  ACME_BANK.SAVINGS SET BALANCE=? WHERE C_ID=?";

    // Constructor
    public RDBSavingsDAO(Connection connection)
    {
        this.dbConnection = connection;
    }

    @Override
    public void createSavingsAccount(Savings savingsAccount)
    {
        try
        {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_CREATE_SAVINGS, Statement.RETURN_GENERATED_KEYS);

            sqlStatement.setInt(1, savingsAccount.getAccNumber());
            sqlStatement.setInt(2, savingsAccount.getC_ID());
           
            sqlStatement.executeUpdate();

            ResultSet result = sqlStatement.getGeneratedKeys();
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public Savings readSavingsAccount(int accountNumber)
    {
        try
        {
           
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_READ_SAVINGS_BY_ACC_NUM);
            sqlStatement.setInt(1, accountNumber);

            ResultSet result = sqlStatement.executeQuery();
            result.next();

            Savings savingsAccount = new Savings(result.getInt("C_ID"),
                        result.getInt("ACCNUM"),
                        result.getDouble("BALANCE"));

            return savingsAccount;

        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public void updateSavingsAccount(Savings savingsAccount)
    {
        try
        {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_UPDATE_SAVINGS);
            sqlStatement.setDouble(1, savingsAccount.getBalance());
            sqlStatement.setInt(2, savingsAccount.getAccNumber());

            // execute insert SQL stetement
            sqlStatement.executeUpdate();


        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
            
        }
    }

    @Override
    public void deleteSavingsAccount(int accountNumber)
    {
        try
        {

            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_DELETE_SAVINGS_BY_ACC_NUM);
            sqlStatement.setInt(1, accountNumber);

            sqlStatement.executeUpdate();

        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public Collection getAllSavingsAccount()
    {
        try
        {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_GETALL_SAVINGS);

            ResultSet result = sqlStatement.executeQuery();

            while (result.next())
            {
                Savings savingsAccount = new Savings(result.getInt("C_ID"),
                        result.getInt("ACCNUM"),
                        result.getDouble("BALANCE"));

                savingsAccountList.add(savingsAccount);
            }
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }


        System.out.println("Savings SIZE: " + savingsAccountList.size());
        return savingsAccountList;
    }

   
}
