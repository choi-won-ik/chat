package com.example.demo.domain.kafka;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="roomid")
	private String roomId;
	
	private String writer;
	
	private String message;
	
	private String timestamp;
	
	public static Chat messageText(String roomId,String writer,String message,String timestamp) {
		return new Chat(null,roomId,writer,message,timestamp);
	}
}
