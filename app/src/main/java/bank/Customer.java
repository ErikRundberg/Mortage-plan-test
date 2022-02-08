package bank;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Customer {
    private final String customerName;      // Customer's name
    private final double customerLoan;      // Customer's total loan amount
    private final double customerInterest;  // Customer's interest rate
    private final int customerYears;        // Customer's loan period in years
    private double customerMonthlyMortgage;     // Customer's monthly mortgage cost (Calculated through the method Customer.calculateMonthlyMortgage)

    // Constructor that initializes Customer object
    Customer(ArrayList<String> customerInformation) {
        this.customerName = customerInformation.get(0);
        this.customerLoan = Double.parseDouble(customerInformation.get(1));
        this.customerInterest = Double.parseDouble(customerInformation.get(2));
        this.customerYears = Integer.parseInt(customerInformation.get(3));
    }

    public void printCustomerInfo() {
        // Prints customer's info
        System.out.println("Name: " + this.customerName);
        System.out.println("Total loan: " + this.customerLoan);
        System.out.println("Interest rate (%): " + this.customerInterest);
        System.out.println("Total loan period (years): " + this.customerYears);
        System.out.println("Current monthly mortgage: " + this.customerMonthlyMortgage);
    }

    public void calculateMonthlyMortgage() {
        /*
            Formula for fixed monthly payment
            E = Fixed monthly payment
            b = Interest on a monthly basis
            U = Total loan
            p = Number of payments
            E = U[b(1 + b)^p]/[(1 + b)^p - 1]
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

    // Runs calculateMonthlyMortgage to get this.customerMonthlyMortgage
    // Presents the customer in the format:
    // CustomerName wants to borrow X € for a period of Z years and pay E € each month
    public void presentCustomer() {
        this.calculateMonthlyMortgage();

        Locale locale = new Locale("fi", "FI");
        // currencyFormatter according to Locale Finland
        // Used to get nice formatting on currency
        NumberFormat cF = NumberFormat.getCurrencyInstance(locale);
        String customerInformation = String.format("%s wants to borrow %s for a period of %d years and pay %s each month",this.customerName, cF.format(this.customerLoan), this.customerYears, cF.format(this.customerMonthlyMortgage));
        System.out.println(customerInformation);
    }
}

