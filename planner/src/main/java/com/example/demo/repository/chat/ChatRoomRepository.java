package com.example.demo.repository.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.chat.ChatRoomDTO;

import jakarta.annotation.PostConstruct;

@Repository
public class ChatRoomRepository {

	private Map<String, ChatRoomDTO> chatRoomDTOMap;

	@PostConstruct
	private void init(){
		chatRoomDTOMap = new LinkedHashMap<>();
	}
	
	// 채팅방 방문
	public ChatRoomDTO findRoomById(String id){
		return chatRoomDTOMap.get(id);
	}

	//채팅방 개설
	public ChatRoomDTO createRoom(String name) {
		String roomId = UUID.randomUUID().toString();
		ChatRoomDTO room = new ChatRoomDTO(roomId,name);
		chatRoomDTOMap.put(room.getRoomId(), room);
		return room;
	}

    public List<ChatRoomDTO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }
}