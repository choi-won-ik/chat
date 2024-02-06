package com.example.demo.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.demo.dto.chat.ChatMessageDTO;
import com.example.demo.dto.kafka.KafkaConstants;

@EnableKafka
@Configuration
public class ProducerConfiguration {

	@Bean
	public ProducerFactory<String, ChatMessageDTO> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigurations());
	}

	@Bean
	public Map<String, Object> producerConfigurations() {
		Map<String, Object> configurations = new HashMap<>();
		configurations.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
		configurations.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configurations.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return configurations;
	}

	@Bean
	public KafkaTemplate<String, ChatMessageDTO> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
