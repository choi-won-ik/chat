package com.example.demo.controller.member;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")

public class ViewController {

	@GetMapping("/login")
	public String loginPage() {
		return "view/login";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("loginId", user.getUsername());

		return "view/dashboard";
	}

	@GetMapping("/join")
	public String joinPage() {
		return "view/join";
	}
}