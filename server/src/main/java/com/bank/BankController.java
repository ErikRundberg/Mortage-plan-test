package com.bank.servingwebcontent;

import java.util.ArrayList;

import com.bank.Customer;
import com.bank.CustomerFromFile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        model.addAttribute("customers", customerPresentations);

		return "customer";
		// return String.join("\r\n", customerPresentations);
		// return String.format("Hey %s!", "Yo");
	}
}
