package com.example.demo.service.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.chat.ChatRoomDTO;
import com.example.demo.repository.chat.ChatRoomRepository;
import com.example.demo.repository.chat.KafkaRepository;
import com.example.demo.repository.member.MemberRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService{
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private KafkaRepository kafkaRepository;
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public ChatRoomDTO createRoom(String name) {
		return chatRoomRepository.createRoom(name);
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
