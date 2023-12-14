package com.example.demo.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {
	private Object roomId;
	private String name;
//	private Set<WebSocketSession> sessions = new HashSet<>();
}
