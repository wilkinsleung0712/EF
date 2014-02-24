/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.dataDAO;

import com.ACME.data.Customer;
import java.util.Collection;

/**
 *
 * @author lucasang
 */
//Interface for RDBCustomerDAO
public interface CustomerDAO
{
    public void createCustomer(Customer customer);
    public Customer readCustomer(int customerId);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(int customerId);
    public Collection getAllCustomers();
    public boolean checkCustomerExisted(Customer customer);
    
  
}
