package com.pcwk.ehr.calendar.domain;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CalendarVO {
     int scheduleId;
     int scheduleUserId;
     String scheduleStartDate;
     String scheduleEndDate;
     String scheduleContent;
     String scheduleTitle;
     int scheduleStatus;



}
