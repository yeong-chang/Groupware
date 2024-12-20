package com.pcwk.ehr.calendar.dao;

import com.pcwk.ehr.calendar.domain.CalendarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class CalendarDaoJdbc implements CalendarDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createEvent(CalendarVO inVO) throws SQLException {
        StringBuilder sb = new StringBuilder(500);


        // 캘린더 테이블에 대한 INSERT 문
        sb.append("INSERT INTO CALENDAR (SCHEDULE_ID, SCHEDULE_USER_ID, SCHEDULE_START_DATE, ")
                .append("SCHEDULE_END_DATE, SCHEDULE_CONTENT, SCHEDULE_TITLE, SCHEDULE_STATUS) ")
                .append("VALUES (calendar_schedule_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)");

        // 파라미터 설정: CalendarVO 객체에서 값을 추출
        Object[] params = new Object[] {
                inVO.getScheduleUserId(),
                inVO.getScheduleStartDate(),
                inVO.getScheduleEndDate(),
                inVO.getScheduleContent(),
                inVO.getScheduleTitle(),
                inVO.getScheduleStatus()
        };

        // jdbcTemplate을 사용하여 쿼리 실행
        return jdbcTemplate.update(sb.toString(), params);
    }
}
