/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACME.data;

import java.io.Serializable;
import java.sql.Date;
import java.util.Random;

/**
 *
 * @author lucasang
 */
public class Customer implements Serializable
{

    //Instance Variables
    private int C_ID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String password;
    private String contact;
    private String employer;
    private String currentJob;
    private double salaryPerYear;
    private Random rand = new Random();
    private int max = 99999999;
    private int min = 1;

    //Constructor
    public Customer(String firstName, String lastName, Date dateOfBirth, String address)
    {
        this.C_ID = rand.nextInt(max - min + 1) + min;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Customer(int C_ID, String firstName, String lastName, Date dateOfBirth, String address)
    {
        this.C_ID = C_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Customer(int C_ID, String firstName, String lastName, Date dateOfBirth, String address,
            String password, String employer, String currentJob, double salaryPerYear, String contact)
    {
        this.C_ID = C_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.password = password;
        this.employer = employer;
        this.currentJob = currentJob;
        this.salaryPerYear = salaryPerYear;
        this.contact = contact;
    }
    
    public Customer(int C_ID, String firstName, String lastName, Date dateOfBirth, String address,
           String employer, String currentJob, double salaryPerYear, String contact)
    {
        this.C_ID = C_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.employer = employer;
        this.currentJob = currentJob;
        this.salaryPerYear = salaryPerYear;
        this.contact = contact;
    }
//geter and setter
    public int getC_ID() {
        return C_ID;
    }

    private void setC_ID(int C_ID) {
        this.C_ID = C_ID;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    private void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    private void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmployer() {
        return employer;
    }

    private void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    private void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

    public double getSalaryPerYear() {
        return salaryPerYear;
    }

    private void setSalaryPerYear(double salaryPerYear) {
        this.salaryPerYear = salaryPerYear;
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


    @Override
    public String toString(){
        return "[C_ID: "+this.C_ID+"]"+ "[FIRSTNAME: "+this.firstName+"]"+ "[LASTNAME: "+this.lastName+"]"+ "[DOB: "+this.dateOfBirth+"]";
        
    }
    
    
  
}
