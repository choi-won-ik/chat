package com.example.demo.domain.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.chat.ChatMessageDTO;

@Component
public class MessageListener {

	@Autowired
	private SimpMessagingTemplate simpMessagingtemplate;

	public void listen(ChatMessageDTO message) {
		// kafka로 보낸 메시지를 websocket으로 받아옴.
		simpMessagingtemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
		System.out.println("WebSocket 작동 확인용!!!!!");
	}
}