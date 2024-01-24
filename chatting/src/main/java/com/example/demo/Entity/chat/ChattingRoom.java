package com.example.demo.Entity.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chattingroom")
public class ChattingRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 채팅방의 코드 ex)3+2
	private String roomId;
	
	private String user;

	private String time;
	
	public static ChattingRoom createRoom(String roomId,String user, String time) {
		return new ChattingRoom(null, roomId, user, time);
	}
}
