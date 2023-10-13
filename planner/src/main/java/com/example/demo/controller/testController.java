package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/test")
public class testController {
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@PostMapping("/test2")
	public String test2() {
		return "test2";
	}
}
