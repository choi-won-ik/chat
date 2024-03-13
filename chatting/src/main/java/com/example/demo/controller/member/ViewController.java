package com.example.demo.controller.member;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.member.ProfileService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/view")
@Log4j2
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
		byte[] data = profileService.findProfile(me);

        // byte 배열을 Base64로 변환
        String base64String = Base64.getEncoder().encodeToString(data);
        System.out.println(base64String);
        
		model.addAttribute("data", base64String);

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