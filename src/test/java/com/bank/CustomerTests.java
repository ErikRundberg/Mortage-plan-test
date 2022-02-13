package com.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerTests {
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	Customer customer;

	@BeforeEach
	void setUp() {
		// Capture System.out.print
		System.setOut(new PrintStream(outputStreamCaptor));
		// Reset static variable totalProspects
		Customer.resetTotalProspects();
		ArrayList<String> customerData = new ArrayList<>();
		customerData.add("Test Testsson"); 		// Name
		customerData.add("1000"); 				// Total Loan
		customerData.add("1.2"); 				// Interest Rate
		customerData.add("2"); 					// Period of loan (years)
		customer = new Customer(customerData);
	}

	@Test
	void isCustomer() {
		assertTrue(customer instanceof Customer);
	}

	@Test
	void getProspectNumber() {
		assertEquals(1, customer.getNumber());
	}

	@Test
	void getName() {
		assertEquals("Test Testsson", customer.getName());
	}

	@Test
	void getLoan() {
		assertEquals(1000, customer.getLoan());
	}

	@Test
	void getInterest() {
		assertEquals(1.2, customer.getInterest());
	}

	@Test
	void getYears() {
		assertEquals(2, customer.getYears());
	}

	@Test
	void preCalcMortgage() {
		assertEquals(0.0, customer.getMortgage());
	}

	@Test
	void postCalcMortgage() {
		customer.calculateMonthlyMortgage();
		// Using String.format to round down number as intended
		// when displayed in terminal / web. Avoiding Math package.
		assertEquals("42.19", String.format("%.2f", customer.getMortgage()));
	}

	@Test
	void printCustomerInfo() {
		customer.printCustomerInfo();
		String outputs[] = outputStreamCaptor.toString().trim().split("\r\n");

		assertEquals("Prospect #1", outputs[0]);
		assertEquals("Name: Test Testsson", outputs[1]);
		assertEquals("Total loan (€): 1000.0", outputs[2]);
		assertEquals("Interest rate (%): 1.2", outputs[3]);
		assertEquals("Total loan period (years): 2", outputs[4]);
		assertEquals("Current monthly mortgage: 42.189495511012254", outputs[5]);
	}

	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}


}
