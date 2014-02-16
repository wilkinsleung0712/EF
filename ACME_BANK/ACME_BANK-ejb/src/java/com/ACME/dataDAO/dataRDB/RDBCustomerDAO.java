/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.dataDAO.dataRDB;

import com.ACME.data.Customer;
import com.ACME.dataDAO.CustomerDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author lucasang
 */
public class RDBCustomerDAO implements CustomerDAO
{
    

    // Instance Variables
    private Connection dbConnection = null;
    private Collection<Customer> customerList = new ArrayList();

    private final String SQL_CREATE_CUSTOMER="INSERT INTO ACME_BANK.CUSTOMER(C_ID, FIRSTNAME, LASTNAME, DOB, ADDRESS)"+ " VALUES (?,?, ?, ?, ?)";
    private final String SQL_READ_CUSTOMER="SELECT C_ID FROM ACME_BANK.CUSTOMER WHERE FIRSTNAME = ? AND LASTNAME=? AND ADDRESS=? AND DOB=?";
    private final String SQL_READBYID_CUSTOMER="SELECT * FROM ACME_BANK.CUSTOMER WHERE C_ID=?";
    private final String SQL_UPDATE_CUSTOMER="UPDATE ACME_BANK.CUSTOMER SET FIRSTNAME=?, LASTNAME=?,DOB=?,ADDRESS=?,EMPLOYER = ?, CURRENTJOB = ?, SALARYPERYEAR = ?,CONTACT = ? WHERE C_ID=?";
    private final String SQL_DELETE_CUSTOMER="DELTE FROM ACME_BANK.CUSTOMER WHERE C_ID=?";
    private final String SQL_DELETE_CUSTOMER_SAVINGS="DELTE FROM ACME_BANK.SAVINGS WHERE C_ID=?";
    private final String SQL_GETALL_CUSTOMER="SELECT * FROM ACME_BANK.CUSTOMER";
    
    // Constructor
    public RDBCustomerDAO(Connection connection)
    {
        this.dbConnection = connection;
    }

    // Methods
    @Override
    public void createCustomer(Customer customer)
    {
        try
        {
            //check if the customer existed in the database
            if(!checkCustomerExisted(customer)){
                 PreparedStatement sqlStatement = dbConnection.prepareStatement(
                 SQL_CREATE_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
                sqlStatement.setInt(1, customer.getC_ID());
                sqlStatement.setString(2, customer.getFirstName());
                sqlStatement.setString(3, customer.getLastName());
                sqlStatement.setDate(4, (Date) customer.getDateOfBirth());
                sqlStatement.setString(5, customer.getAddress());

                sqlStatement.executeUpdate();

               // ResultSet result = sqlStatement.getGeneratedKeys();
                //result.next();
             }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }
    
    @Override
    public boolean checkCustomerExisted(Customer customer){
        try
        {
            
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_READ_CUSTOMER);
            //SELECT C_ID FROM ACME_BANK.CUSTOMER WHERE FIRSTNAME = ? AND LASTNAME=? AND ADDRESS=?
            sqlStatement.setString(1, customer.getFirstName());
            sqlStatement.setString(2, customer.getLastName());
            sqlStatement.setString(3, customer.getAddress());
            sqlStatement.setDate(4, customer.getDateOfBirth());
            
            
            ResultSet result = sqlStatement.executeQuery();
               return result.next();
            
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
            return false;
        }
       
    }

    @Override
    public Customer readCustomer(int customerId)
    {
        try
        {
            
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_READBYID_CUSTOMER);
            sqlStatement.setInt(1, customerId);

            ResultSet result = sqlStatement.executeQuery();

            result.next();

            Customer customer = new Customer(result.getInt("C_ID"),
                    result.getString("FIRSTNAME"), result.getString("LASTNAME"),
                    result.getDate("DOB"), result.getString("ADDRESS"));

            return customer;
            
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
            return null;
        }
    }
    
    

    @Override
    public void updateCustomer(Customer customer)
    {
        try
        {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_UPDATE_CUSTOMER);
            sqlStatement.setString(1, customer.getFirstName());
            sqlStatement.setString(2, customer.getLastName());
            sqlStatement.setDate(3, (Date) customer.getDateOfBirth());
            sqlStatement.setString(4, customer.getAddress());
            sqlStatement.setString(5, customer.getEmployer());
            sqlStatement.setString(6, customer.getCurrentJob());
            sqlStatement.setDouble(7, customer.getSalaryPerYear());
            sqlStatement.setString(8, customer.getContact());
            sqlStatement.setInt(9, customer.getC_ID());


            sqlStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void deleteCustomer(int customerId)
    {
        try
        {
          
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_DELETE_CUSTOMER_SAVINGS);
            sqlStatement.setInt(1, customerId);


            sqlStatement.executeUpdate();

        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public Collection getAllCustomers()
    {
        try
        {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(SQL_GETALL_CUSTOMER);

            ResultSet result = sqlStatement.executeQuery();

            while (result.next())
            {

                Customer customer = new Customer(result.getInt("C_ID"),
                        result.getString("FIRSTNAME"), result.getString("LASTNAME"),
                        result.getDate("DOB"), result.getString("ADDRESS"), 
                        result.getString("PASSWORD"), result.getString("EMPLOYER"),
                        result.getString("CURRENTJOB"), result.getDouble("SALARYPERYEAR"), 
                        result.getString("CONTACT"));

                customerList.add(customer);
            }

        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }

        return customerList;
    }
}