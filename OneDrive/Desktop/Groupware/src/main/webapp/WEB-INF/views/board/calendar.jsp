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
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/bootstrap5@6.1.14/index.global.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet" />

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
        @media (min-width: 1920px) {
            .container, .container-lg, .container-md, .container-sm, .container-xl {
                max-width: 1920px !important;
            }
        }
        @media (min-width: 1920px) {
            .container, .container-lg, .container-md, .container-sm, .container-xl, .container-xxl {
                 max-width: 1920px !important;
            }
        }

        @media (min-width: 1920px) {
            .container, .container-lg, .container-md, .container-sm, .container-xl {
                 max-width: 1920px !important;
            }
        }
        @media (min-width: 1920px) {
            .container, .container-lg, .container-md, .container-sm {
                 max-width: 1920px !important;
            }
        }
        @media (min-width: 1920px) {
            .container, .container-md, .container-sm {
                 max-width: 1920px !important;
            }
        }
        @media (min-width: 1920px) {
            .container, .container-sm {
                 max-width: 1920px !important;
            }
        }

    </style>
</head>
<body>
<div id="calendar"></div>

<script>
    /*document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            themeSystem: 'bootstrap5', // BootStrap5 디자인 사용
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay',
            },
            buttonText: {
                today: '현재날짜',
                month: '월',
                week: '주',
                day: '일',
                list: '목록',
            },
            events: 'https://fullcalendar.io/api/demo-feeds/events.json',
        });
        calendar.render();
    });*/


    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            headerToolbar: {
                center: 'addEventButton'
            },
            customButtons: {
                addEventButton: {
                    text: '일정추가',
                    click: function() {
                        var dateStr = prompt('날짜를 YYYY-MM-DD 형식으로 입력해주세요');
                        var date = new Date(dateStr + 'T00:00:00'); // will be in local time
                        var ScheduleTitle = prompt('일정 제목을 입력해주세요');

                        if (!isNaN(date.valueOf())) { // valid?
                            calendar.addEvent({
                                title: ScheduleTitle,
                                start: date,
                                allDay: true
                            });
                            // 서버로 일정 데이터 전송
                            fetch('/addEvent', {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/json'
                                },
                                body: JSON.stringify({
                                    title: ScheduleTitle,
                                    start: date.toISOString(), // 서버에서는 ISO 형식으로 날짜를 받을 것
                                    allDay: true
                                })
                            })
                                .then(response => response.json())
                                .then(data => {
                                    alert('일정이 등록되었습니다');
                                })
                                .catch(error => {
                                    console.error('Error:', error);
                                    alert('일정 등록에 실패했습니다');
                                });
                        } else {
                            alert('잘못된 날짜 형식입니다.');
                        }
                    }
                }
            }
        });

        calendar.render();
    });

</script>
</body>
</html>
