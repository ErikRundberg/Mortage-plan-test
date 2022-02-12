/**
 * Customer class
 * 
 * The Customer class stores information about the customer and
 * has two methods, printCustomerInfo and calculateMonthlyMortgage.
 * 
 * printCustomerInfo prints all the information about the customer.
 * 
 * calculateMonthlyMortgage calculates the customers monthly mortgage
 * and stores it in the customer variable customerMonthlyMortgage.
 * 
 * @author Erik Rundberg
 * @version 1.0
 * @since 2022-02-08
 */

package com.bank;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Customer {
    private int prospectNumber; // Customer's prospect number
    private final String customerName; // Customer's name
    private final double customerLoan; // Customer's total loan amount
    private final double customerInterest; // Customer's interest rate
    private final int customerYears; // Customer's loan period in years
    private double customerMonthlyMortgage; // Customer's monthly mortgage cost (Calculated through the method
                                            // Customer.calculateMonthlyMortgage)

    private static int totalProspects = 1; // Static number that increments by 1 each time a Customer is created

    /**
     * This constructor creates an instance of the Customer class
     * recieved by CustomerFromFile.getCustomersList().
     * The Customer.totalProspects gets incremented by each instance.
     * 
     * @param customerInformation [Customer,Total loan,Interest,Years]
     */
    Customer(ArrayList<String> customerInformation) {
        this.customerName = customerInformation.get(0);
        this.customerLoan = Double.parseDouble(customerInformation.get(1));
        this.customerInterest = Double.parseDouble(customerInformation.get(2));
        this.customerYears = Integer.parseInt(customerInformation.get(3));

        // Customer gets given the latest number and then increments by 1
        this.prospectNumber = Customer.totalProspects;
        Customer.totalProspects++;
    }

    /**
     * This method prints out all of the information
     * stored in the Customer instance.
     */
    public void printCustomerInfo() {
        // Calculates monthly mortgage if it hasn't been done before
        if (this.customerMonthlyMortgage == 0) {
            calculateMonthlyMortgage();
        }
        System.out.println("Prospect #" + this.prospectNumber);
        System.out.println("Name: " + this.customerName);
        System.out.println("Total loan (\u20AC): " + this.customerLoan);
        System.out.println("Interest rate (%): " + this.customerInterest);
        System.out.println("Total loan period (years): " + this.customerYears);
        System.out.println("Current monthly mortgage: " + this.customerMonthlyMortgage);
    }

    /**
     * This method uses the variables
     * customerLoan
     * customerInterest
     * customerYears
     * and calculates the monthly mortgage which
     * gets stored in customerMonthlyMortgage
     */
    public void calculateMonthlyMortgage() {
        /*
         * Formula for fixed monthly payment
         * E = Fixed monthly payment
         * b = Interest on a monthly basis
         * U = Total loan
         * p = Number of payments
         * E = U[b(1 + b)^p]/[(1 + b)^p - 1]
         */
        final byte monthsInYear = 12;
        final double totalLoan = this.customerLoan; // U in formula
        final double monthlyInterest = this.customerInterest / monthsInYear / 100; // b in formula
        int numberOfPayments = this.customerYears * monthsInYear; // p in formula

        // Calculating (1 + b)^p without using Math.pow()
        double powResult = 1;
        for (int i = 0; i < numberOfPayments; i++) {
            powResult *= (1 + monthlyInterest);
        }

        this.customerMonthlyMortgage = totalLoan * ((monthlyInterest * powResult) / (powResult - 1));
    }

    /**
     * Runs calculateMonthlyMortgage to get this.customerMonthlyMortgage
     * Presents the customer in the following format:
     * Prospect X: CustomerName wants to borrow X € for a period of Z years and pay
     * E € each month
     */
    public void presentCustomer() {
        this.calculateMonthlyMortgage();

        // Currency formatter according to Locale Finland
        // Used to get nice formatting on currency
        Locale locale = new Locale("fi", "FI");
        NumberFormat cF = NumberFormat.getCurrencyInstance(locale);

        String customerInformation = String.format(
                "Prospect %d: %s wants to borrow %s for a period of %d years and pay %s each month",
                this.prospectNumber, this.customerName, cF.format(this.customerLoan), this.customerYears,
                cF.format(this.customerMonthlyMortgage));
        System.out.println(customerInformation);
    }

    /**
     * Runs calculateMonthlyMortgage to get this.customerMonthlyMortgage
     * Presents the customer in the following format:
     * Prospect X: CustomerName wants to borrow X € for a period of Z years and pay
     * E € each month
     * 
     * @return String containing customer presentation
     */
    public String presentCustomerString() {
        this.calculateMonthlyMortgage();

        // Currency formatter according to Locale Finland
        // Used to get nice formatting on currency
        Locale locale = new Locale("fi", "FI");
        NumberFormat cF = NumberFormat.getCurrencyInstance(locale);

        return String.format(
                "Prospect %d: %s wants to borrow %s for a period of %d years and pay %s each month",
                this.prospectNumber, this.customerName, cF.format(this.customerLoan), this.customerYears,
                cF.format(this.customerMonthlyMortgage));
    }
}