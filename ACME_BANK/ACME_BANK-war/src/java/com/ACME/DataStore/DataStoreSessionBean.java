/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.DataStore;

import com.ACME.JSFManagedBean.HomeLoanJSFManagedBean;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author WEIQIANG LIANG
 */
@Singleton
@LocalBean
public class DataStoreSessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    //variable holding the customer password from database
    private String DBcustomerpass;

    //variable to hold the number of deposits made by the customer
    private int DBnumofdeposits;
    
    //variable to check to see if the money from saving account withdrawed or not
    private String DBmakepayvadilaty;
    
    //variable to hold the saving accounts
    private String DBSavingAccounts;
    
    //variable to hold the total amount money from all saving accounts
    private double DBTotalAmountMoney;

    private HomeLoanJSFManagedBean h;
    
     private String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
      h.setHname(firstname);
    }
     
     
     
     
     
    public double getDBTotalAmountMoney() {
        return DBTotalAmountMoney;
    }

    public void setDBTotalAmountMoney(double DBTotalAmountMoney) {
        this.DBTotalAmountMoney = DBTotalAmountMoney;
    }

    public String getDBSavingAccounts() {
        return DBSavingAccounts;
    }

    public void setDBSavingAccounts(String DBSavingAccounts) {
        this.DBSavingAccounts = DBSavingAccounts;
    }

    public String getDBmakepayvadilaty() {
        return DBmakepayvadilaty;
    }

    public void setDBmakepayvadilaty(String DBmakepayvadilaty) {
        this.DBmakepayvadilaty = DBmakepayvadilaty;
    }

    public int getDBnumofdeposits() {
        return DBnumofdeposits;
    }

    public void setDBnumofdeposits(int DBnumofdeposits) {
        this.DBnumofdeposits = DBnumofdeposits;
    }

    public String getDBcustomerpass() {
        return DBcustomerpass;
    }

    public void setDBcustomerpass(String DBcustomerpass) {
        this.DBcustomerpass = DBcustomerpass;
    }
}
