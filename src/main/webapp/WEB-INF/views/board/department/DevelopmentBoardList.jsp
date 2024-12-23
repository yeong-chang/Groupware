<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/Header.jsp" %>
<%@ include file="../layout/sidebar.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>개발 게시판</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            display: flex;
        }

        .main-content {
            width: 90%;
            height: 95%;
            margin-left: 10%;
            margin-top: 5%;
            padding: 20px;
            background-color: #fff;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        tr {
            width: 400px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 9px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            text-align: center;
        }

        .board-title {
            font-size: 24px;
            margin-top: -20px;
            margin-bottom: 20px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <!-- 메인 콘텐츠 영역 -->
    <div class="main-content">
        <h1 class="board-title">개발 게시판</h1>

        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="board" items="${boardList}">
                    <c:if test="${board.article_board_div == 30}">
                        <tr>
                            <td>${board.article_no}</td>
                            <td><a href="boardDetail.do?article_no=${board.article_no}">${board.article_title}</a></td>
                            <td>${board.article_user_id}</td>
                            <td>${board.article_reg_date}</td>
                        </tr>    
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
        <br>

        <!-- 게시글 작성 버튼 -->
        <a href="departmentwrite.do"><button>부서별 게시글 작성</button></a>
    </div>

</body>
</html>
