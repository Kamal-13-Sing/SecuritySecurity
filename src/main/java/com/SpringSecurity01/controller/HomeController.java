package com.SpringSecurity01.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {


	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String special() {
		
		return "Special User for admin only ";
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	@GetMapping("/customer")
	public String basic() {
		
		return "Basic User for customer as well as admin too";
	}

}
