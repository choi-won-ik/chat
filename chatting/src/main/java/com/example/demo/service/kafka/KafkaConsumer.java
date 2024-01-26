package com.example.demo.service.kafka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.chat.Chat;
import com.example.demo.Entity.chat.ChattingRoom;
import com.example.demo.domain.kafka.KafkaConstants;
import com.example.demo.domain.kafka.MessageListener;
import com.example.demo.dto.chat.ChatMessageDTO;
import com.example.demo.repository.chat.ChatRepository;
import com.example.demo.repository.chat.ChatRoomRepository;
import com.example.demo.repository.member.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
	
	@Autowired
	private ChatRepository kafkaRepository;
	@Autowired
	private MessageListener messageListener;
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	@KafkaListener(
			topics = KafkaConstants.KAFKA_TOPIC,
			groupId = KafkaConstants.GROUP_ID
		)
	public void consume(ChatMessageDTO message) {
		// a:오전/오후 시간대를 알기쉽게 나타냄
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a hh:mm");
		// 현재시간
		LocalDateTime time = LocalDateTime.now();
		// 메시지 시간을 문자열로 나타낸 후 messageDTO 셋팅
		message.setTimestamp(time.format(formatter).toString());
		// 메시지 해시 코드 지정
		String timeStr = time.toString();
		// 메시지 처리 로직을 여기에 작성합니다.
		Chat chat = Chat.messageText(message.getRoomId(),message.getWriter(), message.getMessage(), message.getTimestamp());
		kafkaRepository.save(chat);
		messageListener.listen(message);
		System.out.println("Received message: 도대체 뭐가 출력되는 거냐?" + message);
		
		
		
		// 대화상대 식별
		String roomId = message.getRoomId().replace("&", "");
		roomId = roomId.replace(String.valueOf(memberRepository.findIdByUserid(message.getWriter())),"");
		Long talkerName = Long.parseLong(roomId);
		String talkerNum = memberRepository.findUserById(talkerName);
		
		
		// 메시지를 보낸 사람으로 db저장
		ChattingRoom chattingRoom = ChattingRoom.createRoom(message.getRoomId(), message.getWriter(), message.getMessage(),timeStr );
		List<ChattingRoom> list=chatRoomRepository.findByRoomId(message.getRoomId());
		if(list.isEmpty()) {
			chatRoomRepository.save(chattingRoom);
			// 메시지를 받은 사람으로 db저장
			chatRoomRepository.save(ChattingRoom.createRoom(message.getRoomId(),talkerNum,message.getMessage(),timeStr));
		}else {
			update(list,timeStr,message.getMessage());
		}
	}	
	
	


	// db에 해당 채팅 리스트가 있을 시 시간만 업대이트 시킴.
	@Transactional
	public void update(List<ChattingRoom> list,String timeStr,String last) {
		for (ChattingRoom room : list) {
			room.setTime(timeStr);
			room.setLast(last);
			
			chatRoomRepository.save(room);
		}
	}	
	
}