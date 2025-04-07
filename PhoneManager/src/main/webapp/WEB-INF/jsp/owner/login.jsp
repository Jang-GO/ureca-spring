<%@ page import="hello.phonemanager.domain.Owner" contentType="text/html; charset=UTF-8" %>
<!doctype html>
<%
    Owner loginOwner = (Owner)session.getAttribute("loginOwner");
    String errorMessage = (String)request.getAttribute("errorMessage");
%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>로그인 - 휴대폰 판매관리 시스템</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .navbar-brand {
            font-weight: bold;
            font-size: 1.4rem;
        }
        .hero-section {
            background: linear-gradient(135deg, #13547a 0%, #80d0c7 100%);
            color: white;
            padding: 60px 0;
            border-radius: 0 0 20px 20px;
            margin-bottom: 30px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        .btn-custom {
            padding: 12px 24px;
            font-weight: 600;
            letter-spacing: 0.5px;
            border-radius: 8px;
            transition: all 0.3s ease;
        }
        .btn-custom:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .welcome-badge {
            background-color: rgba(255,255,255,0.2);
            border-radius: 20px;
            padding: 8px 16px;
            margin-right: 10px;
            backdrop-filter: blur(5px);
        }
        .login-form {
            max-width: 500px;
            margin: 0 auto;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
            background-color: white;
        }
        .form-control {
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .form-label {
            font-weight: 600;
        }
        .input-group-text {
            padding-left: 15px;
            padding-right: 15px;
            background-color: #f8f9fa;
            border-color: #dee2e6;
        }
        .input-group .form-control {
            height: 50px;
        }
        .input-group-text {
            height: 50px;
            display: flex;
            align-items: center;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <div class="container">
        <a class="navbar-brand" href="/">
            <i class="fas fa-mobile-alt me-2"></i>PhoneManager
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/shop/list"><i class="fas fa-store me-1"></i> 샵 목록</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/sales/history"><i class="fas fa-chart-line me-1"></i> 판매 내역</a>
                </li>
            </ul>
            <div class="d-flex align-items-center">
                <% if (loginOwner != null) { %>
                <span class="welcome-badge text-white">
                    <i class="fas fa-user-circle me-1"></i> <%= loginOwner.getName() %> 님
                </span>
                <a class="btn btn-outline-light btn-sm" href="/owner/logout">
                    <i class="fas fa-sign-out-alt me-1"></i> 로그아웃
                </a>
                <% } else { %>
                <a class="btn btn-outline-light me-2" href="/owner/login">
                    <i class="fas fa-sign-in-alt me-1"></i> 로그인
                </a>
                <a class="btn btn-primary" href="/owner/register">
                    <i class="fas fa-user-plus me-1"></i> 회원가입
                </a>
                <% } %>
            </div>
        </div>
    </div>
</nav>

<section class="hero-section">
    <div class="container text-center">
        <h1 class="display-4 fw-bold mb-3">로그인</h1>
    </div>
</section>

<div class="container">
    <div class="login-form mt-4">
        <form action="/owner/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">아이디</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
            </div>
            <div class="mb-4">
                <label for="password" class="form-label">비밀번호</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary btn-lg btn-custom">
                    <i class="fas fa-sign-in-alt me-2"></i> 로그인
                </button>
            </div>
            <div class="text-center mt-3">
                <p>계정이 없으신가요? <a href="/owner/register">회원가입</a></p>
            </div>
        </form>
    </div>
</div>

<!-- 로그인 실패 시 알림 -->
<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
<script>
    window.onload = function() {
        alert("<%= errorMessage %>");
    }
</script>
<% } %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>