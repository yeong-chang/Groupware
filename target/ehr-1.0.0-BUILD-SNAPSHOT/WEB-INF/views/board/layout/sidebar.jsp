<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <style>

    .container {
      width: 100%;
        display: flex;
    }
    .container-fluid {
        flex-grow: 1;
        background-color: #ffffff;
        height: 60px;
        margin-top: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        overflow-y: auto;
        text-decoration: none;
        color: #333;
        transition: background-color 0.3s, color 0.3s;
    }
    .container-fluid:hover {
        background-color: #000000;
        color: #00ff00;
    }
    .sidebar {
        width: 15%;
        background-color: #f1f1f1;
        height: 100vh;
        padding: 20px;
    }
    .sidebar h2 {
        margin-top: 0;
    }
    .sidebar ul {
        list-style-type: none;
        padding: 0;
    }
    .main-content {
        flex-grow: 1;
        padding: 20px;
    }
</style>
    <title>게시판</title>
</head>
<body>
    <div class="container">
        <nav class="sidebar">
            <h2>게시판 메뉴</h2>
            <ul>
                <li><a href="announcement.do" class="container-fluid">공지 게시판</a></li>
                <li><a href="free.do" class="container-fluid">자유 게시판</a></li>
                <li><a href="development.do" class="container-fluid">개발 부서 게시판</a></li>
                <li><a href="hr.do" class="container-fluid">인사처 게시판</a></li>
                <li><a href="marketing.do" class="container-fluid">마케팅 부서 게시판</a></li>
            </ul>
        </nav>
</body>
</html>
