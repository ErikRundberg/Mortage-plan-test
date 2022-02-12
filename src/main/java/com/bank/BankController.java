package com.bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankController {
	ArrayList<Customer> customersList = new ArrayList<>();
	ArrayList<String> customerPresentations = new ArrayList<>();

	/**
	 * Method that gets called once on startup
	 * Creates a list of Customers and then adds
	 * all of the customers' presentations into
	 * the ArrayList customerPresentations
	 * @throws IOException
	 * 
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void startUp() throws IOException {
		// Create a list of customers from the file "src/main/resources/prospects.txt"
		customersList = CustomerFromFile.getCustomersList();

		// Iterate through customers list printing out customer info and monthly payment
		for (Customer customer : customersList) {
			customerPresentations.add(customer.presentCustomerString());
		}
	}

	/**
	 * Index Route (/)
	 * 
	 * Takes ArrayList customerPresentations and turns
	 * it into an Iterator which gets added to the
	 * Model so we can iterate in the customer.html
	 * template.
	 * 
	 * @return view customer.html (resources/static/templates/customer.html)
	 */
    @GetMapping("/")
	public String index(Model model) {
		// Create Iterator for ArrayList customerPresentations
		Iterator<String> customerIterator = customerPresentations.iterator();

		model.addAttribute("customers", customerIterator);

		return "customer";
	}
}
