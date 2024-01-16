package com.example.demo.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.chat.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
public class ChatController {
	@Autowired
	private ChatService chatService;

	@GetMapping("/main")
	public String main(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userid = authentication.getName();
		model.addAttribute("me", userid);
		model.addAttribute("talkList", chatService.talkList());
		return "/chat/mainRoom";
	}
	
	//채팅방 방문
	@RequestMapping("/room")
	public String room(@RequestParam String roomId,Model model) {
		System.out.println("채팅창 방문 컨트롤러 작동");
		model.addAttribute("room", chatService.findByRoomId(roomId));
		System.out.println("TestTestTestTestTest");
		System.out.println(roomId);
		System.out.println("TestTestTestTestTest");
		
		return "chat/room";
	}

//	// 채팅방 방문
//	@GetMapping("/room")
//	public String getRoom(Long roomId, Model model){
//	model.addAttribute("room",chatService.findRoomById(roomId));
//	return "/chat/room(roomId="+roomId;
//	}
}
