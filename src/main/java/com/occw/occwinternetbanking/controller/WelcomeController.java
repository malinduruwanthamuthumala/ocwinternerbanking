package com.occw.occwinternetbanking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping("/occwp/pwelcome")
	public String welcome() {
		return "Welcome to OCCW online banking";
	}
	
	//main package ekata pitin controller classes hadanwanam @ComponenScan annotation eka use karala opackage names tika denna ona

}
