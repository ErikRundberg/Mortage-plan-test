package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class CustomerFromFile {

    private CustomerFromFile() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * 
     * @return                  Returns ArrayList containing Customer objects
     */
    public static ArrayList<Customer> getCustomersList() {
        // List containing all customers as objects
        ArrayList<Customer> customers = new ArrayList<>();

        try {

            // Load file from /resources folder
            File customersFile = new File(ClassLoader.getSystemClassLoader().getResource("./prospects.txt").getFile());
            // Use charset UTF-8 because of "åäö" and "é"
            Scanner customersScanner = new Scanner(customersFile, "UTF-8");
            // Get rid of header containing CSV-information
            customersScanner.nextLine();
            // Go through file line-by-line, create customers and add to ArrayList customers
            while (customersScanner.hasNextLine()) {
                String fileLine = customersScanner.nextLine();

                // Check for empty or "." and skip the line
                if (fileLine.equals("") || fileLine.equals(".")) {
                    continue;
                }

                // Creates an ArrayList so commas inbetween citation marks gets included
                ArrayList<String> result = new ArrayList<>();
                int start = 0;
                boolean inQuotes = false;
                for (int current = 0; current < fileLine.length(); current++) {
                    if (fileLine.charAt(current) == '\"') inQuotes = !inQuotes;
                    else if (fileLine.charAt(current) == ',' && !inQuotes) {
                        result.add(fileLine.substring(start, current));
                        start = current + 1;
                    }
                }
                result.add(fileLine.substring(start));

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