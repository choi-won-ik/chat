package com.example.demo.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.chat.ChatService;

@Controller
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

	// 채팅창 개설
	@PostMapping("/create")
	public String Someone(@RequestParam("talkerName")String talkerName,RedirectAttributes rttr) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String me = authentication.getName();
		Long SomeoneUserNum = chatService.Someone(talkerName);
		System.out.println("여기까지 성공!!!!!!!!!!");
		
		rttr.addFlashAttribute("talkerName",talkerName);
		String roomId = chatService.roomCreate(talkerName,me,SomeoneUserNum);
		rttr.addFlashAttribute("roomId",roomId);
		return "redirect:/chat/room?roomId="+roomId;
	}

	//채팅방 방문
	@PostMapping("/visit")
	public String visit(@RequestParam("talkerName")String talkerName,RedirectAttributes rttr) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String me = authentication.getName();
		rttr.addFlashAttribute("talkerName", talkerName);
		String roomId=chatService.findRoomId(talkerName,me);
		rttr.addFlashAttribute("roomId", roomId);

		System.out.println("TestTestTestTestTest");

		System.out.println("TestTestTestTestTest");

		return "redirect:/chat/room?roomId="+roomId;
	}

	@GetMapping("/room")
	public String room(Model model) {
		model.addAttribute("talkList", chatService.talkList());
		return "chat/room";
	}

//	// 채팅방 방문
//	@GetMapping("/room")
//	public String getRoom(Long roomId, Model model){
//	model.addAttribute("room",chatService.findRoomById(roomId));
//	return "/chat/room(roomId="+roomId;
//	}
}