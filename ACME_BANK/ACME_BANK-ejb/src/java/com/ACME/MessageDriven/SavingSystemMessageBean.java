/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.MessageDriven;

import com.ACME.Sessionbean.AcmeBankStatlessSessionBeanRemote;
import java.util.ArrayList;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author WEIQIANG LIANG
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myACMEQueue")
})
public class SavingSystemMessageBean implements MessageListener {
    @EJB
    private SenderSessionBeanRemote senderSessionBean;
    @EJB
    private AcmeBankStatlessSessionBeanRemote customerSessionBean;
    
    public SavingSystemMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try{
            
            //login information
              if(message instanceof TextMessage){
                  TextMessage tm=(TextMessage) message;
                  System.out.println("Server Received: "+tm.getText());
              }
              //login information
              if(message instanceof MapMessage){
                  MapMessage map=(MapMessage) message;
                  //determind what this message is using for(i.e. the purpose of the message)
                  if(map.getString("ACME_BANK_SYSTEM_QUERY").equalsIgnoreCase("LOGIN")){
                      String c_id = map.getString("C_ID");
                      String password=map.getString("PASSWORD");
                        if(customerSessionBean.customerLogin(c_id, password)){
                            ArrayList<String> ts=(ArrayList<String>) customerSessionBean.getCustomer(c_id); 
                            senderSessionBean.exchangeUserInfo(ts);
                        }
                  }
                  
                  
                 
              }
               
         }catch (JMSException ex){
            System.out.println("Error");
        }
    }
    
    /*
    public boolean validateLogin(Message message){
         boolean operationResult=false; 
             try {
                 if(message instanceof MapMessage){
                 MapMessage map=(MapMessage) message;
                 String c_id = map.getString("C_ID");
                 String password=map.getString("PASSWORD");
                    if(customerSessionBean.customerLogin(c_id, password)){
                            
                    }
                 }
             } catch (JMSException ex) {
                 operationResult=false;
             }
        return operationResult;
    }*/
    
}
