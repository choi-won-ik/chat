package com.example.demo.repository.chat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.dto.chat.ChatRoomDTO;


public interface ChatRoomRepository extends JpaRepository<ChattingRoom, Long>{
	Optional<ChattingRoom> findByName(String name);

	Optional<ChatRoomDTO> findByRoomId(String roomId);
}
