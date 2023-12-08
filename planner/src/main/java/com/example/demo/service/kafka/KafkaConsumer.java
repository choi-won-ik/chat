package com.example.demo.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.domain.kafka.Chat;
import com.example.demo.domain.kafka.KafkaConstants;
import com.example.demo.domain.kafka.MessageListener;
import com.example.demo.dto.chat.ChatMessageDTO;
import com.example.demo.repository.chat.KafkaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
	
	@Autowired
	private KafkaRepository kafkaRepository;
	@Autowired
	private MessageListener messageListener;
	
	@KafkaListener(
			topics = KafkaConstants.KAFKA_TOPIC,
			groupId = KafkaConstants.GROUP_ID
		)
	public void consume(ChatMessageDTO message) {
		// 메시지 처리 로직을 여기에 작성합니다.
		Chat chat = Chat.messageText(message.getRoomId(),message.getWriter(), message.getMessage(), message.getTimestamp());
		kafkaRepository.save(chat);
		messageListener.listen(message);
		System.out.println("Received message: 도대체 뭐가 출력되는 거냐?" + message);

	}
}