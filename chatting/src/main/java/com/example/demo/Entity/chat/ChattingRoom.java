package com.example.demo.Entity.chat;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
	@Column(unique = true, nullable = false)
	private Long id;
	
	// 채팅방의 코드 ex)3+2
	@Column(nullable = false)
	private String roomId;
	
	@Column(length = 11, nullable = false)
	private String user;

	@Column(nullable = false)
	private String last;
	
	@Column(nullable = false)
	private LocalDateTime time;
	
	@Column(nullable = false, length = 1)
	private int receive;
	
	public static ChattingRoom createRoom(String roomId,String user,String last, LocalDateTime time,int receive) {
		return new ChattingRoom(null, roomId, user,last, time,receive);
	}
}
