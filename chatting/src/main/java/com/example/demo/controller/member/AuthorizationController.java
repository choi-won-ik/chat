package com.example.demo.controller.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.member.MemberJoinDto;
import com.example.demo.service.member.RegisterMemberService;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
	@Autowired
	private RegisterMemberService registerMemberService;

	public AuthorizationController(RegisterMemberService registerMemberService) {
		this.registerMemberService = registerMemberService;
	}

	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberJoinDto dto) {
		try {
			registerMemberService.join(dto.getUserid(), dto.getPw());
			return ResponseEntity.ok("join success");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
}
