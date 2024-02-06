package com.example.demo.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.chat.ChatMessageDTO;
import com.example.demo.dto.kafka.KafkaConstants;

@CrossOrigin
@Controller
public class messageController {
	
	@Autowired
	private KafkaTemplate<String, ChatMessageDTO> kafkaTemplate;
	
	@MessageMapping("chat/message")
	public void sendMessage(@PathVariable("roomId") String roomId ,@RequestBody ChatMessageDTO message) {
		try {
			kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
