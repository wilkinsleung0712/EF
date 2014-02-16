/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.Sessionbean;

import javax.ejb.Remote;

/**
 * base on employee activities
 * @author WEIQIANG LIANG
 */
@Remote
public interface AcmeBankStatefullSessionBeanRemote {
    public boolean employeeLogin(String emplId, String password);
    public void employeeLogout();
    public int getOperationCount();
    public int checkSessionStatus();
    public void sessionExpired();
    public void operationCount();
}
