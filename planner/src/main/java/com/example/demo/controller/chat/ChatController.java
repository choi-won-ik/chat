package com.example.demo.controller.chat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.chat.Chat;
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
		
		// main하면 좌측에 있는 채팅리스트
		CopyOnWriteArrayList<ChattingRoom> talkList = chatService.talkList(me);
		model.addAttribute("talkList", talkList);
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
		List<Chat> MsgList = chatService.MessagList(roomId);
		
		for (Chat chat : MsgList) {
			System.out.println(chat);
		}
		
		// roomId 정의
		model.addAttribute("roomId",roomId);
		// 채팅 상대방 정의
		model.addAttribute("talkerName",chatService.roomTalkerName(me, roomId));
		// 좌측 채팅리스트 정의
		model.addAttribute("talkList", chatService.talkList(me));
		// db 저장된 채팅내용 출력
		model.addAttribute("MsgList",MsgList);
		return "chat/room";
	}
	


//	// 채팅방 방문
//	@GetMapping("/room")
//	public String getRoom(Long roomId, Model model){
//	model.addAttribute("room",chatService.findRoomById(roomId));
//	return "/chat/room(roomId="+roomId;
//	}
}