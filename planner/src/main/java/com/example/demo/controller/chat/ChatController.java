package com.example.demo.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Entity.chat.ChattingRoom;
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
	@PostMapping("/room/{roomId}")
	public String Someone(@PathVariable String roomId,@RequestParam("TalkerName") String TalkerName, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String me = authentication.getName();
		Long SomeoneUserNum = chatService.Someone(TalkerName);
		System.out.println("여기까지 성공!!!!!!!!!!");
	
		roomId = chatService.roomCreate(TalkerName,me,SomeoneUserNum);
		System.out.println(roomId);
		return "/chat/room";
	}
	
//	//채팅방 방문
//	@GetMapping("/room")
//	public String room(@RequestParam String roomId,Model model) {
//		System.out.println("채팅창 방문 컨트롤러 작동");
//		model.addAttribute("roomId", chatService.findByRoomId(roomId).getRoomId());
//		model.addAttribute("user1", chatService.findByRoomId(roomId).getUser1());
//		System.out.println("TestTestTestTestTest");
//		System.out.println(chatService.findByRoomId(roomId));
//		System.out.println("TestTestTestTestTest");
//		
//		return "/chat/room";
//	}

//	// 채팅방 방문
//	@GetMapping("/room")
//	public String getRoom(Long roomId, Model model){
//	model.addAttribute("room",chatService.findRoomById(roomId));
//	return "/chat/room(roomId="+roomId;
//	}
}
