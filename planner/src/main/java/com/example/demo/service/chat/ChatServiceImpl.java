package com.example.demo.service.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
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
	public List<Member> UserList(String userid,String me) {
		List<Member> UserList=memberRepository.findByUseridStartingWith(userid);

		for (Member member : UserList) {
			if(member.getUserid().equals(me)) {
				UserList.remove(member);
			}
		}
		
		return UserList;
	}

	@Override
	public Long Someone(String talkerName) {
		return memberRepository.findIdByUserid(talkerName);
	}

	// 채팅방 링크 생성
	@Override
	public String roomCreate(String talkerName, String me,Long SomeoneUserNum) {
		Long meNum = memberRepository.findIdByUserid(me);
		String roomId;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		// 내 고유 id가 상대 고유 id보다 클 때
		if(meNum>SomeoneUserNum) {
			roomId = meNum+"&"+SomeoneUserNum;
//			user1 = me;
//			user2 = talkerName;
		}
		// 내 고유 id가 생다 공유 id보다 작을 때
		else {
			roomId = SomeoneUserNum+"&"+meNum;

		}
		
		// 방 생성 시 시간 관리
		LocalDateTime nowTime =LocalDateTime.now(); 
		String time=nowTime.format(formatter);
		
		ChattingRoom chattingRoom=ChattingRoom.createRoom(roomId,me,time);
		
		// db에 내용 저장
		chatRoomRepository.save(chattingRoom);
		chatRoomRepository.save(ChattingRoom.createRoom(roomId,talkerName,time));
		
		return chattingRoom.getRoomId();
	}

	// 모든 채팅방 List를 db에서 불러옴
	public List<ChattingRoom> talkList(String me){
		
		// 내가 포함되어 있는 모든 roomId 색출
		List<ChattingRoom> roomId =chatRoomRepository.findRooIdByUser(me);
		List<ChattingRoom> talkList= new ArrayList<>();
		
		// 색출된 roomId로 상대가 있는 필드도 추가
		for (ChattingRoom chattingRoom : roomId) {
			talkList.add(chatRoomRepository.findByRoomId(chattingRoom));
		}
		
		// 검색한 채팅방 중에서 user가 내 id면 제외한다.
		for (ChattingRoom chattingRoom : talkList) {
			if(chattingRoom.getUser().equals(me)) {
				talkList.remove(chattingRoom);
			}
		}
		return talkList;
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
