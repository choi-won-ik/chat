package com.example.demo.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.kafka.Chat;

public interface KafkaRepository extends JpaRepository<Chat, Long>{

}
