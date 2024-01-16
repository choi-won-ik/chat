
package com.example.demo.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChattingRoomDTO {
	private String roomId;
	private String user1;
	private String user2;
	private String time;
}