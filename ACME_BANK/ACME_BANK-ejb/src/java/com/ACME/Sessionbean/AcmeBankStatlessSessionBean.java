/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.Sessionbean;

import com.ACME.data.Customer;
import com.ACME.data.Savings;
import com.ACME.data.Transaction;
import com.ACME.dataDAO.CustomerDAO;
import com.ACME.dataDAO.SavingsDAO;
import com.ACME.dataDAO.TransactionDAO;
import com.ACME.dataDAO.dataRDB.RDBCustomerDAO;
import com.ACME.dataDAO.dataRDB.RDBSavingsDAO;
import com.ACME.dataDAO.dataRDB.RDBTransactionDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author WEIQIANG LIANG
 */
@Stateless
public class AcmeBankStatlessSessionBean implements AcmeBankStatlessSessionBeanRemote {
    @Resource(lookup="jdbc/myACMEDatasource")
    private DataSource dataSource;
    private Connection connection;  
    
    
     //PostConstruct annotation allow method to perform initialization
    //PostActivate annotation allow method receive callback after session bean activated
    @PostConstruct
    @PostActivate
    public void initialize()
    {
        try
        {
            connection = dataSource.getConnection();
            
            
            System.out.println("initialize()");
        }
        catch (SQLException sqle)
        {
            System.out.println("initialize exception");
            System.out.println(sqle.getMessage());
        }
    }

    //Prepassivate annotation is allow method to receive callback before bean is passivated
    //Predestroy annotation allow method to signal that instance is being removed by container
    @PreDestroy
    @PrePassivate
    public void close()
    {
        try
        {
            connection.close();
            System.out.println("close()");
        }
        catch (SQLException sqle)
        {
            System.out.println("close exception");
            System.out.println(sqle.getMessage());
        }
    }

    @Override
    public boolean customerLogin(String c_id, String password) {
        boolean operationResult=false; 
        int id=Integer.valueOf(c_id);
        try{
            CustomerDAO dao=new RDBCustomerDAO(connection);
            Collection<Customer> cl=dao.getAllCustomers();
            for(Customer c:cl){
                if(id==c.getC_ID()){
                    if(password.equals(c.getPassword())){
                       operationResult=true;
                       
                    }
                }
            }
        }catch(Exception ex){
            System.out.println("SERVER ERROR: Logining Customer <FN: "+c_id+" LN: "+password+" > fail.");
          
        }
        
        return operationResult;   
    
    }

    @Override
    public boolean customerLogout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addCustomer(String firstName, String lastName, Date dob, String address) {
        boolean operationResult=false; 
        try{
            CustomerDAO dao=new RDBCustomerDAO(connection);
            Customer customer=new Customer(firstName,lastName,dob,address);
            dao.createCustomer(customer);
            operationResult=true;
        }catch(Exception ex){
            System.out.println("SERVER ERROR: Adding Customer <FN: "+firstName+" LN: "+lastName+" > fail.");
            ex.printStackTrace();
            operationResult=false;
        }
        
        return operationResult;
    
    }

    @Override
    public boolean openSavingsAccount(int customerId) {
          boolean operationResult=false; 
          try{
                CustomerDAO dao=new RDBCustomerDAO(connection);
                if(dao.readCustomer(customerId)!=null){
                    SavingsDAO sdao=new RDBSavingsDAO(connection);
                    sdao.createSavingsAccount(new Savings(customerId));
                }
          }catch(Exception ex){
            System.out.println("SERVER ERROR: Open savings account on Customer<ID: "+customerId+" LN: "+" > fail.");
            operationResult=false;
          }
          return operationResult;
    }

    @Override
    public boolean makeDeposit(int accountNumber, double amount, String description) {
         boolean operationResult=false; 
          try{
              //make the deposit for the customer
            SavingsDAO dao=new RDBSavingsDAO(connection);
            Savings account=dao.readSavingsAccount(accountNumber);
            account.makeDeposit(amount);
            dao.updateSavingsAccount(account);
            //record the transaction for this operations
            operationResult=true;
            
          }catch(Exception ex){
            System.out.println("SERVER ERROR: Make deposit on Account <AN: "+accountNumber+" AMOUNT: "+amount+" > fail.");
        }finally{
              TransactionDAO tdao=new RDBTransactionDAO(connection);
              Transaction transaction=new Transaction(accountNumber,amount,description);
              tdao.createTransaction(transaction);
              
          }
        
        return operationResult;
    }

    @Override
    public boolean makeWithdrawal(int accountNumber, double amount, String description) {
         boolean operationResult=false; 
          try{
            CustomerDAO dao=new RDBCustomerDAO(connection);
          }catch(Exception ex){
            System.out.println("SERVER ERROR: Make withdraw on Account <AN: "+accountNumber+" AMOUNT: "+amount+" > fail.");
            operationResult=false;
        }
        
        return operationResult;
    }

    @Override
    public String[] viewBalance(int accountNumber) {
         CustomerDAO dao=new RDBCustomerDAO(connection);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Collection getCustomerList() {
        
        CustomerDAO dao=new RDBCustomerDAO(connection);
        Collection<String> custList=new HashSet<>();
        Collection<Customer> cl=dao.getAllCustomers();
        
        for(Customer c:cl){
            custList.add(c.toString()+"\n");
        }
        custList.add("Customer List\n");
        return custList;
    }

    @Override
    public Collection getSavingAccountList() {
        SavingsDAO dao=new RDBSavingsDAO(connection);
        Collection<String> savingsList=new HashSet<>();
        Collection<Savings> sl=dao.getAllSavingsAccount();
        
        for(Savings s:sl){
            savingsList.add(s.toString()+"\n");
        }
        savingsList.add("Savings Account List\n");
        return savingsList;
    }

    @Override
    public ArrayList getCustomer(String c_id) {
         CustomerDAO dao=new RDBCustomerDAO(connection);
         ArrayList<String> custList=new ArrayList<>();
         Collection<Customer> cl=dao.getAllCustomers();
         
         for(Customer c:cl){
             if(c.getC_ID()==Integer.valueOf(c_id)){
                 custList.add(String.valueOf(c.getC_ID()));
                 custList.add(c.getPassword());
                 custList.add(c.getAddress());
                 custList.add(c.getFirstName());
                 custList.add(c.getLastName());
                 custList.add(String.valueOf(c.getDateOfBirth()));
                 
             }
        }
        return custList;
    }
}
