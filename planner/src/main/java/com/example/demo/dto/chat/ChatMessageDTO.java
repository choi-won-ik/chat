package com.example.demo.dto.chat;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessageDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String roomId;
    private String writer;
    private String message;
    private String timestamp;
}
