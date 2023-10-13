package com.example.demo.domain.kafka;

import lombok.Data;

@Data
public class KafkaConstants {
	public static final String KAFKA_TOPIC = "kafka-chat";			// kafka 토픽명
	public static final String GROUP_ID = "foo";					// kafka 그룹아이디
	public static final String KAFKA_BROKER = "localhost:9092";		// kafka 주소
}