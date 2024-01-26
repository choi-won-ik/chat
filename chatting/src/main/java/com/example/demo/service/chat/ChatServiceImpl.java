package com.example.demo.service.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.chat.Chat;
import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.Entity.member.Member;
import com.example.demo.repository.chat.ChatRepository;
import com.example.demo.repository.chat.ChatRoomRepository;
import com.example.demo.repository.member.MemberRepository;

import jakarta.transaction.Transactional;
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
	public List<Chat> MessagList(String roomId) {
		return kafkaRepository.findByRoomId(roomId);
	}

	// 모달에 있는 userList에서 내 이름만 제외
	@Override
	public CopyOnWriteArrayList<Member> UserList(String userid,String me) {
		// ConcurrentModificationException예외를 처리하기 위해 concurrent사용
		CopyOnWriteArrayList<Member> UserList=memberRepository.findByUseridStartingWith(userid);
		
		// UserList에서 내 이름만 제외
		for (Member member : UserList) {
			if(member.getUserid().equals(me)) {
				UserList.remove(member);
			}
		}
		
		return UserList;
	}


	
	

	// 모든 채팅방 List를 db에서 불러옴
	public List<ChattingRoom> talkList(String me){
		// db에서 내 아이디에 해당하는 roomId 가져옴
		List<String> roomId = chatRoomRepository.findRoomIdByUser(me);
		
		// roomId에 해당하는 채팅방을 모두 담음(addAll이 아는 db쿼리문을 조정하는 방법도 있음)
		List<ChattingRoom> talkList= new CopyOnWriteArrayList<>();
		for (String str : roomId) {
			talkList.addAll(chatRoomRepository.findByRoomId(str));
		}
		
		// 내 아이디에 해당 하는 db내용을 제거함
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

	@Override
	public String roomTalkerName(String me, String roomId) {
		// 상대방 고유 id 추출
		roomId = roomId.replace("&", "");
		roomId = roomId.replace(String.valueOf(memberRepository.findIdByUserid(me)), "");
		long talkerName = Long.parseLong(roomId);
		
		return memberRepository.findUserById(talkerName);
	}

	@Override
	public Long number(String me) {
		return memberRepository.findIdByUserid(me);
	}


}
