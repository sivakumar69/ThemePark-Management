package com.uncc.themepark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

	@RequestMapping("/homepage")
	public String welcome() {
		System.out.println("Hello!");
		return "welcome";
	}
}