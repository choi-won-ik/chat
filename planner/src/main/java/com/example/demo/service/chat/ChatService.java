package com.example.demo.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.chat.Chat;
import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.Entity.member.Member;

@Service
public interface ChatService {
	List<Chat> MessagList();
	
	public List<Member> UserList(String userid);

	public Long Someone(String talkerName);

	public String roomCreate(String talkerName, String me,Long SomeoneUserNum);

	public List<ChattingRoom> talkList();

	public ChattingRoom findByRoomId(String roomId);

	String findRoomId(String talkerName, String me);



			
}