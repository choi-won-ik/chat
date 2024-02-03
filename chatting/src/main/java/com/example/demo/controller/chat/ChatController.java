package com.example.demo.controller.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.chat.Chat;
import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.service.chat.ChatService;

@Controller
@RequestMapping(value = "/chat")
public class ChatController {
	@Autowired
	private ChatService chatService;	
	
	// 채팅 main Page 실행
	@GetMapping("/main")
	public String main(Model model,@AuthenticationPrincipal User user) {
		String me = user.getUsername();
		model.addAttribute("me", me);
		
		// main하면 좌측에 있는 채팅리스트
		List<ChattingRoom> talkList = chatService.talkList(me);
		for (ChattingRoom test : talkList) {
			System.out.println(test);
		}
		// 좌측 채팅 리스트
		model.addAttribute("talkList", talkList);
		// 메시지 받기용 내 고유아이디
		model.addAttribute("meNum",chatService.number(me));
		
		return "/chat/mainRoom";
	}

	//채팅방 방문
	@PostMapping("/visit")
	public String visit(@RequestParam("talkerName")String talkerName,@AuthenticationPrincipal User user) {
		String me = user.getUsername();
		String roomId=chatService.findRoomId(talkerName,me);
		chatService.receive(roomId,me);
		
		return "redirect:/chat/room/"+roomId;
	}

	@GetMapping("/room/{roomId}")
	public String room(@PathVariable("roomId") String roomId,Model model,@AuthenticationPrincipal User user) {
		String me = user.getUsername();
		List<Chat> MsgList = chatService.MessagList(roomId);
		
		// roomId 정의
		model.addAttribute("roomId",roomId);
		// 채팅 상대방 정의
		model.addAttribute("talkerName",chatService.roomTalkerName(me, roomId));
		// 좌측 채팅리스트 정의
		model.addAttribute("talkList", chatService.talkList(me));
		// db 저장된 채팅내용 출력
		model.addAttribute("MsgList",MsgList);
		// 안 읽은 상태에서 읽음 상태로 변경
		model.addAttribute("receive",chatService.receive(roomId,me));
		System.out.println(chatService.receive(roomId,me));
		// 메시지 받기용 내 고유아이디
		Long meNum=chatService.number(me);
		model.addAttribute("meNum",meNum);
		
		// 권한이 없는 채팅방에는 입장 불가능
		if(chatService.proper(meNum,roomId)) {
			return "chat/room";
		}else {
			return "error/chattingRoomError";
		}
	}
}