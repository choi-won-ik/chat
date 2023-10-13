package com.example.demo.domain.kafka;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.dto.chat.ChatMessageDTO;

public class KafkaDAO {
	Map<String, ChatMessageDTO> MsgMap = new HashMap<>();
	
	public ChatMessageDTO AllMsg(String roomId) {
		MsgMap.put(roomId, null);
		return null;
	}
}