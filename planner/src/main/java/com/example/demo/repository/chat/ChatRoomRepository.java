package com.example.demo.repository.chat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.chat.ChattingRoom;

public interface ChatRoomRepository extends JpaRepository<ChattingRoom, Long>{
	@Query(
			value= "SELECT user1 from chattingroom u WHERE room_id = :roomId",
			nativeQuery  = true
			)
	String findUser1ByRoomId(@Param("roomId")String roomId);
	@Query(
			value= "SELECT user2 from chattingroom u WHERE room_id = :roomId",
			nativeQuery  = true
			)
	String findUser2ByRoomId(@Param("roomId")String roomId);
	@Query(
			value= "SELECT time from chattingroom u WHERE room_id = :roomId",
			nativeQuery  = true
			)
	String findTimeByRoomId(@Param("roomId")String roomId);
}