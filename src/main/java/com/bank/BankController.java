package com.bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		customersList = FileHandler.getCustomersList();

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

	@PostMapping("/calculate")
	public String calculate(@RequestParam("name") String name, @RequestParam("loan") String loan, @RequestParam("interest") String interest, @RequestParam("years") String years, Model model) {
		// Create Iterator for ArrayList customerPresentations
		Iterator<String> customerIterator = customerPresentations.iterator();

		// Create new Customer
		ArrayList<String> tempCustomerInfo = new ArrayList<>();

		tempCustomerInfo.add(name);
		tempCustomerInfo.add(loan);
		tempCustomerInfo.add(interest);
		tempCustomerInfo.add(years);

		Customer tempCustomer = new Customer(tempCustomerInfo);
		customersList.add(tempCustomer);
		String calculationResult = tempCustomer.presentCustomerString().substring(12);

		model.addAttribute("customers", customerIterator);
		model.addAttribute("calculation", calculationResult);

		return "customer";
	}
}
