package com.example.demo.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.member.ProfileService;

@Controller
@RequestMapping("/view")
public class ViewController {

	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/login")
	public String loginPage() {	
		return "view/login";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
		String me = user.getUsername();
		model.addAttribute("loginId", me);
		model.addAttribute("byte", profileService.findProfile(me));

		return "view/dashboard";
	}

	@GetMapping("/join")
	public String joinPage() {
		return "view/join";
	}
	
	@GetMapping("/loginError")
	public String loginError() {
		return "view/loginError";
	}
}