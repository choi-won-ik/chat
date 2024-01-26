package com.example.demo.domain.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.chat.ChatMessageDTO;

@Component
public class MessageListener {

	@Autowired
	private SimpMessagingTemplate simpMessagingtemplate;

	public void listen(ChatMessageDTO message) {
		String roomId = message.getRoomId();
		int user1 = 0;
		int user2 = 0;
		
        // & 이전의 내용을 없애기
        int index = roomId.indexOf("&");
        if (index != -1) {
            user1 = Integer.parseInt(roomId.substring(0, index));
            user2 = Integer.parseInt(roomId.substring(index + 1));
            System.out.println(user1);
            System.out.println(user2);
        } 
		
		// kafka로 보낸 메시지를 지정주소로 뿌려줌(main채팅)
		simpMessagingtemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
		// side채팅
		simpMessagingtemplate.convertAndSend("/sub/chat/side/"+ user1, message);
		simpMessagingtemplate.convertAndSend("/sub/chat/side/"+ user2, message);
		
		System.out.println("WebSocket 작동 확인용!!!!!");
	}
}