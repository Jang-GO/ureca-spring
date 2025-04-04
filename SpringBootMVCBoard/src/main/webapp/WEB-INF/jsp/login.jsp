<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.4/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-DQvkBjpPgn7RC31MCQoOeC9TI2kdqa4+BSgNMNj8v77fdC77Kj5zpWFTJaaAoMbC" crossorigin="anonymous">
    <title>로그인</title>
</head>
<body>
<!-- validation check
    브라우저 내장 유효성 검사 OFF <- <form novalidate>
    Bootstrap 제공 유효성 검사 ON
-->
<div class="container">
    <div class="mb-3 mt-3 d-flex justify-content-center">
        <h1 class="display-4">장현서 세상</h1>
    </div>
    <div class = "mb-2">
        <h2>로그인</h2>
    </div>
    <form novalidate>
        <div class="mb-3">
            <label for="userEmail" class="form-label">Email</label>
            <input type="email" class="form-control" id="userEmail" aria-describedby="Enter Email">
        </div>
        <div class="mb-3">
            <label for="userPassword" class="form-label">Password</label>
            <input type="password" class="form-control" id="userPassword" aria-describedby="Enter Password">
        </div>

    </form>
    <div>
        <button type="button" id="btnLogin" class="btn btn-primary">로그인</button>
    </div>
</div>

<script>
    window.onload = function(){
        document.querySelector("#btnLogin").onclick = function(){
            // validation check
            if(document.querySelector("#userEmail").value == '' || document.querySelector("#userPassword").value == ''){
                alert("입력이 올바르지 않습니다");
                return;
            }
            login();
        }

    }

    async function login(){
        let userEmail = document.querySelector("#userEmail").value;
        let userPassword = document.querySelector("#userPassword").value;

        let urlParams = new URLSearchParams({
            userEmail: userEmail,
            userPassword: userPassword
        });

        let fetchOptions = {
            method: "POST",
            body: urlParams
        };
        let response = await fetch("/auth/login", fetchOptions);
        let data = await response.json();

        if(data.result == "success") {
            // 게시판 페이지 이동
            window.location.href="/pages/board";
        }else{
            alert("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
    }

</script>
</body>
</html>