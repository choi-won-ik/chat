package com.example.demo.controller.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.service.chat.ChatService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/chat")
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	
	// 채팅 main Page 실행
	@GetMapping("/main")
	public String main(Model model,HttpServletRequest request) {
		 String me = (String)request.getAttribute("me");
		model.addAttribute("me", me);
		List<ChattingRoom> talkList = chatService.talkList(me);
		model.addAttribute("talkList", talkList);
		System.out.println(talkList);
		return "/chat/mainRoom";
	}

	// 채팅창 개설
	@PostMapping("/create")
	public String Someone(@RequestParam("talkerName")String talkerName,HttpServletRequest request) {
		String me = (String)request.getAttribute("me");
		Long SomeoneUserNum = chatService.Someone(talkerName);
		System.out.println("여기까지 성공!!!!!!!!!!");
		
		String roomId = chatService.roomCreate(talkerName,me,SomeoneUserNum);
		
		return "redirect:/chat/room/{"+roomId+"}";
	}

	//채팅방 방문
	@PostMapping("/visit")
	public String visit(@RequestParam("talkerName")String talkerName,HttpServletRequest request) {
		String me = (String)request.getAttribute("me");
		String roomId=chatService.findRoomId(talkerName,me);
		System.out.println("TestTestTestTestTest");

		System.out.println("TestTestTestTestTest");

		return "redirect:/chat/room/"+roomId;
	}

	@GetMapping("/room/{roomId}")
	public String room(@PathVariable("roomId") String roomId,Model model,HttpServletRequest request) {
		String me = (String)request.getAttribute("me");
		model.addAttribute("talkList", chatService.talkList(me));
		model.addAttribute("roomId",roomId);

		return "chat/room";
	}

//	// 채팅방 방문
//	@GetMapping("/room")
//	public String getRoom(Long roomId, Model model){
//	model.addAttribute("room",chatService.findRoomById(roomId));
//	return "/chat/room(roomId="+roomId;
//	}
}