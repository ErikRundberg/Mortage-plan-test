package bank;

import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
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

    public void printCustomer() {
        // Prints customer's info
        System.out.println(this.customerName);
        System.out.println(this.customerLoan);
        System.out.println(this.customerInterest);
        System.out.println(this.customerYears);
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
        String customerInformation = String.format("€£$ \u20ac %s wants to borrow %s for a period of %d years and pay %s each month",this.customerName, cF.format(this.customerLoan), this.customerYears, cF.format(this.customerMonthlyMortgage));
        byte[] bytes = customerInformation.getBytes(StandardCharsets.UTF_8);
        String utf8EncondedString = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(utf8EncondedString);
    }

    public static void main(String[] args) {
        ArrayList<Customer> customers = CustomerFromFile.getCustomersList();

        customers.get(0).presentCustomer();
    }
}
