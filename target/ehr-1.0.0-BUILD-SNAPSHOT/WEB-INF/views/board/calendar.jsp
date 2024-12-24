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


<!-- 일정 추가 모달 -->
<div class="modal" tabindex="-1" id="scheduleModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">일정 추가</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="scheduleUserId" class="form-label">사용자 ID</label>
                    <input type="text" class="form-control" id="scheduleUserId">
                </div>
                <div class="mb-3">
                    <label for="scheduleTitle" class="form-label">일정 제목</label>
                    <input type="text" class="form-control" id="scheduleTitle">
                </div>
                <div class="mb-3">
                    <label for="scheduleContent" class="form-label">일정 내용</label>
                    <input type="text" class="form-control" id="scheduleContent">
                </div>
                <div class="mb-3">
                    <label for="startDate" class="form-label">시작 날짜</label>
                    <input type="date" class="form-control" id="startDate">
                </div>
                <div class="mb-3">
                    <label for="endDate" class="form-label">종료 날짜</label>
                    <input type="date" class="form-control" id="endDate">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary" id="saveScheduleButton">일정 저장</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS 및 의존성 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
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
            select: function(info) {
                // 달력 셀 클릭 시 모달 열기
                var myModal = new bootstrap.Modal(document.getElementById('scheduleModal'));
                myModal.show();
                // 날짜 자동 설정
                document.getElementById('startDate').value = info.startStr.split('T')[0];
                document.getElementById('endDate').value = info.endStr.split('T')[0];
            },
            customButtons: {
                addEventButton: {
                    text: '일정 추가',
                    click: function() {
                        var myModal = new bootstrap.Modal(document.getElementById('scheduleModal'));
                        myModal.show();
                    }
                }
            },
            events: loadEvents
        });
        calendar.render();

        // 일정 저장 버튼 클릭
        document.getElementById('saveScheduleButton').addEventListener('click', async function() {
            var scheduleUserId = document.getElementById('scheduleUserId').value;
            var scheduleTitle = document.getElementById('scheduleTitle').value;
            var scheduleContent = document.getElementById('scheduleContent').value;
            var scheduleStartDate = document.getElementById('startDate').value;
            var scheduleEndDate = document.getElementById('endDate').value;

            var startDate = new Date(scheduleStartDate + 'T00:00:00Z');
            var endDate = new Date(scheduleEndDate + 'T23:59:59Z');

            if (!isNaN(startDate.valueOf()) && !isNaN(endDate.valueOf())) {
                if (startDate <= endDate) {
                    // 캘린더에 이벤트 추가
                    calendar.addEvent({
                        title: scheduleTitle,
                        start: startDate,
                        end: endDate,
                        allDay: true
                    });

                    try {
                        // 서버에 일정 저장
                        const response = await fetch('/calendar/addEvent.do', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                scheduleUserId: scheduleUserId,
                                scheduleTitle: scheduleTitle,
                                scheduleContent: scheduleContent,
                                scheduleStartDate: startDate.toISOString(),
                                scheduleEndDate: endDate.toISOString()
                            })
                        });

                        const data = await response.json();
                        alert(data.message); // 서버 응답 처리
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

            // 모달 닫기
            var myModal = bootstrap.Modal.getInstance(document.getElementById('scheduleModal'));
            myModal.hide();
        });
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
</body>
</html>
