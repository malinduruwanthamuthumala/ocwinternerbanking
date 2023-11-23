package com.occw.occwinternetbanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.occw.occwinternetbanking.jpa.components.Authority;
import com.occw.occwinternetbanking.jpa.components.Customer;
import com.occw.occwinternetbanking.jpa.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/customerregistration")
	public ResponseEntity<?> persistCustomer ( @RequestBody Customer customer) {
		ResponseEntity<?> response = null;
		try {
			String hashedPassword = passwordEncoder.encode(customer.getPassword());
			customer.setPassword(hashedPassword);
			Customer c = customerRepository.save(customer);
			if (c.getId() != null) {
				response = ResponseEntity.status(HttpStatus.CREATED)
						.body(c);
			}
		} catch  (Exception e){
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
		
		return response;
		
	}

	
	@GetMapping("/occwp/loand")
	public String getAccountDetails() {
	return "Here are the loan details from the db";
}
	
	@GetMapping("/user")
	public Customer getUserDetails(Authentication auth) {
	List<Customer> c = customerRepository.findByEmail(auth.getName());
	if(c.size()>0) {
		return c.get(0);
	} else {
		return null;
	}
}
}
