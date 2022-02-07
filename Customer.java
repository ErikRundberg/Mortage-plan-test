import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Customer {
    // Stores customer's name, total loan, interest rate and how many years
    private final String customerName;
    private final double customerLoan;
    private final double customerInterest;
    private final int customerYears;
    private double customerMonthlyCost;

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

    public void calculateMonthlyPayment() {
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

        this.customerMonthlyCost = totalLoan * ((monthlyInterest * powResult) / (powResult - 1));
    }

    // Presents the customer in the format:
    // CustomerName wants to borrow X € for a period of Z years and pay E € each month
    public void presentCustomer() {
        this.calculateMonthlyPayment();
        String customerInformation = String.format("%s wants to borrow %f \u20ac for a period of %d years and pay %f each month",this.customerName, this.customerLoan, this.customerYears, this.customerMonthlyCost);
        System.out.println(customerInformation);
    }

    public static void main(String[] args) {
        ArrayList<Customer> customers = CustomerFromFile.getCustomersList("prospects.txt");

        customers.get(0).presentCustomer();
    }
}

