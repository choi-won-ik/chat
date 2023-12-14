package com.example.demo.controller.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import com.example.demo.domain.kafka.KafkaConstants;
import com.example.demo.dto.chat.ChatMessageDTO;
import com.example.demo.service.chat.ChatService;


@CrossOrigin
@RestController
public class ChatController {
	@Autowired
	private KafkaTemplate<String, ChatMessageDTO> kafkaTemplate;
	@Autowired
	private ChatService chatService;

	@MessageMapping("/chat/message")
	public void sendMessage(@RequestBody ChatMessageDTO message) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a hh:mm");			// a:오전/오후 시간대를 알기쉽게 나타냄
		message.setTimestamp(LocalDateTime.now().format(formatter).toString());			// 메시지 시간을 나타냄
		try {
			kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping("/MessageList")				//JPA에서 findAll()을 사용하여 message정보를 db에서 불러옴
	public List MessageList() {
		System.out.println("메시지 확인용!!!!!!");
		List MsgList = chatService.MessagList();
		return MsgList;
	}
	
	@PostMapping("/UserList")
	public List UserList(@RequestParam("userid") String userid) {
		System.out.println("매우매우 이상암");
//		List<String> UserName = new ArrayList<>();
//		UserName.add(UserList);
		System.out.println(userid);
		List UserName = chatService.UserList(userid);
		return UserName;
	}
	
	@PostMapping("/Someone")
	public Long Someone(@RequestParam("SomeoneName") String SomeoneName, Model model) {
		System.out.println(SomeoneName);
		System.out.println("성공!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Long SomeoneUserNum = chatService.Someone(SomeoneName);
		model.addAttribute("chattingRoom", chatService.chattingRoom(SomeoneUserNum, SomeoneName));
		
		System.out.println(SomeoneUserNum);
		return SomeoneUserNum;
	}
}