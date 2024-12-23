<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 날짜 포맷을 위한 JSTL 함수 추가 -->
<%@ include file="layout/sidebar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <!-- FullCalendar JS CDN을 이용한 방법 -->

    <style>
        /* 전체 페이지에서 .container 관련 여백 없애기 */
        .container,
        .container-fluid,
        .container-lg,
        .container-md,
        .container-sm,
        .container-xl,
        .container-xxl {
            width: 100% !important;
            padding-right: 0 !important;
            padding-left: 0 !important;
            margin-right: 0 !important;
            margin-left: 0 !important;
        }

        /* 캘린더 스타일 */
        #calendar {
            position: relative;
            width: 60%;
            height: 100%;
            margin-top: 30px;
        }

        /* FullCalendar 내부 스타일의 여백을 제거 */
        .fc {
            margin: 0 ;
            padding: 0 ;
        }

        /* 헤더 및 그리드의 마진과 패딩을 없애기 */
        .fc-toolbar {
            margin: 0 ;
            padding: 0 ;
        }

        .fc-daygrid-body {
            margin: 0 ;
        }

        .fc-button {
            padding: 0.25rem 0.5rem ;
            font-size: 14px ;
        }

        .fc-daygrid-day {
            padding: 0 ;
            margin: 0 ;
        }

        .container, .container-fluid, .container-lg, .container-md, .container-sm, .container-xl, .container-xxl {
            margin-right: 0 !important;
            margin-left: 0 !important;
        }

        .container{
            margin-left: 10% !important;
            width: 100% !important;
            display: flex !important;
        }


        .fc-event-time {
            display: none !important;
        }

    </style>
</head>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@fullcalendar/bootstrap5@6.1.14/index.global.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<body>
<div id="calendar"></div>


</body>

<script>

    let test_db;

    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            headerToolbar: {
                center: 'addEventButton'
            },
            displayEventTime: false,
            eventBackgroundColor: '#990e17',
            eventTimeFormat: {
                hour: '2-digit',
                minute: '2-digit',
                meridiem: false
            },
            selectable: true, // 달력 셀 선택 활성화
            select: function(info) {	// 달력 셀을 클릭할 때 모달 열기
                $('#addEventModal').modal('show');
                $('#start').val(info.startStr);
                $('#end').val(info.endStr);
            },
            customButtons: {
                addEventButton: {
                    text: '일정추가',
                    click: async function() {
                        var scheduleUserId = prompt('사용자 ID를 입력해주세요');
                        var scheduleTitle = prompt('일정 제목을 입력해주세요');
                        var scheduleContent = prompt('일정 내용을 입력해주세요');
                        var dateStr = prompt('시작 날짜를 YYYY-MM-DD 형식으로 입력해주세요');
                        var startDate = new Date(dateStr + 'T00:00:00Z');
                        var endDateStr = prompt('종료 날짜를 YYYY-MM-DD 형식으로 입력해주세요');
                        var endDate = new Date(endDateStr + 'T23:59:59Z');

                        if (!isNaN(startDate.valueOf()) && !isNaN(endDate.valueOf())) {
                            if (startDate <= endDate) {
                                calendar.addEvent({
                                    title: scheduleTitle,
                                    start: startDate,
                                    end: endDate,
                                    allDay: true
                                });

                                var startDateFormatted = startDate.toISOString();


                                // var endDateFormatted = endDate.toISOString();
                                var endDateFormatted = new Date(endDateStr + 'T00:00:00Z');

                                try {
                                    // fetch 요청을 async/await로 처리하여, 요청 완료 후 다음 코드를 실행
                                    const response = await fetch('/calendar/addEvent.do', {
                                        method: 'POST',
                                        headers: {
                                            'Content-Type': 'application/json; '
                                        },
                                        body: JSON.stringify({
                                            scheduleUserId: scheduleUserId,
                                            scheduleTitle: scheduleTitle,
                                            scheduleContent: scheduleContent,
                                            scheduleStartDate: startDateFormatted,
                                            scheduleEndDate: endDateFormatted
                                        })
                                    });

                                    const data = await response.json();
                                    alert(data.message);
                                } catch (error) {
                                    console.error('Error:', error);
                                    alert('일정 등록에 실패했습니다.');
                                }
                            } else {
                                alert('시작 날짜는 종료 날짜보다 늦을 수 없습니다.');
                            }
                        } else {
                            alert('잘못된 날짜 형식입니다.');
                        }
                    }
                }
            },
            events: loadEvents
        })
        calendar.render();
    });


    function loadEvents(fetchInfo, successCallback, failureCallback) {

        fetch('/calendar/getEvents.do')
            .then(response => response.json())
            .then(data => {
                successCallback(data);
            })
            .catch(error => {
                failureCallback(error);
                console.error('Error fetching events:', error);
            });
    }

</script>

</html>
