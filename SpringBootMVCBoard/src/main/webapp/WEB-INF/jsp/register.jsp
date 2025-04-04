<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.4/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-DQvkBjpPgn7RC31MCQoOeC9TI2kdqa4+BSgNMNj8v77fdC77Kj5zpWFTJaaAoMbC" crossorigin="anonymous">
    <title>회원 가입</title>
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
        <h2>회원 가입</h2>
    </div>
    <form novalidate>
        <div class="mb-3">
            <label for="userName" class="form-label">Username</label>
            <input type="text" class="form-control" id="userName" name="userName" aria-describedby="Enter Username">
            <div class = "valid-feedback" name="is-valid">Valid</div>
            <div class = "invalid-feedback">Username은 2글자 이상</div>
        </div>
        <div class="mb-3">
            <label for="userPassword" class="form-label">Password</label>
            <input type="password" class="form-control" id="userPassword" name="userPassword" aria-describedby="Enter Password">
            <div class = "valid-feedback">Valid</div>
            <div class = "invalid-feedback">Password는 적어도 1개이상의 특수문자, 알파벳, 숫자 포함</div>
        </div>
        <div class="mb-3">
            <label for="userPassword2" class="form-label">Confirm Password</label>
            <input type="password" class="form-control" id="userPassword2" name="userPassword2" aria-describedby="Confirm Password">
            <div class = "valid-feedback">Valid</div>
            <div class = "invalid-feedback">Password는 적어도 1개이상의 특수문자, 알파벳, 숫자 포함</div>
        </div>
        <div class="mb-3">
            <label for="userEmail" class="form-label">Email</label>
            <input type="email" class="form-control" id="userEmail" name="userEmail" aria-describedby="Enter Email">
            <div class = "valid-feedback">Valid</div>
            <div class = "invalid-feedback">이메일 형식 지켜야 함</div>
        </div>
    </form>
    <div>
        <button type="button" id="btnRegister" class="btn btn-primary">회원 가입</button>
    </div>
</div>

<script>
    window.onload = function(){
        // btnRegister 처리
        document.querySelector("#btnRegister").onclick = function(){
            // validation check
            if(document.querySelectorAll("form .is-invalid").length > 0){
                alert("입력이 올바르지 않습니다");
                return;
            }
            register();
        }

        // focus 를 잃을 때 <- 입력 완료 후 다른 입력으로 넘어갈 때
        document.querySelector("#userName").onblur = function (){
            if(validateUserName(this.value)){
                this.classList.remove("is-invalid");
                this.classList.add("is-valid");
            }else{
                this.classList.remove("is-valid");
                this.classList.add("is-invalid");
            }
        }
        document.querySelector("#userPassword").onblur = function (){
            if(validatePassword(this.value)){
                this.classList.remove("is-invalid");
                this.classList.add("is-valid");
            }else{
                this.classList.remove("is-valid");
                this.classList.add("is-invalid");
            }
        }
        document.querySelector("#userPassword2").onblur = function (){
            if(validatePassword2(this.value)){
                this.classList.remove("is-invalid");
                this.classList.add("is-valid");
            }else{
                this.classList.remove("is-valid");
                this.classList.add("is-invalid");
            }
        }
        document.querySelector("#userEmail").onblur = function (){
            if(validateEmail(this.value)){
                this.classList.remove("is-invalid");
                this.classList.add("is-valid");
            }else{
                this.classList.remove("is-valid");
                this.classList.add("is-invalid");
            }
        }

    }

    function validateUserName(userName){
        // 2글자 이상
        if( userName.length >= 2) return true;
        return false;
    }

    function validatePassword(userPassword){
        // 알파벳 적어도 1개 이상
        // 특수문자 적어도 1개 이상
        // 숫자 적어도 1개 이상
        let patternEngAtListOne = new RegExp(/[a-zA-Z]+/);// + for at least one
        let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/);// + for at least one
        let patternNumAtListOne = new RegExp(/[0-9]+/);// + for at least one

        if(patternEngAtListOne.test(userPassword) &&
            patternSpeAtListOne.test(userPassword) &&
            patternNumAtListOne.test(userPassword) ) return true;
        return false;
    }

    function validatePassword2(userPassword2){
        if(userPassword2 == document.querySelector("#userPassword").value) return true;
        return false;
    }

    function validateEmail(userEmail){
        // @ . 구분
        let regexp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        if(regexp.test(userEmail)) return true;
        return false;
    }

    async function register(){
        let userName = document.querySelector("#userName").value;
        let userPassword = document.querySelector("#userPassword").value;
        let userEmail = document.querySelector("#userEmail").value;

        let urlParams = new URLSearchParams({
            userName: userName,
            userPassword: userPassword,
            userEmail: userEmail
        });

        let fetchOptions = {
            method: "POST",
            body: urlParams
        };
        let response = await fetch("/user/register", fetchOptions);
        let data = await response.json();

        if(data.result == "success"){
            alert("회원 가입 성공");
            // 로그인 페이지 이동
            window.location.href="/pages/login";
        }else{
            alert("회원 가입 실패")
        }

    }

</script>
</body>
</html>