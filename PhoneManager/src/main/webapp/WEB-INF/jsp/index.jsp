<%@ page import="hello.phonemanager.domain.Owner" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<!doctype html>
<%
    Owner loginOwner = (Owner)session.getAttribute("loginOwner");

    Integer totalShopsObj = (Integer)request.getAttribute("totalShops");
    Integer monthlySalesObj = (Integer)request.getAttribute("monthlySales");
    Long totalRevenueObj = (Long)request.getAttribute("totalRevenue");

    int totalShops = (totalShopsObj != null) ? totalShopsObj : 0;
    int monthlySales = (monthlySalesObj != null) ? monthlySalesObj : 0;
    long totalRevenue = (totalRevenueObj != null) ? totalRevenueObj : 0;

    // 통화 형식으로 매출 포맷팅
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
    String formattedRevenue = currencyFormatter.format(totalRevenue);
%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>휴대폰 판매관리 시스템</title>
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
        .feature-card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            height: 100%;
        }
        .feature-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.12);
        }
        .feature-icon {
            font-size: 2.5rem;
            margin-bottom: 1rem;
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
        .footer {
            background-color: #343a40;
            color: #adb5bd;
            padding: 20px 0;
            margin-top: 80px;
            border-radius: 20px 20px 0 0;
        }
        .stats-box {
            background-color: rgba(255, 255, 255, 0.2);
            border-radius: 10px;
            padding: 15px;
            margin-top: 20px;
            backdrop-filter: blur(5px);
        }
        .welcome-badge {
            background-color: rgba(255,255,255,0.2);
            border-radius: 20px;
            padding: 8px 16px;
            margin-right: 10px;
            backdrop-filter: blur(5px);
        }
        .stats-row {
            margin-top: 30px;
        }
        .stats-number {
            font-weight: bold;
        }
        .stats-trend {
            font-size: 0.8rem;
            margin-left: 5px;
        }
        .stats-trend-up {
            color: #28a745;
        }
        .stats-trend-down {
            color: #dc3545;
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
        <h1 class="display-4 fw-bold mb-3">휴대폰 판매관리 시스템</h1>
        <p class="lead mb-4">점주용 휴대폰 판매관리 시스템</p>

        <% if (loginOwner != null) { %>
        <div class="row justify-content-center stats-row">
            <div class="col-md-3">
                <div class="stats-box">
                    <h3 class="h5">총 매장 수</h3>
                    <h2 class="display-6 fw-bold"><%= totalShops %></h2>
                </div>
            </div>
            <div class="col-md-3">
                <div class="stats-box">
                    <h3 class="h5">이번 달 판매</h3>
                    <h2 class="display-6 fw-bold">
                        <%= monthlySales %>
                        <% if (monthlySales > 0) { %>
                        <span class="stats-trend stats-trend-up">
                                <i class="fas fa-arrow-up"></i>
                            </span>
                        <% } %>
                    </h2>
                </div>
            </div>
            <div class="col-md-3">
                <div class="stats-box">
                    <h3 class="h5">총 매출</h3>
                    <h2 class="display-6 fw-bold"><%= formattedRevenue %></h2>
                </div>
            </div>
        </div>
        <% } else { %>
        <a href="/owner/login" class="btn btn-light btn-lg px-4 me-3 btn-custom">
            <i class="fas fa-sign-in-alt me-2"></i> 시작하기
        </a>
        <% } %>
    </div>
</section>

<div class="container mb-5">
    <div class="row g-4">
        <div class="col-md-4">
            <div class="card feature-card">
                <div class="card-body text-center p-4">
                    <div class="feature-icon text-primary">
                        <i class="fas fa-store"></i>
                    </div>
                    <h3 class="card-title h4">샵 관리</h3>
                    <a href="/shop/list" class="btn btn-primary btn-custom mt-3 w-100">
                        <i class="fas fa-list me-2"></i> 샵 목록 보기
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card feature-card">
                <div class="card-body text-center p-4">
                    <div class="feature-icon text-success">
                        <i class="fas fa-plus-circle"></i>
                    </div>
                    <h3 class="card-title h4">새 샵 등록</h3>
                    <a href="/shop/new" class="btn btn-success btn-custom mt-3 w-100">
                        <i class="fas fa-plus me-2"></i> 샵 등록하기
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card feature-card">
                <div class="card-body text-center p-4">
                    <div class="feature-icon text-warning">
                        <i class="fas fa-chart-bar"></i>
                    </div>
                    <h3 class="card-title h4">판매 내역</h3>
                    <a href="/sales/history" class="btn btn-warning btn-custom mt-3 w-100">
                        <i class="fas fa-chart-line me-2"></i> 판매 내역 보기
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>