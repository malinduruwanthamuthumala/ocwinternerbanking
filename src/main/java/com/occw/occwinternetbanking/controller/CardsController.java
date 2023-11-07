package com.occw.occwinternetbanking.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class CardsController {

	@GetMapping("/cards")
	public String getAccountDetails() {
	return "Here are the account details from the db";
}
}
