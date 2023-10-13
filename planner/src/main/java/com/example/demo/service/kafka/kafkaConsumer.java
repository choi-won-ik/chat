package com.example.demo.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.domain.kafka.KafkaConstants;
import com.example.demo.dto.chat.ChatMessageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
//@RequiredArgsConstructor
@Slf4j
public class kafkaConsumer {

	@KafkaListener(
			topics = KafkaConstants.KAFKA_TOPIC,
			groupId = KafkaConstants.GROUP_ID
		)
	public void consume(ChatMessageDTO message) {
		// 메시지 처리 로직을 여기에 작성합니다.
		log.info("Produce message : 무언가 좀 출력 해줘" + message.toString());
		System.out.println("Received message: 도대체 뭐가 출력되는 거냐?" + message);

	}
}