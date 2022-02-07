import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerFromFile {

    /**
     * 
     * @param directoryPath     Path to file containing customer information
     * @return                  Returns ArrayList containing Customer objects
     */
    public static ArrayList<Customer> getCustomersList(String directoryPath) {
        // List containing all customers as objects
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            File customersFile = new File(directoryPath);
            // Use charset UTF-8 because of "åäö" and "é"
            Scanner customersScanner = new Scanner(customersFile, "UTF-8");

            // Get rid of header containing CSV-information
            customersScanner.nextLine();

            // Go through file, create customers and add to ArrayList customers
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

    public static void main(String[] args) {
        ArrayList<Customer> customers = getCustomersList("prospects.txt");

        customers.get(0).printCustomer();
    }
}