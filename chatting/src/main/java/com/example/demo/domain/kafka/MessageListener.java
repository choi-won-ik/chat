package com.example.demo.domain.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.chat.ChatMessageDTO;

@Component
public class MessageListener {

	@Autowired
	private SimpMessagingTemplate simpMessagingtemplate;

	public void listen(ChatMessageDTO message) {
		// kafka로 보낸 메시지를 지정주소로 뿌려줌(main채팅)
		simpMessagingtemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
		// side채팅
		simpMessagingtemplate.convertAndSend("/sub/chat/side/" + message.getRoomId(), message);
		System.out.println("WebSocket 작동 확인용!!!!!");
	}
}