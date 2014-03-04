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
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.jms.MapMessage;

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
    
    public String customerLogin(){
        String result=senderSessionBean.sendQueryToSavingSystemForUserLogin(c_id, password);
        return result;
    }
    //end of auto generated setter and getter
    
    
    public void sendJMSMessageToQueue(ComponentSystemEvent event){
        //first is to the get the values from the browser (start)
        UIComponent uic = event.getComponent();
        FacesContext fc = FacesContext.getCurrentInstance();
        
        UIInput browserID = (UIInput)uic.findComponent("idinput");
        UIInput browserPW = (UIInput)uic.findComponent("passwordinput");
        
        String browserIDValue = browserID.getLocalValue().toString();

        String browserPWValue = browserPW.getLocalValue().toString();
        String browserPWid = browserPW.getClientId().toString();
        
        try{
          senderSessionBean.sendQueryToSavingSystemForUserLogin(c_id, password);
        }

        catch(Exception e){
            
        }
    }
}
