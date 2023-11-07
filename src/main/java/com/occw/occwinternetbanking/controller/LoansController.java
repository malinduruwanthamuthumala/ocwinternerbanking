package com.occw.occwinternetbanking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {
	
	@GetMapping("/loan")
	public String getAccountDetails() {
	return "Here are the loan details from the db";
}

}
