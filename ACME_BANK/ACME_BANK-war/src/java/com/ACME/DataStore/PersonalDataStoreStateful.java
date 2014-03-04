/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.DataStore;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

/**
 *
 * @author WEIQIANG LIANG
 */
@Stateful
@LocalBean
public class PersonalDataStoreStateful {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //variable to hold the saving accounts
    private String DBSavingAccounts;
    private String firstname;
    private String lastname;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    private String password;
    private String address;
    private String saving_one;
    private String saving_two;

    public String getDBSavingAccounts() {
        return DBSavingAccounts;
    }

    public void setDBSavingAccounts(String DBSavingAccounts) {
        this.DBSavingAccounts = DBSavingAccounts;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSaving_one() {
        return saving_one;
    }

    public void setSaving_one(String saving_one) {
        this.saving_one = saving_one;
    }

    public String getSaving_two() {
        return saving_two;
    }

    public void setSaving_two(String saving_two) {
        this.saving_two = saving_two;
    }
    
     @PostConstruct
    @PostActivate
    public void initialize()
    {
       System.out.println("PersonalDataStoreStateful Bean Starting");
    }

    //Prepassivate annotation is allow method to receive callback before bean is passivated
    //Predestroy annotation allow method to signal that instance is being removed by container
    @PreDestroy
    @PrePassivate
    public void close()
    {
        System.out.println("PersonalDataStoreStateful Bean Closing");
    }
}
