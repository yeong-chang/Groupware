<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 날짜 포맷을 위한 JSTL 함수 추가 -->
<%@ include file="layout/sidebar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <!-- FullCalendar JS 알집 파일을 이용한 방법 -->
    <!-- <script type="text/javascript" src="/resources/js/index.global.min.js"></script> -->
    <!-- FullCalendar JS CDN을 이용한 방법 -->
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/bootstrap5@6.1.14/index.global.min.js"></script>
</head>
<link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
/>
<link
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css"
        rel="stylesheet"
/>
<style>
    #calendar {
        position: relative;
        height: 600px;
        margin-left: 400px;
        margin-right: 50px;
        margin-top: 50px;
    }
</style>
<body>
<div id="calendar"></div>
</body>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            themeSystem: 'bootstrap5', // BootStrap5 버전으로 디자인 설정
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
    });
</script>
</html>
