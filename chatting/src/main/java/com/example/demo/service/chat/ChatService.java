package com.example.demo.service.chat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.chat.Chat;
import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.Entity.member.Member;

@Service
public interface ChatService {
	List<Chat> MessagList(String roomId);
	
	CopyOnWriteArrayList<Member> UserList(String userid,String me);

	List<ChattingRoom> talkList(String me);

	String findRoomId(String talkerName, String me);

	String roomTalkerName(String me, String roomId);

	boolean proper(Long meNum,String roomId);

	int receive(String roomId,String me);
			
}