package com.example.demo.repository.chat;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.chat.ChattingRoom;

public interface ChatRoomRepository extends JpaRepository<ChattingRoom, Long>{
	Optional<ChattingRoom> findByRoomId(String roomId);
	
//	@Query(
//			value= "select room_id from chattingroom",
//			nativeQuery  = true
//			)
//	List<String> findAllByRoomId();
}
