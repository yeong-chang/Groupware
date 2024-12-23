<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>부서 게시글 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .form-container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        .form-container h2 {
            text-align: center;
        }
        .form-container label {
            display: block;
            margin: 10px 0 5px;
        }
        .form-container input, .form-container textarea, .form-container select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>부서 게시글 작성</h2>
        <form action="departmentboard.do" method="post">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" placeholder="게시글 제목을 입력하세요" required>

            <label for="content">내용</label>
            <textarea id="content" name="content" rows="10" placeholder="게시글 내용을 입력하세요" required></textarea>

            <label for="author">작성자 ID</label>
            <input type="text" id="author" name="author" placeholder="작성자 ID를 입력하세요" required>

            <label for="department">부서</label>
            <select id="department" name="department" required>
                <option value="">부서를 선택하세요.</option>
                <option value=30>개발</option>
                <option value=40>인사</option>
                <option value=50>마케팅</option>
            </select>

            <button type="submit">작성 완료</button>
        </form>
    </div>

</body>
</html>
