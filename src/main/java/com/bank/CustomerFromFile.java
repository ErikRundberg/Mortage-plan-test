package com.bank;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CustomerFromFile class
 * 
 * The CustomerFromFile class opens up the file prospects.txt and
 * returns all the customers in an ArrayList.
 * 
 * @author Erik Rundberg
 * @version 1.0
 * @since 2022-02-08
 */
public class CustomerFromFile {

    /**
     * Utility class - Can't be instantiated
     */
    private CustomerFromFile() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Opens up the file prospects.txt, encodes it into UTF-8
     * to support special characters such as é.
     * Supports finding "," in fields if they're inside quotation
     * marks and lastly returns it in an ArrayList.
     * 
     * @return Returns ArrayList containing Customer objects
     * @throws IOException
     * @exception FileNotFoundException on file missing error.
     * @see FileNotFoundException
     */
    public static ArrayList<Customer> getCustomersList() throws IOException{
        // List containing all customers as objects
        ArrayList<Customer> customers = new ArrayList<>();

        // Load file prospects.txt from /resources folder
        try (InputStream in = CustomerFromFile.class.getResourceAsStream("/prospects.txt")){
            // Use charset UTF-8 because of "åäö" and "é"
            Scanner customersScanner = new Scanner(in, "UTF-8");
            // Get rid of header containing CSV-information
            customersScanner.nextLine();
            // Go through file line-by-line, create customers and add to ArrayList customers
            while (customersScanner.hasNextLine()) {
                String currentLine = customersScanner.nextLine();
                // Check for empty or "." and skip the line
                if (currentLine.equals("") || currentLine.equals(".")) {
                    continue;
                }

                // Checks for commas inbetween citation marks so they get included
                ArrayList<String> result = new ArrayList<>();
                int start = 0;
                boolean inQuotes = false;
                for (int current = 0; current < currentLine.length(); current++) {
                    if (currentLine.charAt(current) == '\"') inQuotes = !inQuotes;
                    else if (currentLine.charAt(current) == ',' && !inQuotes) {
                        result.add(currentLine.substring(start, current));
                        start = current + 1;
                    }
                }
                result.add(currentLine.substring(start));

                customers.add(new Customer(result));
            }
            customersScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        return customers;
    }
}