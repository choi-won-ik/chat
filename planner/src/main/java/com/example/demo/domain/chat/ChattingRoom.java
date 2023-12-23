package com.example.demo.domain.chat;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.member.Member;

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
	
	private Long roomId;
	
	private String name;
	
	public static ChattingRoom createRoom(Long roomId, String name ) {
		return new ChattingRoom(null, roomId, name);
	}
	
}
