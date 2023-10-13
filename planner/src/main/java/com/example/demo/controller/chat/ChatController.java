package com.example.demo.controller.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.kafka.KafkaConstants;
import com.example.demo.dto.chat.ChatMessageDTO;

import lombok.extern.slf4j.Slf4j;

//@RequiredArgsConstructor
//@Controller
//public class ChatController {
//
//	private final SimpMessagingTemplate simpMessagingtemplate; //특정 Broker로 메세지를 전달
//
//	@MessageMapping(value = "/chat/message")
//	public void message(ChatMessageDTO message){
//		simpMessagingtemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//	}
//}


// ajax로 작성자 및 내용을 server에 보낸다.
@CrossOrigin
@RestController
@Slf4j
public class ChatController {
	@Autowired
	private KafkaTemplate<String, ChatMessageDTO> kafkaTemplate;
//	@Autowired
//	private KafkaService kafkaService;

	@MessageMapping("/chat/message")
	public void sendMessage(@RequestBody ChatMessageDTO message) {
//		log.info("Produce message : " + message.toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a hh:mm");			// a:오전/오후 시간대를 알기쉽게 나타냄
		message.setTimestamp(LocalDateTime.now().format(formatter).toString());			// 메시지 시간을 나타냄
		try {
			kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	@PostMapping("/AllMsg")
//	@ResponseBody
//	public void AllMsg(@RequestBody ChatMessageDTO message) {
//		List<ChatMessageDTO> MsgList = new ArrayList<>();
//		MsgList.add(kafkaService.AllMsg(message.getRoomId()));
//		System.out.println(MsgList);
//	}
//
//	@MessageMapping의 주소에 보내진 내용을 @sendTo에 있는 주소로 이동시킴.
//	@MessageMapping("/sendMessage")
//	@SendTo("/topic/group")
//	@MessageMapping("/chat/message")				// "pub/..."로 메시지를 보낼 시 호출 됨.
//	@SendTo("/sub/chat/room")						// "sub/..." 구독하는 사용자에게 메시지를 보냄.
//	public ChatMessageDTO broadcastGroupMessage(
//			@Payload ChatMessageDTO message				// WebSocket을 통해 수신된 메시지가 이 메서드의 매개변수로 전달됩니다.
//			)				// 이 메서드는 WebSocket을 통해 수신된 메시지를 다시 클라이언트에게 반환하는 역할을 합니다.
//	{
//		return message;
//	}			// "pub/..."에 메시지를 보내면 이 메서드가 호출되고, 해당 메시지를 다시 "sub/..."를 구도하고 있는 해당 사용자에게 반환한다.
}