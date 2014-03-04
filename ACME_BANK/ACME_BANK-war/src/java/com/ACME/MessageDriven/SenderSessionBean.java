/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.MessageDriven;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;

/**
 *
 * @author WEIQIANG LIANG
 */
@Stateless
@LocalBean
public class SenderSessionBean {
    @Resource(mappedName = "jms/myACMEQueue")
    private Queue myACMEQueue;
    
    @Inject
    @JMSConnectionFactory("jms/myACMEQueueConnection")
    private JMSContext context;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void sendJMSMessageToMyACMEQueue(String messageData) {
        context.createProducer().send(myACMEQueue, messageData);
    }
    
    public String sendQueryToSavingSystemForUserLogin(String c_id, String  password){
        try {
            MapMessage map = context.createMapMessage();
            map.setString("ACME_BANK_SYSTEM_QUERY", "LOGIN");
            map.setString("C_ID", c_id);
            map.setString("PASSWORD", password);
             context.createProducer().send(myACMEQueue, map);
             return "SUCCESS";
        } catch (JMSException ex) {
            System.out.println("ERROR: ERROR CORRUTED ON USER LOGIN ");
            return "FAIL";
        }    
    }
    
}
