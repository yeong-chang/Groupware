package com.pcwk.ehr.messenger.controller;

import com.pcwk.ehr.messenger.domain.MessengerVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pcwk.ehr.messenger.dao.MessengerDaoJdbc;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    MessengerDaoJdbc messengerDaoJdbc = new MessengerDaoJdbc();

    // 채팅 리스트 화면
    @GetMapping("/room.do")
    public String rooms(Model model) {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms.do")
    @ResponseBody
    public List<MessengerVO> room() {
        return messengerDaoJdbc.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping("/room.do")
    @ResponseBody
    public MessengerVO createRoom(@RequestParam String name) {
        return messengerDaoJdbc.createChatRoom(name);
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}.do")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}.do")
    @ResponseBody
    public MessengerVO roomInfo(@PathVariable String roomId) {
        return messengerDaoJdbc.findRoomById(roomId);
    }
}
