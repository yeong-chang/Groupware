package com.pcwk.ehr.calendar.dao;

import com.pcwk.ehr.calendar.domain.CalendarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public class CalendarDaoJdbc implements CalendarDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createEvent(CalendarVO inVO) throws SQLException {
        StringBuilder sb = new StringBuilder(500);

        // ISO 8601 형식의 문자열을 UTC 시간 기준으로 파싱
        String startDateStr = inVO.getScheduleStartDate(); // 예시: "2024-12-20T15:00:00.000Z"
        String endDateStr = inVO.getScheduleEndDate(); // 예시: "2024-12-23T15:00:00.000Z"

        // 날짜 형식을 파싱하기 위해 DateTimeFormatter 사용
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        OffsetDateTime startDate = OffsetDateTime.parse(startDateStr, formatter);  // UTC 시간 처리
        OffsetDateTime endDate = OffsetDateTime.parse(endDateStr, formatter);      // UTC 시간 처리

        // OffsetDateTime을 Timestamp로 변환 (오라클 TIMESTAMP와 호환)
        Timestamp startTimestamp = Timestamp.from(startDate.toInstant());
        Timestamp endTimestamp = Timestamp.from(endDate.toInstant());

        sb.append("INSERT INTO CALENDAR (SCHEDULE_ID, SCHEDULE_USER_ID, SCHEDULE_TITLE, SCHEDULE_CONTENT, ")
                .append("SCHEDULE_START_DATE, SCHEDULE_END_DATE, SCHEDULE_STATUS) ")
                .append("VALUES (1, ?, ?, ?, ?, ?, 10)");

        // 파라미터 설정
        Object[] params = new Object[]{
                inVO.getScheduleUserId(),
                inVO.getScheduleTitle(),
                inVO.getScheduleContent(),
                startTimestamp,
                endTimestamp,
        };

        return jdbcTemplate.update(sb.toString(), params);
    }

    // 캘린더 이벤트를 가져오는 메서드
    public List<Map<String, Object>> getCalendarEvents() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT SCHEDULE_ID, SCHEDULE_USER_ID, SCHEDULE_TITLE, SCHEDULE_CONTENT, ")
                .append("SCHEDULE_START_DATE, SCHEDULE_END_DATE ")
                .append("FROM CALENDAR WHERE SCHEDULE_STATUS = 10");

        return jdbcTemplate.queryForList(sb.toString());
    }


}
