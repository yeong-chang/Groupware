package com.pcwk.ehr.messenger.controller;

import com.pcwk.ehr.messenger.domain.MessageVO;
import com.pcwk.ehr.messenger.domain.MessengerVO;
import com.pcwk.ehr.messenger.domain.Messenger_ParticipantsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(MessengerVO messenger, Messenger_ParticipantsVO participants, MessageVO message) {
        // 채팅방이 그룹방인지 개인방인지 구분
        String joinMessage = "";

        if (messenger.getChatGroupOrNot() == 20) { // 그룹방인 경우
            joinMessage = message.getMessageReceiverID() + "님이 그룹방에 참여하셨습니다.";
        } else if (messenger.getChatGroupOrNot() == 10) { // 개인방인 경우
            joinMessage = message.getMessageReceiverID()+ "님이 개인방에 참여하셨습니다.";
        }

        // 메시지 내용 설정
        message.setMessageContent(joinMessage);

        // 해당 채팅방으로 메시지 전송
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getMessageChatRoomID(), message);
    }

}
