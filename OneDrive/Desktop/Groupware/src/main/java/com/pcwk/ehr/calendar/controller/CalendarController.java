package com.pcwk.ehr.calendar.controller;

import com.pcwk.ehr.calendar.dao.CalendarDaoJdbc;
import com.pcwk.ehr.calendar.domain.CalendarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/callendar")
public class CalendarController {

    @Autowired
    CalendarDaoJdbc calendarDaoJdbc;

    @GetMapping("/show.do")
    public String calendar() {
        return "board/calendar";
    }

    @PostMapping("/addEvent")  // 일정 추가 요청 처리
    public ResponseEntity<String> addEvent(@RequestBody CalendarVO inVO) {
        // 이벤트 데이터는 EventDTO 객체로 받을 수 있습니다.
        // EventDTO 클래스는 title, start, allDay와 같은 필드를 포함해야 합니다.

        try {
            // 이벤트 추가 처리 로직 (예: DB에 저장)
            calendarDaoJdbc.createEvent(inVO);
            System.out.println("이벤트 제목: " + inVO.getScheduleTitle());
            System.out.println("이벤트 시작일: " + inVO.getScheduleStartDate());
            System.out.println("All Day: " + inVO.getScheduleEndDate());

            // 성공 시 응답
            return ResponseEntity.ok("일정이 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("일정 등록에 실패했습니다.");
        }
    }

}
