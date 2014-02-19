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
    private int E_ID;
    private String firstName;
    private String lastName;
    private String password;
    private Random rand = new Random();
    private int max = 99999999;
    private int min = 1;

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
    
 //auto generate getter and setter
    public int getE_ID() {
        return E_ID;
    }

    private void setE_ID(int E_ID) {
        this.E_ID = E_ID;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public Random getRand() {
        return rand;
    }

    private void setRand(Random rand) {
        this.rand = rand;
    }

    public int getMax() {
        return max;
    }

    private void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    private void setMin(int min) {
        this.min = min;
    }
    
    
}
