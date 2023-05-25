package com.ssafy.kiwi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/attraction")
	public String attraction() {
		return "attraction";
	}

	@GetMapping("/admin/userlist")
	public String userList() {
		return "admin/userlist";
	}
}
