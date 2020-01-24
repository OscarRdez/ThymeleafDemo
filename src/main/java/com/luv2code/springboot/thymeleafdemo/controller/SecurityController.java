package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}

	@RequestMapping("/showRegisterForm")
	public String showRegisterForm(Model theModel) {

		return "user-register";
	}

	@RequestMapping("/addUser")
	public String userRegister() {
		return "redirect:/employees/list";
	}

}
