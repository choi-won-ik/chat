package com.example.demo.controller.chat;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.member.Member;
import com.example.demo.service.chat.ChatService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value = "/chat")
public class ChatRestController {
	
	@Autowired
	private ChatService chatService;
	
	// modal에서 검색할 수 있는 userList
	@PostMapping("/UserList")
	public CopyOnWriteArrayList<Member> UserList(@RequestParam("userid") String userid,Model model,HttpServletRequest request) {
		// security에 접속되어 있는 내 아이디
		String me = (String)request.getAttribute("me");

		CopyOnWriteArrayList<Member> userName = chatService.UserList(userid,me);
		
		return userName;
	}
}