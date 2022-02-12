/**
 * The App program implements an application that
 * reads from the file 'prospects.txt' and creates
 * Customer objects. The application can then
 * calculate the customer's monthly mortgage
 * and display it to the standard output.
 * 
 * @author  Erik Rundberg
 * @version 1.0
 * @since   2022-02-08
 */

package com.bank;

import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws IOException{
        // Create a list of customers from the file "src/main/resources/prospects.txt"
        ArrayList<Customer> customersList = CustomerFromFile.getCustomersList();

        // Iterate through customers list printing out customer info and monthly payment
        for (Customer customer : customersList) {
            customer.presentCustomer();
        }
    }
}
