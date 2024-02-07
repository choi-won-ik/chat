package com.example.demo.Entity.chat;

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
@Table(name = "chat")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@Column(name="roomid",length = 20, nullable = false)
	private String roomId;
	
	@Column(length = 11, nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private String message;
	
	@Column(nullable = false)
	private String timestamp;
	
	public static Chat messageText(String roomId,String writer,String message,String timestamp) {
		return new Chat(null,roomId,writer,message,timestamp);
	}
}
