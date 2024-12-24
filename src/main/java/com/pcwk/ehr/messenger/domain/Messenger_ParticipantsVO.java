package com.pcwk.ehr.messenger.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messenger_ParticipantsVO {
    //채팅방 ID
    int chatRoomId;
    //사용자 ID
    int chatSenderId;
}
