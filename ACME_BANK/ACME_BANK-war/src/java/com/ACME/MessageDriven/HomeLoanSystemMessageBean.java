/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.MessageDriven;

import com.ACME.JSFManagedBean.HomeLoanJSFManagedBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author WEIQIANG LIANG
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myACMEQueue")
})
public class HomeLoanSystemMessageBean implements MessageListener {
    
    public HomeLoanSystemMessageBean() {
    }
    //call the jsf managed bean to populate the information of customer accross the web site after validated the user info.
     
    @Override
    public void onMessage(Message message) {
        try {
            if(message instanceof MapMessage){
                MapMessage map=(MapMessage) message;
                System.out.println("message demap from homeloan bean"+map.getString("C_ID"));
                System.out.println("message demap from homeloan bean"+map.getString("PASSWORD"));
                System.out.println("message demap from homeloan bean"+map.getString("ADDRESS"));
                System.out.println("message demap from homeloan bean"+map.getString("FIRSTNAME"));
                System.out.println("message demap from homeloan bean"+map.getString("LASTNAME")); 
                //homeJSFPage.setHaddress(map.getString("ADDRESS"));
            } 
        } catch (JMSException ex) {
               System.out.println("Message is not an instance of MapMessage.");
            }
       
        
    }
    
}
