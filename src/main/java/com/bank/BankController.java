/**
 * Controller with routing
 * 
 * @author Erik Rundberg
 * @version 1.0
 * @since 2022-02-11
 */

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

	/**
	 * Method that gets called once on startup
	 * Creates a list of all the Customers from
	 * the file prospects.txt and adds them to
	 * customersList.
	 * @throws IOException
	 * 
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void startUp() throws IOException {
		// Create a list of customers from the file "src/main/resources/prospects.txt"
		customersList = FileHandler.getCustomersList();
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
		ArrayList<String> customerPresentations = new ArrayList<>();

		// Iterate through customers list printing out customer info and monthly payment
		for (Customer customer : customersList) {
			customerPresentations.add(customer.presentCustomerString());
		}

		// Create Iterator for ArrayList customerPresentations
		Iterator<String> customerIterator = customerPresentations.iterator();

		model.addAttribute("customers", customerIterator);

		return "customer";
	}

	/**
	 * 
	 * @param name		Name of prospect
	 * @param loan		Total loan amount
	 * @param interest	Yearly interest
	 * @param years		Loan period in years
	 * @param model		Thymeleaf template model
	 * @return			Redirects to Index ("/")
	 */
	@PostMapping("/calculate")
	public String calculate(@RequestParam("name") String name, @RequestParam("loan") String loan, @RequestParam("interest") String interest, @RequestParam("years") String years, Model model) {
		// Create new Customer
		ArrayList<String> tempCustomerInfo = new ArrayList<>();

		tempCustomerInfo.add(name);
		tempCustomerInfo.add(loan);
		tempCustomerInfo.add(interest);
		tempCustomerInfo.add(years);

		Customer tempCustomer = new Customer(tempCustomerInfo);
		customersList.add(tempCustomer);

		return "redirect:/";
	}
}
