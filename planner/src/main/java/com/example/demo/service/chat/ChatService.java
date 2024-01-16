package com.example.demo.service.chat;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.dto.chat.ChattingRoomDTO;


@Service
public interface ChatService {
	

	public List UserList(String userid);

	List MessagList();

	public Long Someone(String TalkerName);

	public Long roomCreate(String TalkerName, String me,Long SomeoneUserNum);

	public List<ChattingRoom> talkList();

	public ChattingRoomDTO findByRoomId(String roomId);

			
}