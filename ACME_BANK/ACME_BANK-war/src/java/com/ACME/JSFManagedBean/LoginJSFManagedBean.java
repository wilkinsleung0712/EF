/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.JSFManagedBean;

import com.ACME.MessageDriven.SenderSessionBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author WEIQIANG LIANG
 */
@ManagedBean
@SessionScoped
public class LoginJSFManagedBean implements Serializable{
    @EJB
    private SenderSessionBean senderSessionBean;

    /**
     * Creates a new instance of LoginJSFManagedBean
     */
    public LoginJSFManagedBean() {
    }
    
    private String c_id;
    private String password;

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void customerLogin(){
        senderSessionBean.sendQueryToSavingSystemForUserLogin(c_id, password);
    }
    
}
