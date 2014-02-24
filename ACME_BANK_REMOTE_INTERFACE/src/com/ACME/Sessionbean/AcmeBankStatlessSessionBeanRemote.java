/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.Sessionbean;

import java.sql.Date;
import java.util.Collection;
import javax.ejb.Remote;

/**
 * base on customer activities
 * @author WEIQIANG LIANG
 */
@Remote
public interface AcmeBankStatlessSessionBeanRemote {
    public boolean customerLogin(String c_id, String password);
    public boolean customerLogout();
    
    public boolean addCustomer(String firstName, String lastName, Date dob, String address);
    public boolean openSavingsAccount(int customerId);
    public boolean makeDeposit(int accountNumber, double amount, String description);
    public boolean makeWithdrawal(int accountNumber, double amount, String description);
    
    public Collection getCustomer(String c_id);
    
    public String[] viewBalance(int accountNumber);
    public Collection getCustomerList();
    public Collection getSavingAccountList();
}
