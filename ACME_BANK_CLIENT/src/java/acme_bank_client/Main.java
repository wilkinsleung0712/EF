/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acme_bank_client;

import com.ACME.Sessionbean.AcmeBankStatefullSessionBeanRemote;
import com.ACME.Sessionbean.AcmeBankStatlessSessionBeanRemote;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.ejb.EJB;

/**
 *
 * @author WEIQIANG LIANG
 */
public class Main {
    @EJB
    private static AcmeBankStatlessSessionBeanRemote customerBean;
    @EJB
    private static AcmeBankStatefullSessionBeanRemote employeeBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Declare a bufferreader to read the input by user
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        //Instance Variable
        boolean showSystemLogin = true;
        boolean employeeLoginCheck = false;

        //Employee Login 
        while (showSystemLogin)
        {
            String empId ="";
            String empPassword = "";

            //Employee Log in
            System.out.println("---------------");
            System.out.println("System Login");
            System.out.println("---------------");

            System.out.print("Enter your Employee ID: ");
            try
            {
                empId = bufferReader.readLine();
            }
            catch (Exception e)
            {
                System.out.println("Invalid Input.");
            }

            System.out.print("Enter your password: ");
            try
            {
                empPassword = bufferReader.readLine();
            }
            catch (Exception e)
            {
                System.out.println("Invalid Input.");
            }

            try
            {
                //Call the method employeeLogin and pass the inputs enter by users
                //EmployeeLoginCheck will become true if id and passward are correct
                //Showsystemlogin will become false to prohibit the login page appear
                //If the ID and Passward wasn`t correct, user need to re-enter
                if (employeeBean.employeeLogin(empId, empPassword))
                {
                    employeeLoginCheck = true;
                    showSystemLogin = false;
                }
                else
                {
                    System.out.println("");
                    System.out.println("You could not be logged on to the system.");
                }
            }
            catch (Exception e)
            {
                //SHOW restart client
                System.out.println();
                System.out.println("The session has expired, "
                        + "please restart the client");
                break;
            }

            System.out.println("");

            while (employeeLoginCheck)
            {
                //Welcome screen after login
                System.out.println("---------------------------------");
                System.out.println("Welcome to ACME Banking System");
                System.out.println("---------------------------------");

                int option = 0;
                while (option != 6)
                {
                    System.out.println("");
                    System.out.println("-------------------------");
                    System.out.println("Main Menu:");
                    System.out.println("1. Create a Customer");
                    System.out.println("2. Open a Savings Account");
                    System.out.println("3. Make Deposit into Savings Account");
                    System.out.println("4. Make Withdrawl into Savings Account");
                    System.out.println("5. View Balance of Savings Account");
                    System.out.println("6. Logout");

                    System.out.println();

                    System.out.print("Enter a option: ");

                    try
                    {
                        option = Integer.parseInt(bufferReader.readLine());
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid Input.");
                    }

                    System.out.println();

                    //Create Customer
                    if (option == 1)
                    {
                        String first = "";
                        String last = "";
                        String dob = "";
                        java.sql.Date javaSqlDate = null;
                        String address = "";
                        boolean invalidIputCheck = false;


                        System.out.println("Create a Customer");
                        System.out.println("--------------------");

                        System.out.print("Enter Customers FIRST Name: ");

                        try
                        {
                            first = bufferReader.readLine();
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }

                        System.out.print("Enter Customers LAST Name: ");

                        try
                        {
                            last = bufferReader.readLine();
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }

                        System.out.print("Enter Customers Date of Birth (YYYY-MM-dd): ");

                        try
                        {
                            dob = bufferReader.readLine();

                            try
                            {
                                javaSqlDate = java.sql.Date.valueOf(dob);
                            }
                            catch (Exception e)
                            {
                                System.out.println("Invalid date format.");
                                invalidIputCheck = true;
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }

                        System.out.print("Enter Customers ADDRESS: ");

                        try
                        {
                            address = bufferReader.readLine();
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }

                        System.out.println();

                        if (invalidIputCheck == true)
                        {
                            System.out.println("Customer could not be added due "
                                    + "to invalid inputs.");
                        }
                        else
                        {
                            //Check session status
                            //If idle over 1 minute, need to restart client
                            try
                            {
                                employeeBean.getOperationCount();
                            }
                            catch (Exception e)
                            {
                                System.out.println("The session has timed out, "
                                        + "please restart the client.");

                                employeeLoginCheck = false;
                                showSystemLogin = false;
                                break;
                            }

                            if (customerBean.addCustomer(first, last, javaSqlDate, address))
                            {
                                System.out.println("Customer has been Added.");
                            }
                            else
                            {
                                System.out.println("An error has been occured "
                                        + "and the customer could not be added.");
                            }

                            System.out.println();

                            try
                            {
                                //Increment operation count
                                employeeBean.operationCount();
                                //Get the current operation count
                                int count = employeeBean.getOperationCount();
                                //Need to restart client if employee has done 
                                //more than 10 operations
                                if (count >= 10)
                                {
                                    //end the session bean
                                    System.out.println("Session has expired due to "
                                            + "a maximum number of operations.\n\n");

                                    employeeLoginCheck = false;
                                    showSystemLogin = true;
                                    break;
                                }
                                else
                                {
                                    System.out.println("You have perform a"
                                            + " number of " + count + " "
                                            + "operations since login.");
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("An Error has occurred.");
                            }
                        }
                    }
                    //Open Savings Account
                    else if (option == 2)
                    {
                        int customerId = 0;
                        double balance = 0;
                        boolean invalidIputCheck = false;

                        System.out.println("Open a Savings Account");
                        System.out.println("-------------------------");
                        System.out.println(customerBean.getCustomerList());
                        System.out.print("Enter Customers Identification Number: ");

                        try
                        {
                            customerId = Integer.parseInt(bufferReader.readLine());
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }
                        System.out.println();

                        if (invalidIputCheck == true)
                        {
                            System.out.println("Customer could not be added due "
                                    + "to invalid inputs.");
                        }
                        else
                        {
                            //Check session status
                            //If idle over 1 minute, need to restart client
                            try
                            {
                                employeeBean.getOperationCount();
                            }
                            catch (Exception e)
                            {
                                System.out.println("The session has timed out, "
                                        + "please restart the client.");

                                employeeLoginCheck = false;
                                showSystemLogin = false;
                                break;
                            }
                            //Call the openSavingAccount Method and pass the 
                            //user input
                            if (customerBean.openSavingsAccount(customerId))
                            {
                                System.out.println("Savings Account Opened.");
                            }
                            else
                            {
                                System.out.println("An error has been occured "
                                        + "and the account could not be opened.");
                            }

                            System.out.println();

                            try
                            {
                                //Increment operation 
                                employeeBean.operationCount();
                                //Get the total operation
                                int count = employeeBean.getOperationCount();
                                //Over 10 operation will be forced to restart client
                                if (count >= 10)
                                {
                                    //End the session bean
                                    System.out.println("Session has expired due to "
                                            + "a maximum number of operations.\n\n");

                                    employeeLoginCheck = false;
                                    showSystemLogin = true;
                                    break;
                                }
                                else
                                {
                                    System.out.println("You have perform a"
                                            + " number of " + count + " "
                                            + "operations since login.");
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("An Error has occurred.");
//                                employeeLoginCheck = false;
//                                showSystemLogin = false;
//                                break;
                            }
                        }
                    }
                    //Make deposit into Savings account
                    else if (option == 3)
                    {
                        int customerId = 0;
                        int accountNumber = 0;
                        double amount = 0;
                        String description = "Deposit";
                        boolean invalidIputCheck = false;

                        System.out.println("Make a Deposit into Savings Account");
                        System.out.println("--------------------------------------");
                        //System.out.println(customerBean.getSavingAccountList());

                        System.out.print("Enter Customers Account Number: ");

                        try
                        {
                            accountNumber = Integer.parseInt(bufferReader.readLine());
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }

                        System.out.print("Enter amount: ");

                        try
                        {
                            amount = Double.parseDouble(bufferReader.readLine());
                            
                            if(amount < 0)
                            {
                                invalidIputCheck = true;
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }

                        System.out.println();

                        if (invalidIputCheck == true)
                        {
                            System.out.println("Deposit failed due "
                                    + "to invalid inputs.");
                        }
                        else
                        {
                            //Check session status
                            //If idle over 1 minute, need to restart client
                            try
                            {
                                employeeBean.getOperationCount();
                            }
                            catch (Exception e)
                            {
                                System.out.println("The session has timed out, "
                                        + "please restart the client.");

                                employeeLoginCheck = false;
                                showSystemLogin = false;
                                break;
                            }
                            //Call the makeDeposit method with user input
                            if (customerBean.makeDeposit(accountNumber,
                                    amount, description))
                            {
                                System.out.println("Deposit Successful.");
                            }
                            else
                            {
                                System.out.println("An error has been occured "
                                        + "and the deposit is unsuccessful.");
                            }

                            System.out.println();

                            try
                            {
                                //Increment operation 
                                employeeBean.operationCount();
                                //Get the total operation
                                int count = employeeBean.getOperationCount();
                                //User will be forced to restart if operation over 10
                                if (count >= 10)
                                {
                                    //end the session bean
                                    System.out.println("Session has expired due to "
                                            + "a maximum number of operations.\n\n");

                                    employeeLoginCheck = false;
                                    showSystemLogin = true;
                                    break;
                                }
                                else
                                {
                                    System.out.println("You have perform a"
                                            + " number of " + count + " "
                                            + "operations since login.");
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("An Error has occurred.");
                            }
                        }
                    }
                    //Make withdrawal from Savings account
                    else if (option == 4)
                    {
                        int customerId = 0;
                        int accountNumber = 0;
                        double amount = 0;
                        String description = "Withdrawal";
                        boolean invalidIputCheck = false;

                        System.out.println("Make a Withdrawal from Savings Account");
                        System.out.println("-----------------------------------------");

                        System.out.print("Enter Customers Account Number: ");

                        try
                        {
                            accountNumber = Integer.parseInt(bufferReader.readLine());
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }

                        System.out.print("Enter amount: ");

                        try
                        {
                            amount = Double.parseDouble(bufferReader.readLine());
                            
                            if(amount < 0)
                            {
                                invalidIputCheck = true;
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }

                        System.out.println();

                        if (invalidIputCheck == true)
                        {
                            System.out.println("Withdrawal failed due "
                                    + "to invalid inputs.");
                        }
                        else
                        {
                            //Check session status
                            //If idle over 1 minute, need to restart client
                            try
                            {
                                employeeBean.getOperationCount();
                            }
                            catch (Exception e)
                            {
                                System.out.println("The session has timed out, "
                                        + "please restart the client.");

                                employeeLoginCheck = false;
                                showSystemLogin = false;
                                break;
                            }

                            //Call the makeWithdrawal method
                            //Return true means the transaction success
                            if (customerBean.makeWithdrawal(accountNumber,
                                    amount, description))
                            {
                                System.out.println("Withdrawal successful.");
                            }
                            else
                            {
                                System.out.println("An error has been occured "
                                        + "and the withdrawal is unsuccessful.");
                            }

                            System.out.println();

                            try
                            {
                                //Increment operation count
                                employeeBean.operationCount();
                                //Get the total number of operation
                                int count = employeeBean.getOperationCount();
                                //User will be forced to restart client if operation over 10
                                if (count >= 10)
                                {
                                    //end the session bean
                                    System.out.println("Session has expired due to "
                                            + "a maximum number of operations.\n\n");

                                    employeeLoginCheck = false;
                                    showSystemLogin = true;
                                    break;
                                }
                                else
                                {
                                    System.out.println("You have perform a"
                                            + " number of " + count + " "
                                            + "operations since login.");
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("An Error has occurred.");
//
//                                employeeLoginCheck = false;
//                                showSystemLogin = false;
//                                break;
                            }
                        }
                    }
                    //View balance of Savings account
                    else if (option == 5)
                    {
                        int customerId = 0;
                        int accountNumber = 0;
                        boolean invalidIputCheck = false;

                        System.out.println("View Savings Account");
                        System.out.println("-----------------------------------------");

                        System.out.print("Enter Customers Account Number: ");

                        try
                        {
                            accountNumber = Integer.parseInt(bufferReader.readLine());
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid Input.");
                            invalidIputCheck = true;
                        }

                        System.out.println();

                        if (invalidIputCheck == true)
                        {
                            System.out.println("Viewing account balance failed due "
                                    + "to invalid inputs.");
                        }
                        else
                        {
                            //Check session status
                            //If idle over 1 minute, need to restart client
                            try
                            {
                                employeeBean.checkSessionStatus();
                            }
                            catch (Exception e)
                            {
                                System.out.println("The session has timed out, "
                                        + "please restart the client.");

                                employeeLoginCheck = false;
                                showSystemLogin = false;
                                break;
                            }

                            //Execute view customer balance
                            String[] string = customerBean.viewBalance(accountNumber);

                            if (string.length > 1)
                            {
                                System.out.println("Customer Id: " + string[0]);
                                System.out.println("Account Number: " + string[1]);
                                System.out.println("Account Balance: $" + string[2]);
                            }
                            else
                            {
                                System.out.println(string[0]);
                            }

                            System.out.println();

                            try
                            {
                                //Increment operation count
                                employeeBean.operationCount();
                                //Get the total number of operation
                                int count = employeeBean.getOperationCount();
                                //User will be forced to restart client if operation over 10
                                if (count >= 10)
                                {
                                    //end the session bean
                                    System.out.println("Session has expired due to "
                                            + "a maximum number of operations.\n\n");

                                    employeeBean.sessionExpired();
                                    employeeLoginCheck = false;
                                    showSystemLogin = true;
                                    break;
                                }
                                else
                                {
                                    System.out.println("You have perform a"
                                            + " number of " + count + " "
                                            + "operations since login.");
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("An Error has occurred.");
//                                System.out.println("The session has timed out, "
//                                        + "please restart the client.");
//
//                                employeeLoginCheck = false;
//                                showSystemLogin = false;
//                                break;
                            }
                        }
                    }
                    //Logout
                    else if (option == 6)
                    {
                        //Call the checksessionstatus to check stateful session
                        //bean is active
                        try
                        {
                            employeeBean.checkSessionStatus();
                        }
                        catch (Exception e)
                        {
                            System.out.println("The session has timed out, "
                                    + "please restart the client.");

                            employeeLoginCheck = false;
                            showSystemLogin = false;
                            break;
                        }

                        //end the session bean
                        System.out.println("Logout Successful\n\n");
                        employeeBean.employeeLogout();
                        employeeLoginCheck = false;
                        showSystemLogin = true;
                    }
                    else
                    {
                        System.out.println("Invalid Input.");
                    }
                }
            }
        }
    }
    
}
