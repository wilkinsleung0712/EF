/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.JSFManagedBean;

import com.ACME.MessageDriven.SenderSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIPanel;

/**
 *
 * @author WEIQIANG LIANG
 */
@ManagedBean
@SessionScoped
public class HomeLoanJSFManagedBean implements Serializable{
    @EJB
    private SenderSessionBean senderSessionBean1;
  
    @EJB
    private SenderSessionBean senderSessionBean;
  
   

//variable for the logged in user information
    private int id;

    //variable to store value entered during homeloan creation
    private String hname;
    private String haddress;
    private String hdob;
    private String hcurrentjob;
    private double hsalaryperyear;
    private String hcontactmethod;
    private double hamountborrowed;
    //variable for getting saving account information
    private String sacclist;
    
    
    //variable to return a string list of homeloan accounts and saving accounts
    private ArrayList<String> homeloanstring;
    private ArrayList<String> savingaccountstring =  new ArrayList<String>();

    private boolean checkbox;
    
    private UIPanel resultPanel;

    
  
    
    /**
     * Creates a new instance of HomeLoanJSFManagedBean
     */
    public HomeLoanJSFManagedBean() {
    }
    
  
   
    public void validateLogin(){
   
        //FacesContext context = FacesContext.getCurrentInstance();
        // resultPanel.setRendered(true);
        
        // if (checkOperation()) {
        //  context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
        //} //else {
        //  context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
        // }
        
        //senderSessionBean.sendQueryToSavingSystemForUserLogin(hname, );
     
     }
    
    public String checkResult(){
        return "Name: "+this.hname;
    }
    
      //beginning of get and set methods 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHaddress() {
        return haddress;
    }

    public void setHaddress(String haddress) {
        this.haddress = haddress;
    }

    public String getHdob() {
        return hdob;
    }

    public void setHdob(String hdob) {
        this.hdob = hdob;
    }

    public String getHcurrentjob() {
        return hcurrentjob;
    }

    public void setHcurrentjob(String hcurrentjob) {
        this.hcurrentjob = hcurrentjob;
    }

    public double getHsalaryperyear() {
        return hsalaryperyear;
    }

    public void setHsalaryperyear(double hsalaryperyear) {
        this.hsalaryperyear = hsalaryperyear;
    }

    public String getHcontactmethod() {
        return hcontactmethod;
    }

    public void setHcontactmethod(String hcontactmethod) {
        this.hcontactmethod = hcontactmethod;
    }

    public double getHamountborrowed() {
        return hamountborrowed;
    }

    public void setHamountborrowed(double hamountborrowed) {
        this.hamountborrowed = hamountborrowed;
    }

    public String getSacclist() {
        return sacclist;
    }

    public void setSacclist(String sacclist) {
        this.sacclist = sacclist;
    }

    public ArrayList<String> getHomeloanstring() {
        return homeloanstring;
    }

    public void setHomeloanstring(ArrayList<String> homeloanstring) {
        this.homeloanstring = homeloanstring;
    }

    public ArrayList<String> getSavingaccountstring() {
        return savingaccountstring;
    }

    public void setSavingaccountstring(ArrayList<String> savingaccountstring) {
        this.savingaccountstring = savingaccountstring;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
    
    
    //end of auto generate getter and setter

    }
  

