/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.MessageDriven;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;

/**
 *
 * @author WEIQIANG LIANG
 */
@Stateless
public class SenderSessionBean implements SenderSessionBeanRemote {
    @Resource(mappedName = "jms/myACMEQueue")
    private Queue myACMEQueue;
    @Inject
    @JMSConnectionFactory("jms/myACMEQueueConnection")
    private JMSContext context;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")+

    private void sendJMSMessageToMyACMEQueue(String messageData) {
        context.createProducer().send(myACMEQueue, messageData);
    }
   @Override
    public void exchangeUserInfo(Collection o){
        try {
                if(o instanceof ArrayList){
                    ArrayList<String> ol=(ArrayList<String>) o;
                    System.out.println(ol);
                    MapMessage map=context.createMapMessage();
                    map.setString("ACME_BANK_SYSTEM_ACK", "USER LOGIN");
                    map.setString("C_ID", ol.get(0));
                    map.setString("PASSWORD", ol.get(1));
                    map.setString("ADDRESS", ol.get(2));
                    map.setString("FIRSTNAME", ol.get(3));
                    map.setString("LASTNAME", ol.get(4));
                    map.setString("DOB", ol.get(5));
                    map.setString("ACCOUNT_ONE", ol.get(6));
                    map.setString("ACCOUNT_TWO", ol.get(7));
                    
                    
                    context.createProducer().send(myACMEQueue, map);
                }else{
                    System.out.println("NOT WORKING");
                }
        } catch (JMSException ex) {
            System.out.println("ERROR: ERROR CORRUTED ON USER LOGIN ");
        }    
    }

    @Override
    public void userLoginValudation(String c_id, String c_password) {
        try{
            
             MapMessage map = context.createMapMessage();
             
             map.setString("C_ID", c_id);
                    map.setString("PASSWORD", c_password);
            context.createProducer().send(myACMEQueue, map);
        }catch (JMSException ex) {
            System.out.println("ERROR: ERROR CORRUTED ON USER LOGIN ");
        } 
    }
    
}
