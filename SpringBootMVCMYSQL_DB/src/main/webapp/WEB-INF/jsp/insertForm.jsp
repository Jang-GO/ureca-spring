<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>insertForm</title>
</head>
<body>
<h1>도서 등록</h1>
<form action="/books/insert" method="post">
    도서 ID <input type="text" name="bookId"><br>
    도서명 <input type="text" name="bookName"><br>
    출판사 <input type="text" name="publisher"><br>
    가격<input type="text" name="price"><br>
    <button type = "submit">등록</button>
</form>
<hr>
<a href="/books/list">목록</a>
</body>
</html>
