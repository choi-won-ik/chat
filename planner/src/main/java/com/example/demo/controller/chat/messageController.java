package com.example.demo.controller.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.domain.kafka.KafkaConstants;
import com.example.demo.dto.chat.ChatMessageDTO;

@CrossOrigin
@Controller
public class messageController {
	
	@Autowired
	private KafkaTemplate<String, ChatMessageDTO> kafkaTemplate;
	
	@MessageMapping("chat/message")
	@SendTo("/sub/chat/room/{roomId}")
	public void sendMessage(@PathVariable("roomId") String roomId ,@RequestBody ChatMessageDTO message) {
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
}
