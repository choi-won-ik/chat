package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.service.chat.ChatService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class testController {
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/test")
	public String test(Model model, HttpServletRequest request) {
		String me = (String)request.getAttribute("me");
		return "chat/test";
	}
	
	
	@PostMapping("/test2")
	public String test2() {
		return "test2";
	}
}
