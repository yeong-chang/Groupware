package com.pcwk.ehr.messenger.dao;

import com.pcwk.ehr.messenger.domain.MessengerVO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class MessengerDaoJdbc {
    private Map<String, MessengerVO> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<MessengerVO> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public MessengerVO findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public MessengerVO createChatRoom(String name) {
        MessengerVO chatRoom = MessengerVO.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

}
