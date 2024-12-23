package com.pcwk.ehr.messenger.domain;

import lombok.Data;  // getter, setter, toString, equals, hashCode 자동 생성
import lombok.AllArgsConstructor;  // 모든 필드를 인자로 받는 생성자 생성
import lombok.NoArgsConstructor;   // 기본 생성자 생성

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessengerVO {

    // 채팅방 ID
    int chatRoomId;

    // 채팅방 이름
    String chatName;

    // 그룹방 여부 (0: 개인방, 1: 그룹방)
    int chatGroupOrNot;

    // 채팅방 참여자 수
    int chatParticipantCount;

    // 채팅방 생성 메서드
    public static MessengerVO create(String chatName) {
        MessengerVO messengerVO = new MessengerVO();
        messengerVO.chatRoomId = (int) (Math.random() * 10000000); // 채팅방 ID를 랜덤하게 생성
        messengerVO.chatName = chatName;
        messengerVO.chatGroupOrNot = 10;
        messengerVO.chatParticipantCount = 1;
        return messengerVO;
    }

}
