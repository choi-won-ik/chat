package com.example.demo.service.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.chat.Chat;
import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.Entity.member.Member;
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

	@Override
	public List<Chat> MessagList() {
		return kafkaRepository.findAll();
	}

	@Override
	public List<Member> UserList(String userid) {
		return memberRepository.findByUseridStartingWith(userid);
	}

	@Override
	public Long Someone(String talkerName) {
		return memberRepository.findIdByUserid(talkerName);
	}

	// 채팅방 링크 생성
	@Override
	public String roomCreate(String talkerName, String me,Long SomeoneUserNum) {
		Long meNum = memberRepository.findIdByUserid(me);
		String roomName;
		String  user1;
		String user2;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		// 내 고유 id가 상대 고유 id보다 클 때
		if(meNum>SomeoneUserNum) {
			roomName = meNum+"&"+SomeoneUserNum;
			user1 = me;
			user2 = talkerName;
		}
		// 내 고유 id가 생다 공유 id보다 작을 때
		else {
			roomName = SomeoneUserNum+"&"+meNum;
			user1 = talkerName;
			user2 = me;
		}
		
		// 방 생성 시 시간 관리
		LocalDateTime nowTime =LocalDateTime.now(); 
		String time=nowTime.format(formatter);
		
		// db에 내용 저장
		ChattingRoom chattingRoom = ChattingRoom.createRoom(roomName,user1,user2,time);
		chatRoomRepository.save(chattingRoom);
		
		return chattingRoom.getRoomId();
	}

	// 모든 채팅방 List를 db에서 불러옴
	public List<ChattingRoom> talkList(){
		return chatRoomRepository.findAll();
	}
	
	public ChattingRoom findByRoomId(String roomId) {		
		return chatRoomRepository.findByRoomId(roomId);
	}

	@Override
	public String findRoomId(String talkerName, String me) {
		long youNum= memberRepository.findIdByUserid(talkerName);
		long meNum = memberRepository.findIdByUserid(me);
		String roomId ;
		if(youNum>meNum) {
			roomId = youNum+"&"+meNum;
		}else {
			roomId = meNum+"&"+youNum;
		}
		return roomId;
	}


}
