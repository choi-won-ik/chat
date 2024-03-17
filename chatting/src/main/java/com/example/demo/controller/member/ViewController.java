package com.example.demo.controller.member;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.member.Member;
import com.example.demo.service.member.MemberService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/view")
@Log4j2
public class ViewController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public String loginPage() {	
		return "view/login";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
		String me = user.getUsername();
		model.addAttribute("loginId", me);
		Member member = memberService.findByUserid(me);
		
		String data;
		String extensions;
//		byte[] data = profileService.findProfile(me);
		if(member.getProfile()==1) {
			// byte로 저장된 배열을 Base64로 변경
			data = Base64.getEncoder().encodeToString(member.getData());
			// 파일 확장자 정의
			extensions=member.getExtensions();
		}else {
			data = null;
			extensions=null;
		}
		
		model.addAttribute("data", data);
		model.addAttribute("extensions",extensions);

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