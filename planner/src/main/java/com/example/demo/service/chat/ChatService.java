package com.example.demo.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.chat.ChatRoomDTO;


@Service
public interface ChatService {
	// 채팅방 개설
	public ChatRoomDTO createRoom(String name);

	// 채팅방 방문
	public ChatRoomDTO findRoomById(Long roomId);
	
	public List findAllRooms();

	public List UserList(String userid);

	List MessagList();

	public Long Someone(String TalkerName);

	public Long roomCreate(String TalkerName, String me,Long SomeoneUserNum);

			
}