package com.bank;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankController {
    
    @GetMapping("/")
	public String index(Model model) {
		// Create a list of customers from the file "src/main/resources/prospects.txt"
		ArrayList<Customer> customersList = CustomerFromFile.getCustomersList();
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
}
