package com.example.demo.repository.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.chat.ChatRoomDTO;
import com.example.demo.repository.member.MemberRepository;

import jakarta.annotation.PostConstruct;

@Component
public class ChatRoomRepository2 {

	@Autowired
	private MemberRepository memberRepository;
	
	private Map<Long, ChatRoomDTO> chatRoomDTOMap;

	@PostConstruct
	private void init(){
		chatRoomDTOMap = new LinkedHashMap<>();
	}
	
	// 채팅방 방문
	public ChatRoomDTO findRoomById(Long id){
		return chatRoomDTOMap.get(id);
	}

	//채팅방 개설
	public ChatRoomDTO createRoom(String SomeoneName) {
		Long roomId = memberRepository.findIdByUserid(SomeoneName);
		ChatRoomDTO room = new ChatRoomDTO(roomId,SomeoneName);
		chatRoomDTOMap.put(room.getRoomId(), room);
		return room;
	}

    public List<ChatRoomDTO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }
    
    // 유저 고유 id로 채팅창 생성
	public ChatRoomDTO chattingRoom(Long SomeoneUserNum,String SomeoneName) {
		ChatRoomDTO chattingRoom = new ChatRoomDTO(SomeoneUserNum, SomeoneName);
		chatRoomDTOMap.put(chattingRoom.getRoomId(), chattingRoom);
		return chattingRoom;
	}
}