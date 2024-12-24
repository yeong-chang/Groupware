package com.pcwk.ehr.messenger.dao;

import com.pcwk.ehr.messenger.domain.MessengerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class MessengerDaoJdbc {
    private Map<String, MessengerVO> chatRoomMap;

    @Autowired
    private JdbcTemplate jdbcTemplate;  // JdbcTemplate 의존성 주입

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

//    public MessengerVO createChatRoom(String name) {
//        MessengerVO chatRoom = MessengerVO.create(name);
//        chatRoomMap.put(String.valueOf(chatRoom.getChatRoomId()), chatRoom);
//        return chatRoom;
//    }
public MessengerVO createChatRoom(String name) {
    // 채팅방 생성
    MessengerVO chatRoom = MessengerVO.create(name);

    // chatRoomMap에 채팅방 추가 (메모리 내에서만)
    chatRoomMap.put(String.valueOf(chatRoom.getChatRoomId()), chatRoom);

    // 데이터베이스에 채팅방 정보 INSERT
    insertChatRoomToDatabase(chatRoom);

    // 생성된 채팅방 반환
    return chatRoom;
}

    private void insertChatRoomToDatabase(MessengerVO chatRoom) {
        // INSERT 쿼리 (chat_room_id는 AUTO_INCREMENT로 설정되어 있다고 가정)
        String query = "INSERT INTO MESSENGER (chat_room_id ,chat_name, chat_group_or_not, chat_participant_count) VALUES (?,?, ?, ?)";

        // JdbcTemplate을 사용하여 INSERT 실행
        int result = jdbcTemplate.update(query, chatRoom.getChatName(), chatRoom.getChatGroupOrNot(), chatRoom.getChatParticipantCount());

        // 쿼리 실행 결과 확인
        if (result > 0) {
            System.out.println("채팅방이 성공적으로 데이터베이스에 추가되었습니다.");
        } else {
            System.out.println("채팅방 추가에 실패했습니다.");
        }
    }

}
