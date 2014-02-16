/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.data;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author lucasang
 */
public class Employee
{
    //Instance Variables
    public int E_ID;
    public String firstName;
    public String lastName;
    public String password;
    Random rand = new Random();
    int max = 99999999;
    int min = 1;

    //Constructor
    public Employee(String firstName, String lastName, String password)
    {
        this.E_ID = rand.nextInt(max - min + 1) + min;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public Employee(int E_ID, String firstName, String lastName, String password)
    {
        this.E_ID = E_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
