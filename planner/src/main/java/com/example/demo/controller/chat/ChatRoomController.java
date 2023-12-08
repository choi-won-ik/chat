package com.example.demo.controller.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.chat.ChatService;
import com.example.demo.service.member.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
public class ChatRoomController {
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/main")
	public String main() {
		return "/chat/mainRoom";
	}
	

	@GetMapping("/rooms")
	public ModelAndView rooms() {
		ModelAndView mv = new ModelAndView("chat/rooms");
		mv.addObject("list",chatService.findAllRooms());
		return mv; 
	}

	// 채팅창 개설
	@PostMapping(value = "/room")
    public String create(@RequestParam String name,RedirectAttributes rttr, Model model){
		model.addAttribute("roomName", chatService.createRoom(name));
		return "redirect:/chat/rooms";
    }
	
	// 채팅방 방문
	@GetMapping("/room")
	public void getRoom(String roomId, Model model){
	model.addAttribute("room",chatService.findRoomById(roomId));
	}
}
