package com.example.demo.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.chat.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long>{

	List<Chat> findByRoomId(String roomId);

}
