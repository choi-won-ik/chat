package com.example.demo.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.demo.domain.kafka.KafkaConstants;
import com.example.demo.dto.chat.ChatMessageDTO;

@EnableKafka
@Configuration
public class ListenerConfiguration {
	
	@Bean
	ConcurrentKafkaListenerContainerFactory<String, ChatMessageDTO> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ChatMessageDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, ChatMessageDTO> consumerFactory() {	
		JsonDeserializer<ChatMessageDTO> deserializer = new JsonDeserializer<>(ChatMessageDTO.class);
		deserializer.setRemoveTypeHeaders(false);		// JsonDeserializer가 메시지에서 타입 정보를 제거하지 않도록 설정 'false'타입정보 유지
		deserializer.addTrustedPackages("*");			// JsonDeserializer가 신뢰할 수 있는 모든 패키지를 허용하도록 설정
		deserializer.setUseTypeMapperForKey(true);		// JsonDeserializer가 역직렬화하는 동안 Type Mapper를 사용하여 Key를 설정 'true' 타입 매퍼를 사용하여 키를 설정

		Map<String, Object> configurations = new HashMap<>();
		configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);		//kafka 주소 추가
		configurations.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.GROUP_ID);					//kafka 그룹 추가
		configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);		//kafka속성을 사용한 메시지 key값을 역직렬화 
		configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);				//Kafka 메시지의 value값을 JSON 형식으로 역직렬화
		configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");						//kafka 컨슈머가 처음 시작할 때, 이전 커밋이 없는 경우 'latest' 가장 최근 메시지부터 시작(이전에 처리된 메시지 무시)

		return new DefaultKafkaConsumerFactory<>(configurations, new StringDeserializer(), deserializer);
	}
}