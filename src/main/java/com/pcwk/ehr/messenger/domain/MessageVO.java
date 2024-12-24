package com.pcwk.ehr.messenger.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO {
    //메시지 ID
    int messageID;
    //채팅방 ID
    int messageChatRoomID;
    //보낸사람 ID
    int messageReceiverID;
    //메시지 내용
    String messageContent;
    //메시지 보낸시간
    int messageAt;
    //메시지 읽음 여부
    int MessageRead;
}
