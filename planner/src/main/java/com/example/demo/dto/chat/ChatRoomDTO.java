package com.example.demo.dto.chat;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Entity.member.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {
	private String roomId;
	private String name;
	private String address;
}