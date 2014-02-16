/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.dataDAO;

import com.ACME.data.Employee;
import java.util.Collection;

/**
 *
 * @author lucasang
 */
//Interface for RDBEmployeeDAO
public interface EmployeeDAO
{
    public void createEmployee(Employee employee);
    public Employee readEmployee(int employeeId);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(int employeeId);
    public Collection<Employee> getAllEmployees();
}
