package com.example.demo.service.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.chat.ChattingRoom;
import com.example.demo.domain.chat.chattingRoom;
import com.example.demo.dto.chat.ChatRoomDTO;
import com.example.demo.repository.chat.ChatRoomRepository2;
import com.example.demo.repository.chat.ChatRepository;
import com.example.demo.repository.chat.ChatRoomRepository;
import com.example.demo.repository.member.MemberRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService{
	@Autowired
	private ChatRoomRepository2 chatRoomRepository;
	@Autowired
	private ChatRoomRepository repository;
	@Autowired
	private ChatRepository kafkaRepository;
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public ChatRoomDTO createRoom(String name) {
		
		return chatRoomRepository.createRoom(name);
	}
	
	private void validateDuplicateRoom(ChattingRoom chattingRoom) {
		repository.findByName(chattingRoom.getName())
			.ifPresent(x->{
				throw new IllegalStateException("존재하는 방입니다.");
			});;
		
	}

	@Override
	public ChatRoomDTO findRoomById(Long roomId) {
		return chatRoomRepository.findRoomById(roomId);
	}

	@Override
	public List findAllRooms() {
		return chatRoomRepository.findAllRooms();
	}

	@Override
	public List MessagList() {
		return kafkaRepository.findAll();
	}

	@Override
	public List UserList(String userid) {
		return memberRepository.findByUseridStartingWith(userid);
	}

	@Override
	public Long Someone(String SomeoneName) {
		return memberRepository.findIdByUserid(SomeoneName);
	}

	@Override
	public ChatRoomDTO chattingRoom(Long SomeoneUserNum,String SomeoneName) {
		return chatRoomRepository.chattingRoom(SomeoneUserNum, SomeoneName);
	}


}
