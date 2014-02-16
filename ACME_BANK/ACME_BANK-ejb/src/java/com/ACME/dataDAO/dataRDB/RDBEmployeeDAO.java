/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.dataDAO.dataRDB;

import com.ACME.data.Employee;
import com.ACME.dataDAO.EmployeeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author lucasang
 */
public class RDBEmployeeDAO implements EmployeeDAO
{
    // Instance Variables
    private Connection dbConnection = null;
    private HashSet employeeList = new HashSet();
    
    private final String SQL_CREATE_EMPLOYEE="INSERT INTO ACME_BANK.EMPLOYEE(FIRSTNAME, LASTNAME, E_ID)"+ " VALUES (?, ?, ?)";
    private final String SQL_READ_EMPLOYEE="SELECT * FROM ACME_BANK.EMPLOYEE WHERE E_ID = ?";
    private final String SQL_UPDATE_EMPLOYEE="UPDATE ACME_BANK.EMPLOYEE SET FIRSTNAME=?, LASTNAME=? WHERE E_ID=?";
    private final String SQL_DELETE_EMPLOYEE="DELTE FROM ACME_BANK.EMPLOYEE WHERE E_ID=?";

    // Constructor
    public RDBEmployeeDAO(Connection connection)
    {
        this.dbConnection = connection;
    }

    @Override
    public void createEmployee(Employee employee)
    {
        try
        {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "INSERT INTO EMPLOYEE(E_ID, FIRSTNAME, LASTNAME, PASSWORD)"
                    + "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            sqlStatement.setInt(1, employee.E_ID);
            sqlStatement.setString(2, employee.firstName);
            sqlStatement.setString(3, employee.lastName);
            sqlStatement.setString(4, employee.password);

            sqlStatement.executeUpdate();

            ResultSet result = sqlStatement.getGeneratedKeys();
            result.next();
            employee.E_ID = result.getInt(1);
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public Employee readEmployee(int employeeId)
    {
        try
        {
            String sqlScript = "SELECT * FROM EMPLOYEE WHERE E_ID=?";

            PreparedStatement sqlStatement = dbConnection.prepareStatement(sqlScript);
            sqlStatement.setInt(1, employeeId);

            ResultSet result = sqlStatement.executeQuery();

            result.next();

            Employee employee = new Employee(result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4));


            System.out.println("return employee");
            return employee;
        }
        catch (SQLException sqlException)
        {
            System.out.println("readEmployee exception");
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public void updateEmployee(Employee employee)
    {
        try
        {
            String sqlScript = "UPDATE EMPLOYEE SET FIRSTNAME = ?, "
                    + "LASTNAME = ?, PASSWORD = ? WHERE E_ID = ?";

            PreparedStatement sqlStatement = dbConnection.prepareStatement(sqlScript);
            sqlStatement.setString(1, employee.firstName);
            sqlStatement.setString(2, employee.lastName);
            sqlStatement.setString(3, employee.password);
            sqlStatement.setInt(4, employee.E_ID);

            sqlStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int employeeId)
    {
        try
        {
            String sqlScript = "DELETE FROM EMPLOYEE WHERE E_ID = ?";
            PreparedStatement sqlStatement = dbConnection.prepareStatement(sqlScript);
            sqlStatement.setInt(1, employeeId);

            sqlStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public Collection<Employee> getAllEmployees()
    {
        try
        {
            String sqlScript = "SELECT * FROM EMPLOYEE";

            PreparedStatement sqlStatement = dbConnection.prepareStatement(sqlScript);

            ResultSet result = sqlStatement.executeQuery();

            while (result.next())
            {
                Employee employee = new Employee(result.getInt(1),
                        result.getString(2), result.getString(3),
                        result.getString(4));

                employeeList.add(employee);
            }

        }
        catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }

        return employeeList;
    }
}
