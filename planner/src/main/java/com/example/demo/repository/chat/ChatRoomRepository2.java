package com.example.demo.repository.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.chat.ChattingRoomDTO;
import com.example.demo.repository.member.MemberRepository;

import jakarta.annotation.PostConstruct;

@Component
public class ChatRoomRepository2 {

	@Autowired
	private MemberRepository memberRepository;
	
	private Map<Long, ChattingRoomDTO> chatRoomDTOMap;

	@PostConstruct
	private void init(){
		chatRoomDTOMap = new LinkedHashMap<>();
	}
	
	// 채팅방 방문
	public ChattingRoomDTO findRoomById(Long id){
		return chatRoomDTOMap.get(id);
	}

	//채팅방 개설
	public ChattingRoomDTO createRoom(String SomeoneName) {
		Long roomId = memberRepository.findIdByUserid(SomeoneName);
		ChattingRoomDTO room = new ChattingRoomDTO(roomId,SomeoneName);
		chatRoomDTOMap.put(room.getRoomId(), room);
		return room;
	}

    public List<ChattingRoomDTO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChattingRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }
    
    // 유저 고유 id로 채팅창 생성
	public ChattingRoomDTO chattingRoom(Long SomeoneUserNum,String SomeoneName) {
		ChattingRoomDTO chattingRoom = new ChattingRoomDTO(SomeoneUserNum, SomeoneName);
		chatRoomDTOMap.put(chattingRoom.getRoomId(), chattingRoom);
		return chattingRoom;
	}
}