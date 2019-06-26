package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminsController {
	
	@RequestMapping(value = "/exhibitor/login" , method = RequestMethod.GET)
	public String adminLogin() {
		return "/exhibitorLogin.html";
	}

}
