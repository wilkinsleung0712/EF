/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.MessageDriven;

import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author WEIQIANG LIANG
 */
@Remote
public interface SenderSessionBeanRemote {
     public void exchangeUserInfo(Collection o);
}
