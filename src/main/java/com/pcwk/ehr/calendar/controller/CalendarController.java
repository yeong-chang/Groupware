package com.pcwk.ehr.calendar.controller;

import com.google.gson.Gson;
import com.pcwk.ehr.calendar.dao.CalendarDaoJdbc;
import com.pcwk.ehr.calendar.domain.CalendarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarDaoJdbc calendarDaoJdbc;  // DAO 객체 주입

    // 일정 페이지로 이동
    @GetMapping(value = "/show.do", produces = "application/json; charset=UTF-8")
    public String calendar(Model model) {
        // 일정 데이터를 가져와서 뷰로 전달
        try {
            List<Map<String, Object>> events = calendarDaoJdbc.getCalendarEvents();
            List<Map<String, String>> jsonEvents = new ArrayList<>();
            for (Map<String, Object> event : events) {
                Map<String, String> jsonEvent = new HashMap<>();
                jsonEvent.put("title", (String) event.get("SCHEDULE_TITLE"));
                jsonEvent.put("start", ((Timestamp) event.get("SCHEDULE_START_DATE")).toInstant().toString());
                jsonEvent.put("end", ((Timestamp) event.get("SCHEDULE_END_DATE")).toInstant().toString());
                jsonEvents.add(jsonEvent);
            }
            model.addAttribute("events", jsonEvents);  // model에 일정 데이터 추가
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "board/calendar";  // 캘린더 뷰 반환
    }

    // 일정 추가 요청 처리
    @PostMapping(value = "/addEvent.do", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, String>> addEvent(@RequestBody CalendarVO inVO) {
        Map<String, String> response = new HashMap<>();

        try {
            // 이벤트 추가 처리 (DB에 저장)
            calendarDaoJdbc.createEvent(inVO);
            response.put("message", "일정이 등록되었습니다.");

            // 성공 시 JSON 응답 반환
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "일정 등록에 실패했습니다.");
            // 실패 시 JSON 응답 반환
            return ResponseEntity.status(500).body(response);
        }
    }

    // 일정 데이터를 가져오기 (JSON 반환)
    @GetMapping(value = "/getEvents.do", produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<Map<String, String>>> getEvents() {
        try {
            // 일정 데이터 가져오기
            List<Map<String, Object>> events = calendarDaoJdbc.getCalendarEvents();
            List<Map<String, String>> jsonEvents = new ArrayList<>();

            for (Map<String, Object> event : events) {
                Map<String, String> jsonEvent = new HashMap<>();
                // 일정 제목
                jsonEvent.put("title", (String) event.get("SCHEDULE_TITLE"));

                // 시작 날짜 (ISO-8601 형식으로 변환)
                jsonEvent.put("start", ((Timestamp) event.get("SCHEDULE_START_DATE")).toInstant().toString());
                // 종료 날짜 (ISO-8601 형식으로 변환)
                jsonEvent.put("end", ((Timestamp) event.get("SCHEDULE_END_DATE")).toInstant().toString());
                jsonEvents.add(jsonEvent);
            }

            // 성공 시 JSON 응답 반환
            return ResponseEntity.ok(jsonEvents);

        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 발생 시 HTTP 500 상태 코드와 함께 에러 메시지 반환
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "일정 데이터를 가져오는 중 오류가 발생했습니다.");
            return ResponseEntity.status(500).body(Collections.singletonList(errorResponse));
        }
    }

}
