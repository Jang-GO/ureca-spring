<%@ page import="hello.springbootmvcboard.user.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    UserDto userDto = (UserDto) session.getAttribute("userDto");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.4/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-DQvkBjpPgn7RC31MCQoOeC9TI2kdqa4+BSgNMNj8v77fdC77Kj5zpWFTJaaAoMbC" crossorigin="anonymous">
    <title>게시판</title>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container">
        <a class="navbar-brand" href="#">
            <img src="/assets/img/user/<%= userDto.getUserProfileImage()%>" style="width: 24px; height: 24px">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/auth/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<script>
    // logut 처리 방식 구분
    // #1. Javascript 를 이용한 비동기 처리
    //     Logout 이 클릭되면 이벤트 핸들러에서 비동기로 logout 요청 ( 서버에서 로그아웃 처리 후 result:success 전달 )
    //     페이지를 window.location.href 로 login 페이지 이동
    // #2. a 의 href 에 page 요청 ( 서버에서 로그아웃 처리 후 바로 페이지를 이동 )
</script>
</body>
</html>