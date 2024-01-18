package com.example.demo.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.chat.ChattingRoom;

public interface ChatRoomRepository extends JpaRepository<ChattingRoom, Long>{

	ChattingRoom findByRoomId(String roomId);
}