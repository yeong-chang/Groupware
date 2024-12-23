package com.pcwk.ehr.calendar.dao;

import com.pcwk.ehr.calendar.domain.CalendarVO;

import java.sql.SQLException;

public interface CalendarDao {
    int createEvent(CalendarVO inVO)throws SQLException;
}
