<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="layout/Header.jsp" %>
<%@ include file="layout/sidebar.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>공지사항 상세보기</title>
    <style>
        :root {
            --white: #fff;
            --blue50: #e8f3ff;
            --blue100: #c9e2ff;
            --blue200: #90c2ff;
            --blue300: #64a8ff;
            --blue400: #4593fc;
            --blue500: #3182f6;
            --blue600: #2272eb;
            --blue700: #1b64da;
            --blue800: #1957c2;
            --blue900: #194aa6;
            --gray: #f5f5f5;
            --dark-gray: #333;
        }
        body{
            margin-top: 0px;
        }
        /* Main Content */
        .main-content {
            margin-left: 15%; /* 사이드바 공간을 고려하여 왼쪽 여백을 설정 */
            padding: 20px;
            margin-top: 80px; /* 헤더 아래로 시작 */
            background-color: var(--white);
            height: 100vh;
            overflow-y: auto;
        }

        /* 공지사항 상세 보기 페이지 스타일 */
        .content {
            background-color: var(--white);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .title {
            font-size: 32px;
            font-weight: bold;
            margin-bottom: 20px;
            color: var(--dark-gray);
        }
        .details {
            font-size: 18px;
            line-height: 1.6;
            color: var(--dark-gray);
            margin-bottom: 30px;
        }
        .back-button {
            padding: 12px 20px;
            font-size: 16px;
            cursor: pointer;
            background-color: var(--blue500);
            color: var(--white);
            border: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .back-button:hover {
            background-color: var(--blue400);
        }

    </style>
</head>
<body>
<div class="main-content">
    <div class="content">
        <h1 class="title">${board.article_title}</h1>
        <p class="details">${board.article_contents}</p>
        <a href="announcement.do"><button class="back-button">목록으로 돌아가기</button></a>
    </div>
</div>
</body>
</html>
