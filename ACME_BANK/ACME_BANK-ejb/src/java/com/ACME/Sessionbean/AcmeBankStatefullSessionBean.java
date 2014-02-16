/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ACME.Sessionbean;

import com.ACME.data.Employee;
import com.ACME.dataDAO.EmployeeDAO;
import com.ACME.dataDAO.dataRDB.RDBEmployeeDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.sql.DataSource;

/**
 *
 * @author WEIQIANG LIANG
 */
@Stateful
//setting the stateful bean session time out in 60 seconds
@StatefulTimeout(unit = TimeUnit.SECONDS, value = 60)
public class AcmeBankStatefullSessionBean implements AcmeBankStatefullSessionBeanRemote {
    @Resource(lookup="jdbc/myACMEDatasource")
    private DataSource dataSource;
    private Connection connection;  
    
    //Stateful caculation for the session operation
    private int operationCount;
    private int sessionStatus;
    //Current Logined in Employee
    private Employee currentLoginedEmployee;
    
    
    //PostConstruct annotation allow method to perform initialization
    //PostActivate annotation allow method receive callback after session bean activated
    @PostConstruct
    @PostActivate
    public void initialize()
    {
        try
        {
            connection = dataSource.getConnection();
            //when the seesion first call, set the operation counter to 0
            operationCount=0;
            
            System.out.println("initialize()");
        }
        catch (SQLException sqle)
        {
            System.out.println("initialize exception");
            System.out.println(sqle.getMessage());
        }
    }

    //Prepassivate annotation is allow method to receive callback before bean is passivated
    //Predestroy annotation allow method to signal that instance is being removed by container
    @PreDestroy
    @PrePassivate
    public void close()
    {
        try
        {
            connection.close();
            System.out.println("close()");
        }
        catch (SQLException sqle)
        {
            System.out.println("close exception");
            System.out.println(sqle.getMessage());
        }
    }

    @Override
    public boolean employeeLogin(String emplId, String password) {
        boolean detailsCheck=false;
        EmployeeDAO dao=new RDBEmployeeDAO(connection);
        Collection<Employee> employeeCollection = dao.getAllEmployees();

            for (Employee e : employeeCollection)
            {
                if (!String.valueOf(e.E_ID).equals(emplId)|| !e.password.equals(password))
                {
                } else {
                    detailsCheck = true;
                    currentLoginedEmployee=e;//set the current save the current employee details in the session
                }
            }
       return detailsCheck;
    }

    @Override
    public void employeeLogout() {
         this.currentLoginedEmployee=null;
         this.operationCount=0;
    }

    @Override
    public int getOperationCount() {
        return this.operationCount;
    }

    @Override
    public int checkSessionStatus() {
        return this.sessionStatus;
        
    }

    @Override
    public void sessionExpired() {
        this.operationCount=0;
    }

    
    @Override
    public void operationCount() {
        this.operationCount+=1;
    }
   
    
    
}
