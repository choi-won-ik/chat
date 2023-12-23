package com.example.demo.repository.chat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.chat.ChattingRoom;


public interface ChatRoomRepository extends JpaRepository<ChattingRoom, Long>{
	Optional<ChattingRoom> findByName(String name);
}
