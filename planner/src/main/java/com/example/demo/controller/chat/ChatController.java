package com.example.demo.controller.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		System.out.println(chatService.talkList());
		model.addAttribute("talkList", chatService.talkList());
		return "/chat/mainRoom";
	}

	// 채팅창 개설
	@PostMapping(value = "/create")
    public String create(@RequestParam String me, RedirectAttributes rttr, Model model){
		model.addAttribute("talkList", chatService.talkList());
		return "redirect:/chat/main";
    }
	
	//채팅방 방문
	@GetMapping("/room")
	public String room(String roomId,Model model) {
		model.addAttribute("room", chatService.findByRoomId(roomId));

		System.out.println("매우매우 이상암!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		return "/chat/room";
	}

//	// 채팅방 방문
//	@GetMapping("/room")
//	public String getRoom(Long roomId, Model model){
//	model.addAttribute("room",chatService.findRoomById(roomId));
//	return "/chat/room(roomId="+roomId;
//	}
}
