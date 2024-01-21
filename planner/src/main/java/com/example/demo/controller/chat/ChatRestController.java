package com.example.demo.controller.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.chat.Chat;
import com.example.demo.Entity.member.Member;
import com.example.demo.domain.kafka.KafkaConstants;
import com.example.demo.dto.chat.ChatMessageDTO;
import com.example.demo.service.chat.ChatService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value = "/chat")
public class ChatRestController {
	@Autowired
	private KafkaTemplate<String, ChatMessageDTO> kafkaTemplate;
	@Autowired
	private ChatService chatService;

	@MessageMapping("/message")
	public void sendMessage(@RequestBody ChatMessageDTO message) {
		// a:오전/오후 시간대를 알기쉽게 나타냄
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a hh:mm");
		// 메시지 시간을 나타냄
		message.setTimestamp(LocalDateTime.now().format(formatter).toString());
		try {
			kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//JPA에서 findAll()을 사용하여 message정보를 db에서 불러옴
	@PostMapping("/MessageList")
	public List<Chat> MessageList() {
		System.out.println("메시지 확인용!!!!!!");
		List<Chat> MsgList = chatService.MessagList();
		return MsgList;
	}
	
	@PostMapping("/UserList")
	public List<Member> UserList(@RequestParam("userid") String userid,Model model,HttpServletRequest request) {
		// security에 접속되어 있는 내 아이디
//		String me = (String) model.getAttribute("me");
		String me = (String)request.getAttribute("me");
		
		System.out.println("TestTestTestTestTestTestTestTestTestTest");
		System.out.println(me);
		System.out.println("TestTestTestTestTestTestTestTestTestTest");
		
		List<Member> userName = chatService.UserList(userid,me);
		
		return userName;
	}
}