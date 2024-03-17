package com.example.demo.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.member.Member;
import com.example.demo.dto.member.MemberLoginDto;
import com.example.demo.service.member.MemberService;
import com.example.demo.service.member.ProfileService;

@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private ProfileService profileService;
	@Value("${upload.dir}")
    private String uploadDir;

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

	@PostMapping("/profile")
	public String createProfile(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user)  {
		// 현재 로그인 중인 아이디
		String me = user.getUsername();
		// 주소설정
		String webPath = uploadDir+"img/profile/";
		webPath = webPath.replace("\\", "/");
		
		try {
			profileService.addProfile(file,me,webPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/view/dashboard";
	}

	@PostMapping("/logout")
	public String logout() {
		return "view/login";
	}
}
