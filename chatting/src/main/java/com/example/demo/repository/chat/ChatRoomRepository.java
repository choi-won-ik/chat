package com.example.demo.repository.chat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.chat.ChattingRoom;

public interface ChatRoomRepository extends JpaRepository<ChattingRoom, Long>{
	
	@Query(
			value= "SELECT roomid from chattingroom WHERE user = :me",
			nativeQuery  = true
			)
	List<String> findRoomIdByUser(@Param("me") String me);
//	List<String> findRoomIdbyUser(String me);

	CopyOnWriteArrayList<ChattingRoom> findByUser(String me);

	CopyOnWriteArrayList<ChattingRoom> findByRoomId(@Param("roomId") String roomId);
}