package com.example.demo.service.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.Entity.member.Member;
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
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private ChatRepository kafkaRepository;
	@Autowired
	private MemberRepository memberRepository;

	private void validateDuplicateRoom(ChattingRoom chattingRoom) {
		repository.findByName(chattingRoom.getName())
			.ifPresent(x->{
				throw new IllegalStateException("존재하는 방입니다.");
			});
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
	public Long Someone(String TalkerName) {
		return memberRepository.findIdByUserid(TalkerName);
	}

	@Override
	public Long roomCreate(String TalkerName, String me,Long SomeoneUserNum) {
		Long meNum = memberRepository.findIdByUserid(me);
		String roomName;
		String addressName ;
		if(meNum>SomeoneUserNum) {
			roomName = meNum+"&"+SomeoneUserNum;
			addressName = "주소";

		}else {
			roomName = SomeoneUserNum+"&"+meNum;
			addressName = "주소";
		}
		ChattingRoom chattingRoom = ChattingRoom.createRoom(roomName,addressName);
		validateDuplicateMember(chattingRoom);
		chatRoomRepository.save(chattingRoom);
		
		return chattingRoom.getId();
	}
	
	private void validateDuplicateMember(ChattingRoom chattingRoom) {
		chatRoomRepository.findByRoomId(chattingRoom.getRoomId())
						.ifPresent(x->{
							throw new IllegalArgumentException();
						});
	}
	
}
