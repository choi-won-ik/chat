package com.example.demo.controller.member;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.member.MemberLoginDto;
import com.example.demo.service.member.MemberService;

@Controller
@RequestMapping("/")
public class LoginController {
	private final MemberService memberService;

	public LoginController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/login-process")
	public String login(MemberLoginDto dto) {
		boolean isValidMember = memberService.isValidMember(dto.getUserid(), dto.getPw());
		if (isValidMember) {
			return "view/dashboard";
		}else {
			return "view/loginError";
		}
	}

	@PostMapping("/logout")
	public String logout() {
		return "view/login";
	}
}
